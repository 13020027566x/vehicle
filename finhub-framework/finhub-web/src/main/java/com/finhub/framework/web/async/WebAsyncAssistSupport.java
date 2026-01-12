package com.finhub.framework.web.async;

import com.finhub.framework.core.str.StringUtils;
import com.finhub.framework.exception.ArgumentException;
import com.finhub.framework.exception.util.ClassUtils;
import com.finhub.framework.web.async.annotation.AsyncProxy;
import com.finhub.framework.web.async.entity.AsyncRequest;
import com.finhub.framework.web.async.helper.WebAsyncHelper;
import com.finhub.framework.web.constant.WebAsyncModeEnum;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationImpl;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * @author : liuwei
 * @date : 2022/1/12
 * @desc :
 */
@Slf4j
@UtilityClass
public final class WebAsyncAssistSupport {

    private static final String PROXY_TARGET_METHOD_SUFFIX = "TargetProxy$";

    public static final String REQUEST_TIMEOUT_RESULT_ATTRIBUTE = "vehicle-web-async-task-timeout-result";

    /**
     * java assist 改写源代码
     *
     * @param sourceName                目标路由类 @Controller
     * @return                          修改后的class字节码文件
     * @throws NotFoundException        无法找到方法或者类
     * @throws CannotCompileException   无法编译异常
     * @throws ClassNotFoundException   class无法找到异常
     */
    public static Class<?> assistClass(String sourceName)
        throws NotFoundException, CannotCompileException, ClassNotFoundException {

        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath(classPool.appendSystemPath());
        CtClass ct = classPool.getCtClass(sourceName);
        //解析获取要代理异步的方法
        List<CtMethod> needProxyMethods = parseGetNeedProxyMethod(ct);
        //无mvc异步返回原类。
        if (ArrayUtil.isEmpty(needProxyMethods)) {
            return Class.forName(sourceName);
        }

        ConstPool constPool = ct.getClassFile().getConstPool();
        for (CtMethod ctMethod : needProxyMethods) {
            //获取注解
            AsyncRequest requestProperties = getAsyncRequestAnnotation(ct, ctMethod);
            //重定义原方法
            rewriteMethodDefinition(ct, constPool, ctMethod);
            //改写路由方法
            writeMethodProxyDefinition(classPool, ct, constPool, ctMethod, requestProperties);

        }
        Class<?> result = ct.toClass(Thread.currentThread().getContextClassLoader(), null);
        //添加参数类型缓存
        WebAsyncHelper.addTargetMethodParametersType(result);
        ct.defrost();
        //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
        ct.detach();
        return result;
    }




    /**
     * 解析目标class文件并且获取得到需要代理的方法
     *
     * @param ct    目标class
     * @return      需要代理方法（需要改写的方法）
     */
    private static List<CtMethod> parseGetNeedProxyMethod(CtClass ct) {

        List<CtMethod> needProxyMethods = Lists.newArrayList();

        CtMethod[] ctMethods = ct.getMethods();
        if (ct.hasAnnotation(com.finhub.framework.web.async.annotation.AsyncRequest.class)) {
            //类标记注解，将所有路由方法进行改写
            for (CtMethod ctMethod : ctMethods) {
                if (isRouteMethod(ctMethod) && isProxyReturnType(ctMethod)) {
                    needProxyMethods.add(ctMethod);
                }
            }
            return needProxyMethods;
        }
        //非类级别，找到路由方法带有 AsyncRequest注解方法，进行改写。
        for (CtMethod ctMethod : ctMethods) {
            if (isRouteMethod(ctMethod) && ctMethod.hasAnnotation(
                com.finhub.framework.web.async.annotation.AsyncRequest.class) && isProxyReturnType(ctMethod)) {
                needProxyMethods.add(ctMethod);
            }
        }
        return needProxyMethods;
    }




    /**
     * 根据方法的返回值属性，判断是否需要代理，其中方法 void返回值&spring-mvc支持的异步响应体作为方法的返回体不进行代理。
     *
     * @param ctMethod  判断方法
     * @return          true 需要代理，false不需要
     */
    private static boolean isProxyReturnType(CtMethod ctMethod) {
        CtClass returnType;
        try {
            returnType = ctMethod.getReturnType();
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new ArgumentException(
                String.format("get method:%s. return type class error", ctMethod.getLongName()), e);
        }

        boolean result = true;
        if (returnType == CtClass.voidType) {
            result = false;
        }

        //spring-mvc support async returnType class
        String name = returnType.getName();
        if (name.equalsIgnoreCase(DeferredResult.class.getName())) {
            result = false;
        }

        if (name.equalsIgnoreCase(ResponseBodyEmitter.class.getName())) {
            result = false;
        }

        if (name.equalsIgnoreCase(Callable.class.getName())) {
            result = false;
        }

        if (name.equalsIgnoreCase(SseEmitter.class.getName())) {
            result = false;
        }

        if (name.equalsIgnoreCase(StreamingResponseBody.class.getName())) {
            result = false;
        }

        if (name.equalsIgnoreCase(WebAsyncTask.class.getName())) {
            result = false;
        }

        if (!returnType.isPrimitive()) {
            returnType.defrost();
            returnType.detach();
        }

        return result;
    }


    /**
     * 是否为路由方法
     *
     * @param ctMethod  解析方法
     * @return          true 路由方法，false不是路由方法
     */
    private static boolean isRouteMethod(CtMethod ctMethod) {
         return ctMethod.hasAnnotation(RequestMapping.class)
            || ctMethod.hasAnnotation(GetMapping.class)
            || ctMethod.hasAnnotation(PostMapping.class);
    }


    /**
     * 获取注解，优先级方法>类
     *
     * @param ct        目标类
     * @param ctMethod  目标方法
     * @return          注解属性
     */
    private static AsyncRequest getAsyncRequestAnnotation(CtClass ct, CtMethod ctMethod) {
        Annotation annotation = getMethodAnnotation(ct, ctMethod, com.finhub.framework.web.async.annotation.AsyncRequest.class);

        long timeout = ifNullForDefault(annotation.getMemberValue("timeout"), WebAsyncHelper.DEFAULT_TIMEOUT);
        TimeUnit timeUnit = ifNullForDefault(annotation.getMemberValue("timeUnit"), WebAsyncHelper.DEFAULT_TIME_UNIT);

        String timeoutMsg = ifNullForDefault(annotation.getMemberValue("msg"), WebAsyncHelper.DEFAULT_TIMEOUT_MESSAGE);
        int timeoutCode = ifNullForDefault(annotation.getMemberValue("code"), WebAsyncHelper.DEFAULT_TIMEOUT_CODE);

        Class<?> returnType =
            ifNullForDefault(annotation.getMemberValue("returnType"), WebAsyncHelper.DEFAULT_RETURN_TYPE);

        return AsyncRequest.builder()
            .code(timeoutCode)
            .msg(timeoutMsg)
            .returnType(returnType)
            .timeout(timeout)
            .timeUnit(timeUnit)
            .build();
    }

    /**
     * 重写路由方法，将方法注解去掉，并添加一个异步返回对象
     *
     * @param ct         重写类文件
     * @param ctMethod   重写方法
     * @throws javassist.CannotCompileException 无法编译异常
     */
    private static void rewriteMethodDefinition(CtClass ct, ConstPool constPool, CtMethod ctMethod)
        throws CannotCompileException {

        //复制原方法，丢弃注解
        CtMethod reCtMethod =
            CtNewMethod.copy(ctMethod, StringUtils.appender(ctMethod.getName(), PROXY_TARGET_METHOD_SUFFIX), ct, null);
        //添加注解
        AnnotationsAttribute reAnnotations = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation asyncProxy = new Annotation(AsyncProxy.class.getTypeName(), constPool);
        reAnnotations.addAnnotation(asyncProxy);
        reCtMethod.getMethodInfo().addAttribute(reAnnotations);
        //类添加方法
        ct.addMethod(reCtMethod);
    }

    /**
     * 写入代理方法
     *
     * @param ct        修改class文件
     * @param constPool 常量池
     * @param ctMethod  待修改方法
     * @throws javassist.NotFoundException      参数类型无法找到
     * @throws ClassNotFoundException           class无法找到
     * @throws javassist.CannotCompileException 无法编译异常
     */
    private static void writeMethodProxyDefinition(ClassPool classPool, CtClass ct, ConstPool constPool,
        CtMethod ctMethod, AsyncRequest requestProperties)
        throws NotFoundException, ClassNotFoundException, CannotCompileException {

        CtClass returnType = classPool.get(requestProperties.getReturnType().getName());

        //复制原方法，并替换返回值类型
        CtMethod ctMethodProxy = new CtMethod(returnType, ctMethod.getName(), ctMethod.getParameterTypes(), ct);

        //设置访问权限
        ctMethodProxy.setModifiers(AccessFlag.PUBLIC);

        doSetMethodAnnotations(constPool, ctMethod, ctMethodProxy);

        doSetParametersAnnotations(constPool, ctMethod, ctMethodProxy);

        chooseSetProxyMethodBody(ctMethodProxy, returnType, requestProperties);
        //删除原方法
        ct.removeMethod(ctMethod);
        //新增代理方法
        ct.addMethod(ctMethodProxy);

    }



    /**
     * 将原方法注解设置到代理方法上。
     *
     * @param constPool     常量池
     * @param ctMethod      目标方法
     * @param ctMethodProxy 代理方法
     * @throws ClassNotFoundException   类无法找到异常
     */
    private static void doSetMethodAnnotations(ConstPool constPool, CtMethod ctMethod, CtMethod ctMethodProxy)
        throws ClassNotFoundException {

        AnnotationsAttribute attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Object[] annotationsProxy = ctMethod.getAnnotations();
        for (Object proxy : annotationsProxy) {
            AnnotationImpl handler = ClassUtils.getPropertyValue(proxy, "h", AnnotationImpl.class);
            Annotation annotation = handler.getAnnotation();
            attribute.addAnnotation(annotation);
        }
        ctMethodProxy.getMethodInfo().addAttribute(attribute);

    }


    /**
     * 将原方法参数注解设置到代理方法参数上
     *
     * @param constPool     常量池
     * @param ctMethod      目标方法
     * @param ctMethodProxy 代理方法
     */
    private static void doSetParametersAnnotations(ConstPool constPool, CtMethod ctMethod, CtMethod ctMethodProxy) {

        Object[][] parameterAnnotations;
        try {
            parameterAnnotations = ctMethod.getParameterAnnotations();
        } catch (Exception e) {
            throw new ArgumentException(
                String.format("get method:%s parameter annotations error.", ctMethod.getLongName()), e);
        }

        if (ArrayUtil.isEmpty(parameterAnnotations)) {
            return;
        }
        ParameterAnnotationsAttribute parameterAnnotationsAttribute =
            new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        Annotation[][] newParameterAnnotations = new Annotation[parameterAnnotations.length][];
        int k = 0;
        for (Object[] objects : parameterAnnotations) {
            Annotation[] annotations = new Annotation[objects.length];
            int j = 0;
            for (Object proxy : objects) {
                AnnotationImpl handler = ClassUtils.getPropertyValue(proxy, "h", AnnotationImpl.class);
                Annotation annotation = handler.getAnnotation();
                annotations[j++] = annotation;
            }
            newParameterAnnotations[k++] = annotations;
        }
        parameterAnnotationsAttribute.setAnnotations(newParameterAnnotations);
        ctMethodProxy.getMethodInfo().addAttribute(parameterAnnotationsAttribute);

    }

    /**
     * 选择设置代理方法结构体
     *
     * @param ctMethodProxy 代理方法
     * @param returnType    返回值属性
     * @throws CannotCompileException 编译错误异常
     */
    private static void chooseSetProxyMethodBody(CtMethod ctMethodProxy, CtClass returnType,
        AsyncRequest requestProperties) throws CannotCompileException {

        WebAsyncModeEnum mode =
            WebAsyncModeEnum.getModeByReturnTypeClassName(returnType.getName());
        switch (mode) {
            case DEFERRED_RESULT:
                doSetMethodBodyForDeferredResult(ctMethodProxy, requestProperties);
                break;
            case CALLABLE:
                doSetMethodBodyForCallable(ctMethodProxy);
                break;
            default:
                doSetMethodBodyForWebAsyncTask(ctMethodProxy, requestProperties);
                break;
        }
    }

    /**
     * 设置代理方法执行body
     *
     * @param ctMethodProxy 代理方法
     * @throws CannotCompileException body无法编译异常
     */
    private static void doSetMethodBodyForWebAsyncTask(CtMethod ctMethodProxy, AsyncRequest requestProperties)
        throws CannotCompileException {

        StringBuilder builder = new StringBuilder("{\n");
        builder.append(
            "org.springframework.web.context.request.ServletRequestAttributes request$ = (org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();");
        builder.append(
            "org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(request$, true);\n");

        builder.append("javax.servlet.http.HttpServletRequest servletRequest$= request$.getRequest();\n");
        builder.append(
            "com.finhub.framework.web.async.entity.TimeoutResult timeoutResult$ = new com.finhub.framework.web.async.entity.TimeoutResult(").append(
            requestProperties.getCode()).append(", \"").append(
            requestProperties.getMsg()).append("\");\n");

        builder.append("servletRequest$.setAttribute(\"").append(REQUEST_TIMEOUT_RESULT_ATTRIBUTE).append(
            "\", ").append("timeoutResult$")
            .append(");\n");

        builder.append(
            "com.finhub.framework.web.async.task.AsyncCallable asyncCallable$ = new com.finhub.framework.web.async.task.AsyncCallable((java.lang.Object)$0,\"")
            .append(StringUtils.appender(ctMethodProxy.getName(), PROXY_TARGET_METHOD_SUFFIX)).append("\",")
            .append("$args);\n");

        builder.append(
            "org.springframework.web.context.request.async.WebAsyncTask webAsyncTask$ = new org.springframework.web.context.request.async.WebAsyncTask(")
            .append(requestProperties.getTimeUnit().toSeconds(requestProperties.getTimeout()))
            .append("L, (java.util.concurrent.Callable)asyncCallable$);\n");

        builder.append("return webAsyncTask$;\n");

        builder.append("}");

        ctMethodProxy.setBody(builder.toString());

    }

    /**
     * 设置代理方法执行body
     *
     * @param ctMethodProxy     代理方法
     * @throws CannotCompileException body无法编译异常
     */
    private static void doSetMethodBodyForCallable(CtMethod ctMethodProxy) throws CannotCompileException {
        StringBuilder builder = new StringBuilder("{\n");
        builder.append("org.springframework.web.context.request.ServletRequestAttributes request$ = (org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();");
        builder.append("org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(request$, true);\n");

        builder.append("return new com.finhub.framework.web.async.task.AsyncCallable((java.lang.Object)$0,\"")
            .append(StringUtils.appender(ctMethodProxy.getName(), PROXY_TARGET_METHOD_SUFFIX)).append("\",")
            .append("$args);\n");

        builder.append("}");

        ctMethodProxy.setBody(builder.toString());

    }


    /**
     * 设置代理方法执行body
     *
     * @param ctMethodProxy 代理方法
     * @throws CannotCompileException body无法编译异常
     */
    private static void doSetMethodBodyForDeferredResult(CtMethod ctMethodProxy,
        AsyncRequest requestProperties) throws CannotCompileException {

        StringBuilder builder = new StringBuilder("{\n");
        builder.append(
            "org.springframework.web.context.request.async.DeferredResult _deferredResult$ = new org.springframework.web.context.request.async.DeferredResult(java.lang.Long.valueOf(")
            .append(requestProperties.getTimeUnit().toSeconds(requestProperties.getTimeout())).append(
            "L), (java.lang.Object)com.finhub.framework.web.vo.Result.instanceFail(java.lang.Integer.valueOf(").append(
            requestProperties.getCode()).append("), \"").append(requestProperties.getMsg()).append("\"));\n");
        builder.append(
            "org.springframework.web.context.request.ServletRequestAttributes _servletRequestAttributes$ = (org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();\n");
        builder.append(
            "org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(_servletRequestAttributes$,true);\n");
        builder.append(
            "com.finhub.framework.web.async.WebAsyncTaskThreadPool.getSingleton().submit(new com.finhub.framework.web.async.task.AsyncReferredCallable((java.lang.Object)$0,")
            .append("_deferredResult$, ")
            .append("\"").append(StringUtils.appender(ctMethodProxy.getName(), PROXY_TARGET_METHOD_SUFFIX)).append("\",")
            .append("$args));\n");

        builder.append("return _deferredResult$;\n");

        builder.append("}");
        ctMethodProxy.setBody(builder.toString());

    }


    /**
     * if annotation property is null then set default value
     *
     * @param memberValue   annotation property
     * @param defaultValue  default value
     * @param <T>           value class type
     * @return              annotation property value
     */
    private static <T> T ifNullForDefault(MemberValue memberValue,T defaultValue) {
        if (memberValue == null) {
            return defaultValue;
        }

        if (memberValue instanceof StringMemberValue) {
            return (T) ((StringMemberValue) memberValue).getValue();
        }

        if (memberValue instanceof IntegerMemberValue) {
            return (T) Integer.valueOf(((IntegerMemberValue) memberValue).getValue());
        }

        if (memberValue instanceof LongMemberValue) {
            return (T) Long.valueOf(((LongMemberValue) memberValue).getValue());
        }

        if (memberValue instanceof ClassMemberValue) {
            try {
                return (T) Class.forName(((ClassMemberValue) memberValue).getValue());
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("FINHUB-WEB-ASYNC parse @AsyncRequest returnType class fail:", e);
            }
        }

        if (memberValue instanceof EnumMemberValue) {
            return (T) TimeUnit.valueOf(((EnumMemberValue) memberValue).getValue());
        }

        throw new UnsupportedOperationException("this operate not supported");
    }


    /**
     * 获取异步方法超时时间 优先级 方法级别>类级别
     *
     * @param ct            解析类文件
     * @param ctMethodProxy 代理方法
     * @return              超时时间
     */
    private static Annotation getMethodAnnotation(CtClass ct, CtMethod ctMethodProxy,Class<? extends java.lang.annotation.Annotation> annotationClass) {

        Object annotation;
        try {
            annotation = ctMethodProxy.getAnnotation(annotationClass);
        } catch (ClassNotFoundException e) {
            throw new ArgumentException(
                String.format("get method:%s. %s annotation error", ctMethodProxy.getLongName(), annotationClass), e);
        }
        if (annotation != null) {
            return doGetAnnotation(annotation, "method", ct.getName(), ctMethodProxy.getName(),
                annotationClass.getName());
        }

        try {
            annotation = ct.getAnnotation(annotationClass);
        } catch (ClassNotFoundException e) {
            throw new ArgumentException(
                String.format("get class:%s. %s annotation error", ctMethodProxy.getLongName(), annotationClass), e);
        }

        return doGetAnnotation(annotation, "class", ct.getName(), ctMethodProxy.getName(),
            annotationClass.getName());
    }


    /**
     * 获取注解timeout属性
     *
     * @param annotation    目标注解
     * @param logAppend     日志追加属性
     * @return              超时时间
     */
    private static Annotation doGetAnnotation(Object annotation, String... logAppend) {

        if (annotation == null) {
            throw new ArgumentException(
                String.format("get {}:%s.%s. doGetMethodAsyncTimeout method parameter [annotation] must not null",
                    logAppend));
        }

        final AnnotationImpl targetAnnotation = doGetProxyAnnotationProperty(annotation);
        if (targetAnnotation == null) {
            throw new ArgumentException(
                String.format("get {}:%s.%s. Proxy target [%s.%s] annotation error", logAppend));
        }

        return targetAnnotation.getAnnotation();

    }


    /**
     * 获取注解实现
     *
     * @param annotation    注解代理
     * @return              注解实现类
     */
    private static AnnotationImpl doGetProxyAnnotationProperty(Object annotation) {

        if (annotation == null) {
            return null;
        }

        if (!(annotation instanceof Proxy)) {
            return null;
        }

        return ClassUtils.getPropertyValue(annotation, "h", AnnotationImpl.class);
    }


    @SuppressWarnings("all")
    public static void main(String[] args)
        throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException,
        InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException,
        InterruptedException {
        Class<DeferredResult> reTypeCl = DeferredResult.class;

        Class<?> aClass = assistClass("com.finhub.framework.web.async.AsyncControllerTest");

        final Object o = aClass.newInstance();

        Method method = aClass.getMethod("sync1", Integer.class);
        //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
        final Object invoke = method.invoke(o, 10);
        log.info("over");
        TimeUnit.SECONDS.sleep(6);

    }

}

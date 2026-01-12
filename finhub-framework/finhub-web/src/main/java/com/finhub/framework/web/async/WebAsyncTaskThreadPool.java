package com.finhub.framework.web.async;

import com.finhub.framework.web.property.AsyncProperties;

import lombok.experimental.UtilityClass;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : liuwei
 * @date : 2022/1/17
 * @desc :  web请求业务处理线程池，方法显示没有被调用，实际是有javassist技术动态插入字节码中, 不可被删除
 * {@link WebAsyncAssistSupport} #doSetMethodBody()
 *
 */
@UtilityClass
public final class WebAsyncTaskThreadPool {

    /**
     * 私有线程池
     */
    @SuppressWarnings("java:S3077")
    private static volatile ThreadPoolTaskExecutor executor;

    /**
     * 获取单例线程池 方法显示没有被调用，实际是有javassist技术动态插入字节码中, 不可被删除
     *
     * @return  单例线程池
     */
    public static AsyncTaskExecutor getSingleton() {
        if (executor == null) {
            synchronized (WebAsyncTaskThreadPool.class) {
                if (executor == null) {
                    AsyncProperties config = AsyncProperties.me();
                    int queueCapacity = config.getQueueCapacity();
                    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
                    taskExecutor.setCorePoolSize(config.getCorePoolSize());
                    taskExecutor.setMaxPoolSize(config.getMaximumPoolSize());
                    taskExecutor.setKeepAliveSeconds((int) config.getUnit().toSeconds(config.getKeepAliveTime()));
                    taskExecutor.setQueueCapacity(queueCapacity);
                    taskExecutor.setThreadFactory(new AsyncMvcThreadFactory(config.getThreadNamePrefix()));
                    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                    taskExecutor.initialize();
                    executor = taskExecutor;
                }
            }
        }
        return executor;
    }

    /**
     * 自定义线程工厂
     */
    private static class AsyncMvcThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        AsyncMvcThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        /**
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         *
         * @param runnable a runnable to be executed by new thread instance
         * @return constructed thread, or {@code null} if the request to
         *         create a thread is rejected
         */
        @Override
        public Thread newThread(Runnable runnable) {
            Thread t =
                new Thread(Thread.currentThread().getThreadGroup(), runnable, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}

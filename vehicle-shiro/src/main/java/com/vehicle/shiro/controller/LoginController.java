package com.vehicle.shiro.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.exception.MessageException;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;
import com.finhub.framework.web.captcha.CaptchaCacheManager;
import com.finhub.framework.web.controller.ControllerSupport;
import com.finhub.framework.web.csrf.CsrfToken;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.MessageResult;

import static com.finhub.framework.web.constant.WebConstants.REDIRECT_HOME;
import static com.finhub.framework.web.constant.WebConstants.REDIRECT_LOGIN;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 登录退出
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@ApiIgnore
@Slf4j
@RestController
public class LoginController extends ControllerSupport {

    /**
     * S_GET 登录 shiro 写法
     */
    @CsrfToken(create = true)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return REDIRECT_HOME;
        }
        return REDIRECT_LOGIN;
    }

    /**
     * POST 登录 shiro 写法
     */
    @CsrfToken(remove = true)
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ItemResult<LoginResVO> login(LoginReqVO loginDTO) {

        boolean isCheckSuccess = CaptchaCacheManager.me().validate(request, response, loginDTO.getCaptcha());
        if (!isCheckSuccess) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, "验证码错误!");
        }

        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());

        // 设置记住密码
        boolean isRememberMe = true;
        if (Func.isNotEmpty(loginDTO.getRememberMe())) {
            isRememberMe = 1 == loginDTO.getRememberMe();
        }
        token.setRememberMe(isRememberMe);

        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            log.error("unknown account.", e);
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "账号不存在!");
        } catch (DisabledAccountException e) {
            log.error("disabled account.", e);
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "账号未启用!");
        } catch (IncorrectCredentialsException e) {
            log.error("incorrect credentials.", e);
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "密码错误!");
        } catch (AuthenticationException e) {
            log.error("authentication fail.", e);
            if (e.getCause() != null) {
                if (e.getCause() instanceof MessageException) {
                    MessageException me = (MessageException) e.getCause();
                    MessageResponseEnum.COMMON_ERROR.newException(me.getCodeEnum().getCode(), me.getMessage());
                } else {
                    MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, e.getCause().getMessage());
                }
            } else {
                MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, e.getMessage());
            }
        } catch (Throwable e) {
            log.error("other error.", e);
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, e.getMessage());
        }

        // 返回当前登录用户信息
        CurrentUser currentUser = (CurrentUser) SecurityUtils.getSubject().getPrincipal();
        LoginResVO loginResVO = new LoginResVO();
        loginResVO.setAccess(currentUser.getName());
        loginResVO.setAvatar("https://avatars0.githubusercontent.com/u/20942571?s=460&v=4");
        loginResVO.setToken(currentUser.getName());
        loginResVO.setName(currentUser.getName());
        return responseItem(ResponseCodeEnum.SUCCESS, loginResVO);
    }


    /**
     * POST 登录 shiro 写法
     */
    @RequestMapping(value = "/userInfo", method = {RequestMethod.POST})
    public ItemResult<LoginResVO> userInfo() {
        // 返回当前登录用户信息
        CurrentUser currentUser = (CurrentUser) SecurityUtils.getSubject().getPrincipal();
        LoginResVO loginResVO = new LoginResVO();
        loginResVO.setAccess(currentUser.getName());
        loginResVO.setAvatar("https://avatars0.githubusercontent.com/u/20942571?s=460&v=4");
        loginResVO.setToken(currentUser.getName());
        loginResVO.setName(currentUser.getName());
        return responseItem(ResponseCodeEnum.SUCCESS, loginResVO);
    }

    /**
     * 未授权
     */
    @RequestMapping(value = "/unauth", method = {RequestMethod.GET})
    public String unauth() {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return REDIRECT_LOGIN;
        }
        return "unauth";
    }

    @CsrfToken(create = true)
    @RequestMapping("/captcha.jpg")
    public void captcha() {
        CaptchaCacheManager.me().generate(request, response);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public MessageResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return responseMessage(ResponseCodeEnum.SUCCESS);
    }
}

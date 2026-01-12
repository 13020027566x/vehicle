package com.vehicle.shiro.realm;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;
import com.finhub.framework.shiro.authc.ShiroByteSource;

import cn.hutool.core.convert.Convert;
import com.vehicle.service.dictionary.DictionaryUtils;
import com.vehicle.service.resource.ResourceService;
import com.vehicle.service.resource.dto.ResourceDTO;
import com.vehicle.service.roleresource.RoleResourceService;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.user.UserService;
import com.vehicle.service.user.dto.UserDTO;
import com.vehicle.service.userrole.UserRoleService;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.util.Deque;
import java.util.List;
import java.util.Set;

/**
 * shiro权限认证
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class ShiroDbRealm extends AuthorizingRealm implements InitializingBean {

    private CacheManager kickoutCacheManager;

    private Cache<String, Deque<Serializable>> kickoutCache;

    private String  kickoutCacheName = "shiro-kickout-session";

    public ShiroDbRealm(final CacheManager cacheManager, final CredentialsMatcher matcher) {
        super(cacheManager, matcher);
        this.kickoutCacheManager = cacheManager;
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        // 将父类方法改写为 Public 的，从而可以从外部调用
        return super.getAuthorizationInfo(principals);
    }

    /**
     * 权限判断
     *
     * @param permission
     * @param info
     * @return
     */
    @Override
    protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
        return super.isPermitted(permission, info);
    }

    /**
     * 授权 CacheKey
     *
     * @param principals
     * @return
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return super.getAuthorizationCacheKey(principals);
    }

    /**
     * 登录后：认证 CacheKey
     *
     * @param principals
     * @return
     */
    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        return super.getAuthenticationCacheKey(principals);
    }

    /**
     * 登录前：认证 CacheKey
     *
     * @param token
     * @return
     */
    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        return super.getAuthenticationCacheKey(token);
    }

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserDTO userDTO = new UserDTO();
        // 此处token.getUsername()对应的user.name而非user.loginName
        userDTO.setLoginName(token.getUsername());
        UserDTO user = UserService.me().findOne(userDTO);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException();
        }

        // 账号未启用
        if (user.getStatusVal() == DictionaryUtils.YHZT_TY_VAL().intValue()) {
            throw new DisabledAccountException();
        }

        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(user.getId());
        currentUser.setName(user.getName());
        currentUser.setLoginName(token.getUsername());
        currentUser.setCode(user.getCode());
        currentUser.setIsTest(user.getIsTest());

        UserRoleDTO paramDTO = new UserRoleDTO();
        paramDTO.setUserId(currentUser.getId());

        List<UserRoleDTO> userRoleDTOs = UserRoleService.me().find(paramDTO);
        if (Func.isEmpty(userRoleDTOs)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "账号未分配角色");
        }

        currentUser.setRoleId(userRoleDTOs.get(0).getRoleId());
        currentUser.setRoleName(userRoleDTOs.get(0).getRoleName());
        currentUser.setRoleCode(userRoleDTOs.get(0).getRoleCode());

        // 认证缓存信息
        return new SimpleAuthenticationInfo(currentUser, user.getPwd(), ShiroByteSource.of(Func.isEmpty(user.getSalt()) ? StrConstants.S_EMPTY : user.getSalt()), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        CurrentUser currentUser = (CurrentUser) principals.getPrimaryPrincipal();

        UserRoleDTO paramDTO = new UserRoleDTO();
        paramDTO.setUserId(currentUser.getId());

        List<UserRoleDTO> userRoleDTOs = UserRoleService.me().find(paramDTO);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (Func.isEmpty(userRoleDTOs)) {
            return info;
        }

        List<String> roleIdList = com.google.common.collect.Lists.newArrayList();
        Set<String> roleCodeSet = com.google.common.collect.Sets.newHashSet();
        for (UserRoleDTO userRoleDTO : userRoleDTOs) {
            roleIdList.add(userRoleDTO.getRoleId());
            roleCodeSet.add(userRoleDTO.getRoleCode());
        }

        Set<String> permissionCodeSet = com.google.common.collect.Sets.newHashSet();
        List<RoleResourceDTO> roleResourceDTOList = RoleResourceService.me().findByRoleIds(roleIdList);
        for (RoleResourceDTO roleResourceDTO : roleResourceDTOList) {
            ResourceDTO resourceDTO = ResourceService.me().findById(roleResourceDTO.getResourceId());
            if (Func.isNotEmpty(resourceDTO) && !Convert.toBool(resourceDTO.getIsMenu())) {
                permissionCodeSet.add(roleResourceDTO.getResourceCode());
            }
        }

        info.setRoles(roleCodeSet);
        info.addStringPermissions(permissionCodeSet);

        return info;
    }

    @Override
    public void onLogout(final PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        CurrentUser user = (CurrentUser) principals.getPrimaryPrincipal();
        removeUserCache(user);
    }

    /**
     * 清除认证缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清除权限缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除用户缓存
     *
     * @param user
     */
    public void removeUserCache(final CurrentUser user) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(user.getLoginName(), super.getName());
        if (Func.isNotEmpty(this.kickoutCache)) {
            Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
            Deque<Serializable> deque = this.kickoutCache.get(user.getLoginName());
            // 判断队列中是否存在sessionId, 存在则删除，更新缓存
            if (Func.isNotEmpty(deque) && deque.contains(sessionId) && deque.remove(sessionId)) {
                this.kickoutCache.put(user.getLoginName(), deque);
            }
        }
        super.clearCachedAuthenticationInfo(principals);
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void afterPropertiesSet() {
        if (Func.isNotEmpty(this.kickoutCacheManager)) {
            this.kickoutCache = this.kickoutCacheManager.getCache(kickoutCacheName);
        }
    }
}

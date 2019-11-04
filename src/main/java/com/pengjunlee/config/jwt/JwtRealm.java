package com.pengjunlee.config.jwt;

import com.pengjunlee.config.shiro.ShiroRealm;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.ApplicationContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * JwtRealm 只负责校验 JwtToken
 *
 * @author pengjunlee
 * @create 2019-09-03 10:34
 */
public class JwtRealm extends AuthorizingRealm {

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authcToken;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String username = jwtToken.getPrincipal().toString();
        // 查询数据库获取用户信息
        UserService userService = ApplicationContextUtil.getBean(UserService.class);
        UserEntity user = userService.getByName(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked()) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, username, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        UserEntity currentUser = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        // 查询数据库，获取用户的角色信息
        Set<String> roles = new HashSet<String>(Arrays.asList(currentUser.getRoles().split(",")));
        // 查询数据库，获取用户的权限信息
        //Set<String> perms = ShiroRealm.permMap.get(currentUser.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        //info.setStringPermissions(perms);
        return info;
    }
}

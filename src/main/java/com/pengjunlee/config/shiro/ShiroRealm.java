package com.pengjunlee.config.shiro;

import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.ApplicationContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 同时开启身份验证和权限验证，需要继承 AuthorizingRealm
 *
 * @author pengjunlee
 * @create 2019-09-03 9:29
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 查询数据库，将获取到的用户安全数据封装返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从 AuthenticationToken 中获取当前用户
        String username = (String) token.getPrincipal();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
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

        // 使用用户名作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象。
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
                getName());
        return info;
    }

    /**
     * 查询数据库，将获取到的用户的角色及权限信息返回
     */
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

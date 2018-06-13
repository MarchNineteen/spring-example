package com.wyb.shiro.realm;

import com.wyb.shiro.config.JWTConfig;
import com.wyb.shiro.dao.dto.UserDto;
import com.wyb.shiro.service.MenuService;
import com.wyb.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Resource
    private JWTConfig jwtConfig;

    /**
     * 授权方法，为当前登录的Subject授予角色和权限
     *
     * @see：本例中该方法的调用时机为需授权资源被访问时
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（系统提供三种cache：内存，ehcache ，redis，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo+" + principalCollection.toString());
//        UserDo user = userService.getByUserName((String) principalCollection.getPrimaryPrincipal());
//        if (null != user) {
//            //把principals放session中 key=userId value=principals
//            SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()), SecurityUtils.getSubject().getPrincipals());
//
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            //赋予角色
//            for (RoleDo userRole : user.getRoles()) {
//                info.addRole(userRole.getRoleName());
//
//            }
//            //赋予权限
////            menuService.getByRoleIds(user.getRoles());
//            //赋予权限
//            for (MenuDo menu : menuService.getByUserId(user.getId())) {
////            if(StringUtils.isNotBlank(permission.getPermCode()))
//                info.addStringPermission(menu.getMenuName());
//            }
//
//            //设置登录次数、时间
////        userService.updateUserLogin(user);
//            return info;
//        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken token = (UserToken) authenticationToken;
        log.info("验证当前Subject时获取到token为：" + token.toString());
        String username = (String) authenticationToken.getPrincipal();
        log.info("验证当前Subject时获取到用户名为：" + username);

//        Long uid = JWTUtil.getUid(token);
//        log.info(String.valueOf(uid));

        UserDto userDto = userService.getByUserName(username);
        if (userDto == null) {
            throw new UnknownAccountException("用户不存在");
        }
//        if (!JWTUtil.verify(token, uid, userDto.getPassword(), jwtConfig.getIssure())) {
//            throw new AuthenticationException("Username or password error");
//        }
        return new SimpleAuthenticationInfo(userDto.getUsername(), userDto.getPassword(), ByteSource.Util.bytes(userDto.getSalt()),
                getName());
    }
}

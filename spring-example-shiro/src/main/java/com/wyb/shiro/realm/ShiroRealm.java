package com.wyb.shiro.realm;

import com.wyb.shiro.dao.model.MenuDo;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.MenuService;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kunzite
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 授权方法，为当前登录的Subject授予角色和权限
     *
     * @see：本例中该方法的调用时机为需授权资源被访问时
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（系统提供三种cache：内存，ehcache ，redis，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo+" + principalCollection.toString());
        UserDo user = userService.getByUserName((String) principalCollection.getPrimaryPrincipal());
        if (null != user) {
            //把principals放session中 key=userId value=principals
            SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()), SecurityUtils.getSubject().getPrincipals());

            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //赋予角色
            for (RoleDo userRole : user.getRoles()) {
                info.addRole(userRole.getRoleName());

            }
            //赋予权限
//            menuService.getByRoleIds(user.getRoles());
            //赋予权限
            for (MenuDo menu : menuService.getByUserId(user.getId())) {
//            if(StringUtils.isNotBlank(permission.getPermCode()))
                info.addStringPermission(menu.getMenuName());
            }

            //设置登录次数、时间
//        userService.updateUserLogin(user);
            return info;
        }
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
        log.info("doGetAuthenticationInfo +" + authenticationToken.toString());
        String token = (String) authenticationToken.getCredentials();
        Integer uid = JWTUtil.getUid(token);
        log.info(String.valueOf(uid));

        UserDo user = userService.getById(uid);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");

        }
//        if (!JWTUtil.verify(token, uid, user.getPassword())) {
//            throw new AuthenticationException("Username or password error");
//        }
        //设置用户session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", user);
        return new SimpleAuthenticationInfo(token, token, getName());
//        return null;
    }
}

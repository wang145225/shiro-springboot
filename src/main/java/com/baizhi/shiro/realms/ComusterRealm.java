package com.baizhi.shiro.realms;

import com.baizhi.pojo.User;
import com.baizhi.service.UserService;
import com.baizhi.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * 自定义的realm
 */
public class ComusterRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        //获取身份信息
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();

//        //根据主身份获取角色信息和权限
//        if ("wang".equals(primaryPrincipal)){
//            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
//
//            //设置用户的角色
//            simpleAuthorizationInfo.addRole("user");
//
//            //用户是否有对资源的访问权限
//            simpleAuthorizationInfo.addStringPermission("user:*:*");
//
//            return  simpleAuthorizationInfo;
//
//        }
        //通过工厂对象获得userservice
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findRolesByUserName(primaryPrincipal);
        if(user!=null){
           SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName());
            });

            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //用户身份信息
        String principal = (String) token.getPrincipal();
        //通过工厂对象获得userservice
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");

        User user = userService.findUserByUserName(principal);

        if(user!=null){
                //第一个事用户名   第二个是密码   第三个是随机盐 必须用shior自带的随机盐解密  第四个是身份名称
              return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }


        return null;
    }
}

package com.baizhi.config;

import com.baizhi.shiro.realms.ComusterRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 *
 * 整合shiro配置类
 */
@Configuration
public class ShiroConfig  {

    //创建shirofilter拦截  负责拦截请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //创建map集合指定拦截的具体页面
        HashMap<String,String> map=new HashMap<>();

        //公共资源
        map.put("/user/login","anon");  //公共访问资源，就是不走拦截
        map.put("/register.jsp","anon");
        map.put("/user/register","anon");
        //受限资源
        map.put("/index.jsp","authc");  //authc  请求这个资源需要认证和授权


        //默认拦截之后的页面
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");  //可以不设置  他自己识别去这个页面

        //设置拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return  shiroFilterFactoryBean;

    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }



    @Bean
    public Realm getRealm(){

        ComusterRealm realm=new ComusterRealm();
        //修改凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //设置加密算法 为md5
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //设置hash散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }
}

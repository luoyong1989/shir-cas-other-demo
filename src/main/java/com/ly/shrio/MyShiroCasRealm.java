package com.ly.shrio;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;

public class MyShiroCasRealm extends CasRealm{

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @PostConstruct
    public void initProperty(){
//      setDefaultRoles("ROLE_USER");
        setCasServerUrlPrefix(ShiroCasConfiguration.casServerUrlPrefix);
        // 客户端回调地址
        setCasService(ShiroCasConfiguration.shiroServerUrlPrefix + ShiroCasConfiguration.casFilterUrlPattern);
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限 
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see ：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        Iterator iterator = principalCollection.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if (next instanceof Map){
                Map<String,String>  map = (Map<String, String>) next;
                System.out.println(map);
            }
        }
        //到数据库查是否有此对象
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String> set = new HashSet<>();
        set.add("admin");
        info.setRoles(set);
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return info;
    }

}
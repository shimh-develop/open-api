package com.shimh.config;


import com.shimh.common.redis.RedisUtil;
import com.shimh.security.CustomizedModularRealmAuthenticator;
import com.shimh.security.JwtFactory;
import com.shimh.security.check.TokenCheckFilter;
import com.shimh.security.check.TokenCheckRealm;
import com.shimh.security.sign.TokenSignFilter;
import com.shimh.security.sign.TokenSignRealm;
import com.shimh.service.UserService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, TokenCheckFilter tokenCheckFilter) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("sign", new TokenSignFilter());
        filterMap.put("check", tokenCheckFilter);
        shiroFilterFactoryBean.setFilters(filterMap);


        // 配置映射路径
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put("/api/**", "anon");

        filterChainDefinitionMap.put("/auth/login", "sign");

        filterChainDefinitionMap.put("/**", "check");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public TokenCheckFilter tokenCheckFilter(AppProperties appProperties, JwtFactory jwtFactory, RedisUtil redisUtil) {
        return new TokenCheckFilter(appProperties, jwtFactory, redisUtil);
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public TokenSignRealm tokenSignRealm(UserService userService, HashedCredentialsMatcher credentialsMatcher) {
        TokenSignRealm tokenSignRealm = new TokenSignRealm(userService);
        tokenSignRealm.setCredentialsMatcher(credentialsMatcher);
        return tokenSignRealm;
    }

    @Bean
    public TokenCheckRealm tokenCheckRealm(UserService userService, JwtFactory jwtFactory, RedisUtil redisUtil) {
        TokenCheckRealm tokenCheckRealm = new TokenCheckRealm(userService, jwtFactory, redisUtil);
        return tokenCheckRealm;
    }

    @Bean
    public SecurityManager securityManager(List<Realm> realms) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(new CustomizedModularRealmAuthenticator());

        // 认证 授权
        securityManager.setRealms(realms);

        // 关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

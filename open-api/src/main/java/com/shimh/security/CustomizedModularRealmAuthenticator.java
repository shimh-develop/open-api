package com.shimh.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        assertRealmsConfigured();
        Collection<Realm> realms = getRealms();
        if (realms.size() == 1) {
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        } else {
            // 只要找到一个验证就行
            for (Realm realm: realms) {

                if(realm.supports(authenticationToken)) {
                    return doSingleRealmAuthentication(realm, authenticationToken);
                }
            }
            // 找不到 随便
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        }
    }
}

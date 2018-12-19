package com.shimh.security.sign;

import com.shimh.entity.User;
import com.shimh.security.check.JwtToken;
import com.shimh.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author: shimh
 * @create: 2018年11月
 *
 * 令牌签发 认证 and 授权
 **/
public class TokenSignRealm extends AuthorizingRealm {

    private UserService userService;

    public TokenSignRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Class<?> getAuthenticationTokenClass() {
        return UsernamePasswordToken.class;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        User user = userService.getByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );

        return authenticationInfo;
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }
}

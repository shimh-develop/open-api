package com.shimh.security.check;

import com.shimh.common.constant.Base;
import com.shimh.common.redis.RedisUtil;
import com.shimh.entity.User;
import com.shimh.security.JwtFactory;
import com.shimh.security.check.extractor.Extractor;
import com.shimh.security.check.extractor.OldTokenValueExtractor;
import com.shimh.service.UserService;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shimh
 * @create: 2018年12月
 *
 * 令牌验签 认证 and 授权
 **/
public class TokenCheckRealm extends AuthorizingRealm {

    private UserService userService;
    private JwtFactory jwtFactory;
    private RedisUtil redisUtil;
    private Extractor extractor;

    public TokenCheckRealm(UserService userService, JwtFactory jwtFactory, RedisUtil redisUtil, Extractor extractor) {
        this.userService = userService;
        this.jwtFactory = jwtFactory;
        this.redisUtil = redisUtil;
        this.extractor = extractor;
    }

    public TokenCheckRealm(UserService userService, JwtFactory jwtFactory, RedisUtil redisUtil) {
        this(userService, jwtFactory, redisUtil, new OldTokenValueExtractor());
    }
    public Class<?> getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwtToken = (String) token.getPrincipal();

        String username = null;
        try {
            username = jwtFactory.getUsernameFromToken(jwtToken);
        }catch (ExpiredJwtException e) {
            Object tokenValue = redisUtil.get(jwtToken);
            if(null != tokenValue) {
                username = extractor.extract(tokenValue.toString());
            }
            throw new ExpiredCredentialsException("Token过期", e);
        }catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            throw new CredentialsException("Token无效", e);
        }

        User user = userService.getByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                true,
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

        User user = (User)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();

        if (user.getAdmin()) {
            roles.add(Base.ADMIN_ROLE_CODE);
        } else {
            roles.add(Base.COMMON_ROLE_CODE);
        }

        info.setRoles(roles);

        return info;

    }
}
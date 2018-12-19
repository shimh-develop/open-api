package com.shimh.security.check;

import com.alibaba.fastjson.JSON;
import com.shimh.common.base.Result;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.redis.RedisUtil;
import com.shimh.config.AppProperties;
import com.shimh.security.JwtFactory;
import com.shimh.security.check.extractor.Extractor;
import com.shimh.security.check.extractor.OldTokenValueExtractor;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public class TokenCheckFilter extends AccessControlFilter {

    private static final Logger LOG = LoggerFactory.getLogger(TokenCheckFilter.class);

    private AppProperties appProperties;

    private RedisUtil redisUtil;

    private JwtFactory jwtFactory;

    private Extractor extractor;

    public TokenCheckFilter(AppProperties appProperties, JwtFactory jwtFactory, RedisUtil redisUtil) {
        this(appProperties, jwtFactory, redisUtil, new OldTokenValueExtractor());
    }

    public TokenCheckFilter(AppProperties appProperties, JwtFactory jwtFactory, RedisUtil redisUtil, Extractor extractor) {
        this.appProperties = appProperties;
        this.jwtFactory = jwtFactory;
        this.redisUtil = redisUtil;
        this.extractor = extractor;
    }


    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        String token = getHeaderToken(request);

        // 没有token 直接返回
        if(null == token || "".equals(token) || "undefined".equals(token)) {
            // write2Response(response, Result.error(ResultCode.USER_NOT_LOGGED_IN));
            return true;
        }

        JwtToken authToken = createToken(request, response);
        boolean tokenExpired = false;
        try {
            Subject subject = getSubject(request, response);
            subject.login(authToken);
        } catch (UnknownAccountException e) {
            LOG.error(e.getMessage(),e);
            write2Response(response, Result.error(ResultCode.USER_NOT_EXIST));
            return false;
        }catch (ExpiredCredentialsException e) {
            LOG.error(e.getMessage(),e);
            tokenExpired = true;
        } catch (CredentialsException e) {
            LOG.error(e.getMessage(),e);
            write2Response(response, Result.error(ResultCode.INVALID_TOKEN));
            return false;
        }

        boolean sessionNotTimeout = redisUtil.hasKey(token);
        if(!sessionNotTimeout) {
            write2Response(response, Result.error(ResultCode.SESSION_TIMEOUT));
            return false;
        }

        if(tokenExpired) {
            Object oldTokenValue = redisUtil.get(token);
            if(null == oldTokenValue || extractor.supports(oldTokenValue.toString())) {

            } else {
                String username = oldTokenValue.toString();
                String refreshToken = jwtFactory.generateToken(username);
                redisUtil.set(refreshToken, username, appProperties.getSecurity().getTimeout());
                redisUtil.set(token, extractor.generate(username), appProperties.getSecurity().getOldTokenAliveTime());

                HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.setHeader(Base.TOKEN_NAME, refreshToken);
            }

        } else {
            redisUtil.expire(token, appProperties.getSecurity().getTimeout());
        }


        return true;

    }

    private void write2Response(ServletResponse response, Result result) throws Exception{
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        JSON.writeJSONString(response.getWriter(), result);
    }

    /**
     * 基于 username and password 生成Token
     *
     * @param request
     * @param response
     * @return
     */
    protected JwtToken createToken(ServletRequest request, ServletResponse response) {
        String jwtToken = getHeaderToken(request);
        return new JwtToken(jwtToken);
    }

    protected String getHeaderToken(ServletRequest request) {
        return ((HttpServletRequest)request).getHeader(appProperties.getSecurity().getJwt().getHeader());

    }

}

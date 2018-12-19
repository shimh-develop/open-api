package com.shimh.security.sign;

import com.alibaba.fastjson.JSON;
import com.shimh.common.base.Result;
import com.shimh.common.constant.ResultCode;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author: shimh
 * @create: 2018年11月
 *
 * 令牌签发
 **/
public class TokenSignFilter extends AccessControlFilter {

    private static final Logger LOG = LoggerFactory.getLogger(TokenSignFilter.class);

    private static final String DEFAULT_USERNAME_PARAM = "username";
    private static final String DEFAULT_PASSWORD_PARAM = "password";


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

        // 已经认证过的放行
        if(null != getSubject(request, response) && getSubject(request, response).isAuthenticated()) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        AuthenticationToken token = createToken(request, response);
        Result r = new Result();
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);//认证
            return true;
        } catch (UnknownAccountException e) {
            LOG.error(e.getMessage(),e);
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        }catch (AuthenticationException e) {
            LOG.error(e.getMessage(),e);
            r.setResultCode(ResultCode.USER_LOGIN_ERROR);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            r.setResultCode(ResultCode.ERROR);
        }

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        JSON.writeJSONString(response.getWriter(), r);

        return false;

    }

    /**
     * 基于 username and password 生成Token
     *
     * @param request
     * @param response
     * @return
     */
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = request.getParameter(DEFAULT_USERNAME_PARAM);
        String password= request.getParameter(DEFAULT_PASSWORD_PARAM);
        return new UsernamePasswordToken(username, password);
    }
}

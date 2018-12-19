package com.shimh.security.check;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
public class JwtToken implements AuthenticationToken {

    private String jwtToken;


    public JwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    @Override
    public Object getPrincipal() {
        return this.jwtToken;
    }

    @Override
    public Object getCredentials() {
        return true;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

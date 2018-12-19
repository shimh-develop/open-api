package com.shimh.config;

import io.jsonwebtoken.Jwt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Security security = new Security();

    public static class Security {

        private int timeout;
        private int oldTokenAliveTime;

        private final Jwt jwt = new Jwt();

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public int getOldTokenAliveTime() {
            return oldTokenAliveTime;
        }

        public void setOldTokenAliveTime(int oldTokenAliveTime) {
            this.oldTokenAliveTime = oldTokenAliveTime;
        }

        public Jwt getJwt() {
            return jwt;
        }
    }


    public static class Jwt {

        private String header;

        /**
         * token 过期时间.
         */
        private int tokenExpirationTime;

        /**
         *  签名 key.
         */
        private String tokenSigningKey;


        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public Integer getTokenExpirationTime() {
            return tokenExpirationTime;
        }

        public void setTokenExpirationTime(int tokenExpirationTime) {
            this.tokenExpirationTime = tokenExpirationTime;
        }

        public String getTokenSigningKey() {
            return tokenSigningKey;
        }

        public void setTokenSigningKey(String tokenSigningKey) {
            this.tokenSigningKey = tokenSigningKey;
        }

    }


    public Security getSecurity() {
        return security;
    }
}

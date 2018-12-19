package com.shimh.security.check.extractor;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
public class OldTokenValueExtractor implements Extractor {

    public static String TOKEN_PREFIX = "oldToken:";

    @Override
    public String extract(String payload) {
        if(supports(payload)) {
            return payload.substring(TOKEN_PREFIX.length());
        }

        return payload;
    }

    @Override
    public String generate(String data) {
        return TOKEN_PREFIX + data;
    }

    @Override
    public boolean supports(String payload) {
        if(payload.startsWith(TOKEN_PREFIX)) {
            return true;
        }
        return false;
    }
}

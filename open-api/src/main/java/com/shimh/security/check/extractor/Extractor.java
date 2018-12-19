package com.shimh.security.check.extractor;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
public interface Extractor {

    String extract(String payload);

    String generate(String data);

    boolean supports(String payload);

}

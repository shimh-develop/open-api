package com.shimh.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.shimh.common.redis.FastJson2JsonRedisSerializer;
import com.shimh.common.redis.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        FastJson2JsonRedisSerializer objectSerializer = new FastJson2JsonRedisSerializer(Object.class);

        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(objectSerializer);
        template.setHashValueSerializer(objectSerializer);

        template.setEnableTransactionSupport(true);

        return template;
    }

}

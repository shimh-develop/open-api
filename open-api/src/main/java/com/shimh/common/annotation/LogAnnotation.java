package com.shimh.common.annotation;

import java.lang.annotation.*;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operation() default "";
}

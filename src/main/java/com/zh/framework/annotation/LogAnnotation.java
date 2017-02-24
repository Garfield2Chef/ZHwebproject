package com.zh.framework.annotation;

import java.lang.annotation.*;


/**
 * controller 层日志注解
 * Created by Mrkin on 2016/12/15.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    public  enum Type {web, app,service}

    String content() default "";

    String operation() default "";

    Type type() default Type.web;
}

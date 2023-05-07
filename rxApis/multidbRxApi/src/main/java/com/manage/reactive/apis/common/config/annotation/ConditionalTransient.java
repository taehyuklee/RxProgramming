package com.manage.reactive.apis.common.config.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD, METHOD, ANNOTATION_TYPE })
public @interface ConditionalTransient {

    //boolean condition() default true;

    String dbType();


}

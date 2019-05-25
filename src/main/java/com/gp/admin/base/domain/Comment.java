package com.gp.admin.base.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author wangjiehan
 *
 */
@Target({ElementType.TYPE,ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Comment {
	String value() default "";
}

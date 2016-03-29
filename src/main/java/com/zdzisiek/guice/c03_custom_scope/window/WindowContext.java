package com.zdzisiek.guice.c03_custom_scope.window;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, METHOD })
@Inherited
public @interface WindowContext {

}

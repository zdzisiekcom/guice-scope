package com.zdzisiek.guice.c03_custom_scope.window;

import com.google.inject.ScopeAnnotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Inherited
@ScopeAnnotation
public @interface WindowSingleton {

}
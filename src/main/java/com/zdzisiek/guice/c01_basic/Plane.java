package com.zdzisiek.guice.c01_basic;

import com.google.inject.BindingAnnotation;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Target({ FIELD, PARAMETER, METHOD })
@Retention(RUNTIME)
@BindingAnnotation
public @interface Plane {}
package com.zdzisiek.guice.c04_inject_annotations;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@BindingAnnotation
@Target({ FIELD }) @Retention(RUNTIME)
public @interface Message {
    String value();
}


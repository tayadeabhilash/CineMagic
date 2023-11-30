package com.scrumandcoke.movietheaterclub.annotation;

import com.scrumandcoke.movietheaterclub.enums.UserType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserTypesAllowed {
    UserType[] value();
}


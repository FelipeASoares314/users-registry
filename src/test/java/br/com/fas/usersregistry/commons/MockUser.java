package br.com.fas.usersregistry.commons;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value = "56962158076", password = "pass")
public @interface MockUser {}

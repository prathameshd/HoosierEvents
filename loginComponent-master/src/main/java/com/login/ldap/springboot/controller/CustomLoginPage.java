package com.login.ldap.springboot.controller;

import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
public class CustomLoginPage extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/forgotPassword").setViewName("forgotPassword");
        registry.addViewController("/forgotPassworderror").setViewName("forgotPassworderror");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}

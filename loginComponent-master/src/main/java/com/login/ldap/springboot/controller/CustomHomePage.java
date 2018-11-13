package com.login.ldap.springboot.controller;

import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
public class CustomHomePage extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/homepage").setViewName("homepage_new");
        registry.addViewController("/sign").setViewName("signup");
        registry.addViewController("/eventpage").setViewName("eventpage");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}

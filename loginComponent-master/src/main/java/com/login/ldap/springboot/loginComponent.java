package com.login.ldap.springboot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@SpringBootApplication
@EntityScan("com.login.ldap.model")
@EnableLdapRepositories(basePackages = "com.login.ldap.springboot.data.repository.**")
public class loginComponent {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(loginComponent.class, args);
    }

}

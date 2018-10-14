package com.login.ldap;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@SpringBootApplication
public class loginComponent {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(loginComponent.class, args);
    }

}

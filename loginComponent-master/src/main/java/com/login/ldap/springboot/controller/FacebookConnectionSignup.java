package com.login.ldap.springboot.controller;

import com.login.ldap.model.User;
import com.login.ldap.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;
//    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {




        return connection.getDisplayName();
    }
}
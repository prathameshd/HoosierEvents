package se.hoosierevents.project.springboot.springboot.controller;

import se.hoosierevents.project.springboot.springboot.data.service.UserService;
import se.hoosierevents.project.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {

        userService.create(connection.getDisplayName(),"12345");


        return connection.getDisplayName();
    }
}
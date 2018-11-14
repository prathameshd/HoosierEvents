package se.hoosierevents.project.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import se.hoosierevents.project.springboot.repository.UserRepository;

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
package se.hoosierevents.project.springboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Service
public class GetAuthDetails {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public String GetDetails(){

    return auth.getName();

    }
}

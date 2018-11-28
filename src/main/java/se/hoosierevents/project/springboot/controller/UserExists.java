package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.hoosierevents.project.springboot.repository.UserRepository;

@RestController
public class UserExists {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/userExists")
    public ResponseEntity<String> userExistsornot(String email){
    	
    	System.out.println("email" + email);

        if(userRepository.findByEmail(email)!= null) {
            return ResponseEntity.ok("found");
        }
        else
            return ResponseEntity.ok("nope");

    }


}

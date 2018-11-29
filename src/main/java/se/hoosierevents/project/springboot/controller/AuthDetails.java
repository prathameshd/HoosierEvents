package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.hoosierevents.project.springboot.repository.UserRepository;
import se.hoosierevents.project.springboot.service.GetAuthDetails;

@RestController
public class AuthDetails {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/getauth")
    public ResponseEntity<String> auth_det()
    {
        GetAuthDetails getAuthDetails = new GetAuthDetails();
        String name = getAuthDetails.GetDetails();
        return ResponseEntity.ok(name);
    }

    @RequestMapping(value = "/checkifexists")
    public ResponseEntity<String> checkifexists(String email)
    {
        System.out.println("email" + email);

        if(userRepository.findByEmail(email)!= null) {
            return ResponseEntity.ok("found");
        }
        else
            return ResponseEntity.ok("nope");
    }

}

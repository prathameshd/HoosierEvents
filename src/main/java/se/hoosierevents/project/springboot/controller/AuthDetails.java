package se.hoosierevents.project.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.hoosierevents.project.springboot.service.GetAuthDetails;

@RestController
public class AuthDetails {

    @RequestMapping(value = "/getauth")
    public ResponseEntity<String> auth_det()
    {
        GetAuthDetails getAuthDetails = new GetAuthDetails();
        String name = getAuthDetails.GetDetails();
        return ResponseEntity.ok(name);
    }

}

package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;
import se.hoosierevents.project.springboot.service.GetAuthDetails;

import javax.servlet.http.HttpSession;

import static se.hoosierevents.project.springboot.controller.Controller.USER_KEY;

@RestController
public class GetUserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/getusertype" , method = RequestMethod.GET)
    public ResponseEntity<User> GetUserType(HttpSession session)
    {
        User user = new User();
        if (null != session) {
			user = (User) session.getAttribute(USER_KEY);
		}
        return ResponseEntity.ok(user);

    }
}

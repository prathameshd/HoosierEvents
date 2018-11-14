package se.hoosierevents.project.springboot.springboot.controller;

import se.hoosierevents.project.springboot.springboot.data.service.LdapClient;
import se.hoosierevents.project.springboot.springboot.repository.UserRepository;
import se.hoosierevents.project.springboot.springboot.data.service.UserService;
import se.hoosierevents.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SignUp {

@Autowired
    UserRepository userRepository;

@Autowired
    UserService users;

@Autowired
LdapClient ldapClient;
//    @RequestMapping(value="/signup",method = RequestMethod.POST)
//    public String SignmeUp(@ModelAttribute("RegForm") RegForm regForm, BindingResult result) {
//        ModelAndView modelAndView = new ModelAndView();
//        if(result.hasErrors())
//        {
//            return "login";
//        }
//        System.out.println("I am here");
////        UserService users = new UserService();
////        users.create(username, password);
//
//return "hello";
//    }
    @RequestMapping(value = "/signup/me", method = RequestMethod.POST)
    public final RedirectView signMeUp(HttpServletRequest request, RegForm regForm, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address,@RequestParam("phone") String phone , @RequestParam("password") String password){
        User user = new User();

        user.setName(name);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setAddress(address);

        userRepository.save(user);

        System.out.println(name);
        System.out.println(password);

        users.create(email, password);
        ldapClient.create(email,password);

        return new RedirectView("/login");

    }
}

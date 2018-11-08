package com.login.ldap.springboot.controller;

import com.login.ldap.springboot.repository.UserRepository;
import com.login.ldap.springboot.data.service.UserService;
import com.login.ldap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SignUp {

@Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public final RedirectView signMeUp(HttpServletRequest request, RegForm regForm, @RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("phone") String phone , @RequestParam("password") String password, @RequestParam("userorg") String type_user){
        User user = new User();

        user.setName(name);
        user.setPhoneNumber(phone);
        user.setEmail(email);

        System.out.println(type_user);
        if(type_user.equals("user"))
            user.setUser_type("1");
        else
            user.setUser_type("2");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

        System.out.println(name);
        System.out.println(bCryptPasswordEncoder.encode(password));
//        UserService users = new UserService();
//       users.create(email, password);

        return new RedirectView("/login");

    }
}

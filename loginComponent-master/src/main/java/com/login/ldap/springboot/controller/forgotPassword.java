package com.login.ldap.springboot.controller;

import com.login.ldap.model.User;
import com.login.ldap.springboot.repository.UserRepository;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class forgotPassword {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public String ValidCode;

    public String id;
    public String username1;
    Random rand;
    private LocalDateTime expiredDateTime;
    private LocalDateTime issuedDateTime;
    private LocalDateTime confirmedDateTime;
    private String status;

@Autowired
LdapTemplate ldapTemplate;

@RequestMapping(value = "/validEmail", method= RequestMethod.GET, produces = "application/json")
public final Map<String,String> verifyEmail(@PathVariable String emailvalid)
{
    System.out.println("I am finally here");
    final Map<String, String> messageObject = new HashMap<>();

//    String emailvalid="arswaroop@outlk.com";

    User userfound;

if(userRepository.findOne(emailvalid)==null)
{
    messageObject.put("user","");
    return messageObject;

}

else {
    userfound = userRepository.findOne(emailvalid);

    String user;
    messageObject.put("user", userfound.getEmail());
return messageObject;
}
}


@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
public final RedirectView sendCode(@RequestParam("email") String username){
    this.username1=username;


     this.rand=new Random();
     this.issuedDateTime=LocalDateTime.now();
    this.expiredDateTime = this.issuedDateTime.plusHours(1);
    this.status="pending";

     id = String.format("%04d", rand.nextInt(10000));

//    List<String> a= ldapTemplate.search(
//            "ou=people",
//            "cn=" + username,
//            (AttributesMapper<String>) attrs -> (String) attrs
//                    .get("cn")
//                    .get());
//     if(a.equals(""))
//     {
//
//     }
//

    Mail mail = new Mail();
    mail.setFrom("noreply@noreply.com");
    mail.setSubject("HoosierEvents Password Recovery Code");
    mail.setContent("Code to reset your password is " + id);
    mail.setTo(username);
    emailService.sendSimpleMessage(mail);


    return new RedirectView("/forgotPassword");


}

@RequestMapping(value="/forgotPass" , method = RequestMethod.GET)
public final RedirectView changetoForgot()
{
    return new RedirectView("/forgotPassword");

}


    @RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
    public final RedirectView forgot(Model model, HttpServletRequest request, RegForm regForm, @RequestParam("password") String password, @RequestParam("code") String code, BindingResult bindingResult){
System.out.println(username1+" "+password);
        this.ValidCode=code;


        this.confirmedDateTime=LocalDateTime.now();
        if(this.confirmedDateTime.isAfter(this.expiredDateTime)){
            return new RedirectView ("/forgotPassworderror") ;

        }


        System.out.println(code +  " " + id);

        if(code.equals(id)) {

            User userFromDb = userRepository.findOne(this.username1);

            userFromDb.setPassword(bCryptPasswordEncoder.encode(password));

            userRepository.save(userFromDb);

            Mail mail = new Mail();
            mail.setFrom("noreply@noreply.com");
            mail.setSubject("HoosierEvents Password Recovery");
            mail.setContent("Your Password is changed!");
            mail.setTo(this.username1);
//        emailService.sendSimpleMessage(mail);


            return new RedirectView ("/login");

        }

        else {
            return new RedirectView ("/forgotpassword");
        }
    }






    }

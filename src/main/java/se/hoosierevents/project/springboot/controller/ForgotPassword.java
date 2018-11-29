
package se.hoosierevents.project.springboot.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;

@RestController
public class ForgotPassword {

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


//    @RequestMapping(value = "/validEmail", method= RequestMethod.GET, produces = "application/json")
//    public final Map<String,String> verifyEmail(@PathVariable String emailvalid)
//    {
//        System.out.println("I am finally here");
//        final Map<String, String> messageObject = new HashMap<>();
//
////    String emailvalid="arswaroop@outlk.com";
//
//        User userfound;
//
//        if(userRepository.findByEmail(emailvalid)==null)
//        {
//            messageObject.put("user","");
//            return messageObject;
//
//        }
//
//        else {
//            userfound = userRepository.findByEmail(emailvalid);
//
//            String user;
//            messageObject.put("user", userfound.getEmail());
//            return messageObject;
//        }
//    }



    @GetMapping(value="/userExists")
    public ResponseEntity<String> userExistsornot(String email){

        System.out.println("email" + email);

        if(userRepository.findByEmail(email)!= null) {
            sendCode(email);
            return ResponseEntity.ok("found");
        }
        else
            return ResponseEntity.ok("nope");

    }



    public final void sendCode(String username){
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
        System.out.println(username);

        Mail mail = new Mail();
        mail.setFrom("noreply@noreply.com");
        mail.setSubject("HoosierEvents Password Recovery Code");
        mail.setContent("Code to reset your password is " + id);
        mail.setTo(username);
        emailService.sendSimpleMessage(mail);


    }




    @RequestMapping(value = "/forgotPass")
    public final ResponseEntity<String> forgot(String password, String code){
        System.out.println(username1+" "+password);
        this.ValidCode=code;


        this.confirmedDateTime=LocalDateTime.now();
        if(this.confirmedDateTime.isAfter(this.expiredDateTime)){
            return ResponseEntity.ok("timeexpired");

        }


        System.out.println(code +  " " + id);

        if(code.equals(id)) {

            User userFromDb = userRepository.findByEmail(this.username1);

            userFromDb.setPassword(bCryptPasswordEncoder.encode(password));

            userRepository.save(userFromDb);

            Mail mail = new Mail();
            mail.setFrom("noreply@noreply.com");
            mail.setSubject("HoosierEvents Password Recovery");
            mail.setContent("Your Password is changed!");
            mail.setTo(this.username1);
//        emailService.sendSimpleMessage(mail);


            return ResponseEntity.ok("success");

        }

        else {
            return ResponseEntity.ok("codeinvalid");
        }
    }






}

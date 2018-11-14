package se.hoosierevents.project.springboot.springboot.controller;

import se.hoosierevents.project.springboot.springboot.data.service.LdapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
public class forgotPassword {

    @Autowired
    EmailService emailService;

    @Autowired
    LdapClient ldapClient;

    public String id;
    public String username1;

@Autowired
LdapTemplate ldapTemplate;

@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
public final RedirectView sendCode(@RequestParam("email") String username){
    username1=username;

    Random rand=new Random();
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
    public final RedirectView forgot(HttpServletRequest request, RegForm regForm,@RequestParam("password") String password, @RequestParam("code") String code){
System.out.println(username1+" "+password);
//
//        File f = new File("schema.ldif");
//        String password="";
//
//
//        try {
//            ////////////////////////////////////////////
//
//            BufferedReader b = new BufferedReader(new FileReader(f));
//            String readLine = "";
//
//            int flag=0;
//            while ((readLine = b.readLine()) != null) {
//                if(flag==1)
//                {
//                    password=b.readLine();
//                    System.out.println(password);
//                    break;
//                }
//
//                if(readLine=="uid: "+username)
//                {
//                    flag=1;
//                }
//
//
//            }
//            ////////////////////////////////////////////
//            b.close();
//
//        }catch(Exception e){}
//        finally {
//
//        }
        System.out.println(code +  " " + id);

        if(code.equals(id)) {

            ldapClient.modify(username1, password);


            Mail mail = new Mail();
            mail.setFrom("noreply@noreply.com");
            mail.setSubject("HoosierEvents Password Recovery");
            mail.setContent("Your Password is changed!");
            mail.setTo(username1);
//        emailService.sendSimpleMessage(mail);


            return new RedirectView("/login");

        }

        else {
            return new RedirectView("/forgotPass");
        }
    }






    }

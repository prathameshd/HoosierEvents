package com.login.ldap.springboot.controller;

import com.login.ldap.springboot.data.service.LdapClient;
import com.unboundid.ldap.sdk.LDAPConnection;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

@RestController
public class forgotPassword {


    @Autowired
    EmailService emailService;



    @RequestMapping(value = "/forgotPassword/te", method = RequestMethod.POST)
    public final RedirectView forgot(HttpServletRequest request, RegForm regForm,@RequestParam("usernameforgot") String username){

        File f = new File("schema.ldif");
String password="";


try {
    ////////////////////////////////////////////

    BufferedReader b = new BufferedReader(new FileReader(f));
    String readLine = "";

    int flag=0;
    while ((readLine = b.readLine()) != null) {
            if(flag==1)
            {
                password=b.readLine();
                break;
            }

            if(readLine=="uid: "+username)
            {
                flag=1;
            }


    }
    ////////////////////////////////////////////
b.close();

}catch(Exception e){}
finally {

}

        Mail mail = new Mail();
        mail.setFrom("noreply@noreply.com");
        mail.setSubject("Your Password is");
        mail.setContent("Your Password is"+password);
        mail.setTo(username);
        System.out.println(username);
        emailService.sendSimpleMessage(mail);




        return new RedirectView("/login");
    }






    }

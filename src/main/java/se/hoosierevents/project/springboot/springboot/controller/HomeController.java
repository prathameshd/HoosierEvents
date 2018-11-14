package se.hoosierevents.project.springboot.springboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@ComponentScan("org.springframework.security.samples.mvc")
public class HomeController  {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);


@GetMapping("/")
public void index() {

        log.info("Getting UsernamePasswordAuthenticationToken from SecurityContextHolder");
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        log.info("Getting principal from UsernamePasswordAuthenticationToken");
        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        log.info("authentication: " + authentication);
        log.info("principal: " + principal);

      //  return "hello";
    }

    @GetMapping("/managers")
    public String managers(){
        return "Hello managers";
    }

    @GetMapping("/employees")
    public String employees(){
        return "Hello employees";
    }




}

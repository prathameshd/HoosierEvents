package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;
import se.hoosierevents.project.springboot.service.GetAuthDetails;
import se.hoosierevents.project.springboot.config.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class GoogleSignIn {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    UserRepository userRepo;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SuccessfulLoginHandler successHandler;

    @RequestMapping(value="googleusernotexists")
    public ResponseEntity<String> GoogleUserNo(String email,String name)
    {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setPhoneNumber("1234");
        user.setActive(1);
        user.setUserType(UserRole.USER_NORMAL);
        userRepository.save(user);
        System.out.println("heloooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        return ResponseEntity.ok("created");
    }




    @RequestMapping(value="googleuserexists")
    public ResponseEntity<String> GoogleUserYes(String email, HttpServletRequest request,HttpServletResponse response) throws Exception {



        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, "1234");

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

//        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,"1234");
//        authToken.setDetails(new WebAuthenticationDetails(request));
//
//        // This call returns an authentication object, which holds principle and user credentials
//        Authentication authentication = this.authenticationManager.authenticate(authToken);
        System.out.println("principal = 11111111111111111111111111111111111111111111" + authentication.getPrincipal());
        // The security context holds the authentication object, and is stored
        // in thread local scope.
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("auth = 11111111111111111111111111111111111111111111" + authentication.isAuthenticated());



        successHandler.onAuthenticationSuccess(request,response,authentication);


        return ResponseEntity.ok("done");



    }


}

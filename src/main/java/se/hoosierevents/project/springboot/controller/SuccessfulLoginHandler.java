package se.hoosierevents.project.springboot.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component

@RequestMapping("/homepage")
 public class SuccessfulLoginHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

Authentication auth;
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getName());

        HttpSession session = arg0.getSession();
        session.setAttribute("username",authentication.getName());


        auth=authentication;
        redirectStrategy.sendRedirect(arg0, arg1, "/homepage");

    }




}

package se.hoosierevents.project.springboot.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import se.hoosierevents.project.springboot.repository.UserRepository;

@Component

@RequestMapping("/homepage")
public class SuccessfulLoginHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepo;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	Authentication auth;

	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
			Authentication authentication) throws IOException, ServletException {
		System.out.println(authentication.getName());

		HttpSession session = arg0.getSession();

		if (null != session) {
			session.setAttribute("user", userRepo.findByEmail(authentication.getName()));
		}

		auth = authentication;
		redirectStrategy.sendRedirect(arg0, arg1, "/homepage");

	}

}

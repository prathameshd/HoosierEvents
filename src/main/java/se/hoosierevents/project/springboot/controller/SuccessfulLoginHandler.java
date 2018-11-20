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

import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.config.UserRole;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component

@RequestMapping("/homepage")
public class SuccessfulLoginHandler implements AuthenticationSuccessHandler, UserRole {

	@Autowired
	UserRepository userRepo;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	Authentication auth;

	public void onAuthenticationSuccess(HttpServletRequest hsRequest, HttpServletResponse hsResponse,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = hsRequest.getSession();
		User user = userRepo.findByEmail(authentication.getName());

		if (null != session) {
			session.setAttribute("user", user);
		}
		auth = authentication;

		redirect(hsRequest, hsResponse, user);

	}

	private void redirect(HttpServletRequest hsRequest, HttpServletResponse hsResponse, User user) throws IOException {
		if (null != user) {
			if (Integer.parseInt(user.getUser_type()) == UserRole.USER_ADMIN) {
				redirectStrategy.sendRedirect(hsRequest, hsResponse, "/");
			} else if (Integer.parseInt(user.getUser_type()) == UserRole.USER_ORGANIZER) {
				redirectStrategy.sendRedirect(hsRequest, hsResponse, "/");
			}
		}
		redirectStrategy.sendRedirect(hsRequest, hsResponse, "/");
	}

}

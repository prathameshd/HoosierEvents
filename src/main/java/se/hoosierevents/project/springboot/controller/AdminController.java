package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;
import se.hoosierevents.project.springboot.service.AdminService;
import se.hoosierevents.project.springboot.service.UserService;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;


	@Autowired
	EmailService emailService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@RequestMapping("/approveOrganizer")
	public String approveOrganizer(@RequestParam("userId") Long id) {
		User user;
		user=userService.getUser(id);

		Mail mail = new Mail();
		mail.setFrom("noreply@noreply.com");
		mail.setSubject("Welcome to HoosierEvents!");
		mail.setContent("You have been successfully signed up as an organizer. For further questions please contact administrator at eventshoosier@gmail.com");
		mail.setTo(user.getEmail());
		emailService.sendSimpleMessage(mail);

		adminService.approveOrganizer(id);
		return "Successfully Approved Organizer";
	}

	@RequestMapping("/denyOrganizer")
	public String denyOrganizer(@RequestParam("userId") Long id) {
		User user;
		user=userService.getUser(id);

		Mail mail = new Mail();
		mail.setFrom("noreply@noreply.com");
		mail.setSubject("HoosierEvents: You have a notification");
		mail.setContent("Unfortunately, your application as an organizer at HoosierEvents couldn't be approved. Please contact the administrator at eventshoosier@gmail.com for more information.");
		mail.setTo(user.getEmail());
		emailService.sendSimpleMessage(mail);


		adminService.deleteUser(id);
		return "Successfully Removed Organizer Request";
	}
}

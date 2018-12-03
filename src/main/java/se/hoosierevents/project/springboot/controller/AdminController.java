package se.hoosierevents.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.service.AdminService;
import se.hoosierevents.project.springboot.service.EventService;
import se.hoosierevents.project.springboot.service.UserService;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;

	@Autowired
	EventService eventService;

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	@RequestMapping("/approveOrganizer")
	public String approveOrganizer(@RequestParam("userId") Long id) {
		User user;
		user = userService.getUser(id);

		Mail mail = new Mail();
		mail.setFrom("noreply@noreply.com");
		mail.setSubject("Welcome to HoosierEvents!");
		mail.setContent(
				"You have been successfully signed up as an organizer. For further questions please contact administrator at eventshoosier@gmail.com");
		mail.setTo(user.getEmail());
		emailService.sendSimpleMessage(mail);
		adminService.approveOrganizer(id);
		return "Successfully Approved Organizer";
	}

	@RequestMapping("/denyOrganizer")
	public String denyOrganizer(@RequestParam("userId") Long id) {
		User user;
		user = userService.getUser(id);

		Mail mail = new Mail();
		mail.setFrom("noreply@noreply.com");
		mail.setSubject("HoosierEvents: You have a notification");
		mail.setContent("Unfortunately, your application as an organizer at HoosierEvents couldn't be approved. Please contact the administrator at eventshoosier@gmail.com for more information.");
		mail.setTo(user.getEmail());
		emailService.sendSimpleMessage(mail);

		adminService.deleteUser(id);
		return "Successfully Removed Organizer Request";
	}

	@RequestMapping("/getReportedEvents")
	public ResponseEntity<List<Event>> getReportedEvents() {
		return ResponseEntity.ok(eventService.getReportedEvents());
	}

	@RequestMapping("/allowReportedEvent")
	public String allowReportedEvent(@RequestParam("eventId") Long id) {
		eventService.allowReportedEvent(id);
		return "Successfully Allowed Event";
	}

	@RequestMapping("/deleteReportedEvent")
	public String deleteReportedEvent(@RequestParam("eventId") Long id) {
		eventService.deleteReportedEvent(id);
		return "Successfully Removed reported Event from the System!!";
	}
}

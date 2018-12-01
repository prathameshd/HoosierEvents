package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.hoosierevents.project.springboot.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;

	@RequestMapping("/approveOrganizer")
	public String approveOrganizer(@RequestParam("userId") Long id) {
		adminService.approveOrganizer(id);
		return "Successfully Approved Organizer";
	}

	@RequestMapping("/denyOrganizer")
	public String denyOrganizer(@RequestParam("userId") Long id) {
		adminService.deleteUser(id);
		return "Successfully Removed Organizer Request";
	}
}

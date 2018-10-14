package se.hoosierevents.project.springboot.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import se.hoosierevents.project.springboot.service.HomeService;

@org.springframework.stereotype.Controller
public class HomeController {
	@Autowired
	private HomeService service;

	@RequestMapping("/")
	public String index() {
		return "homePage.html";
	}
}
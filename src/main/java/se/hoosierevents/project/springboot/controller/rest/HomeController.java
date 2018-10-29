package se.hoosierevents.project.springboot.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.springboot.service.HomeService;

@org.springframework.stereotype.Controller
public class HomeController {
	@Autowired
	private HomeService service;

	@RequestMapping("/")
	public String index() {
		return "homePage.html";
	}

	@RequestMapping("/saveEvent")
	public String saveEvent(HttpServletRequest r, Model m) {

		// String id = Req
		service.saveEvent(new Event("wewe"));
		return new String(service.getAllEvents());
	}
}
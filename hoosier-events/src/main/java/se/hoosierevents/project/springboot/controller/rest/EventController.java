package se.hoosierevents.project.springboot.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

import se.hoosierevents.project.springboot.service.EventService;

@org.springframework.stereotype.Controller
public class EventController implements Controller {

	@Autowired
	EventService eventService;
	
	//@Override
	public String control() {
		return null;
	}

	@RequestMapping("/getEvent")
	public JSONPObject getEvent(@RequestBody String data , @RequestParam("id") Long id, HttpServletRequest request, Model model) {
		return new JSONPObject("",eventService.getEvent(id));
	}
}

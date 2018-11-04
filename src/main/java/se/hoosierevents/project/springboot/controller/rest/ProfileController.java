package se.hoosierevents.project.springboot.controller.rest;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.hoosierevents.project.model.CompositeKey;
import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventAttend;
import se.hoosierevents.project.springboot.service.EventAttendService;
import se.hoosierevents.project.springboot.service.EventService;

@RestController
public class ProfileController{
	@Autowired
	EventService eventService;
	@Autowired
	EventAttendService eventAttendService;
	
	CompositeKey compositekey;
	
	public String control() {
		return null;
	}

	@RequestMapping("/getUser")
	public void getUser(@RequestParam("id") int id) {
	System.out.println("success");
	compositekey.setEventid(117);
	compositekey.setUserid(1);

	Optional<EventAttend> list=eventAttendService.giveDetails(compositekey);
	
	System.out.println(list.get());
	
	
	//public ResponseEntity<Event> getEvent(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
	//	System.out.println(ResponseEntity.ok(eventService.getEvent(id)));
	//	return ResponseEntity.ok(eventService.getEvent(id));
	}
}

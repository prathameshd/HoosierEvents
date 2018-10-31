package se.hoosierevents.project.springboot.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.springboot.service.EventService;

//@org.springframework.stereotype.Controller
@RestController
public class EventController implements Controller {

	@Autowired
	EventService eventService;

	@Override
	public String control() {
		return null;
	}

	@RequestMapping("/getEvent")
	public ResponseEntity<Event> getEvent(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getEvent(id));
	}

	@RequestMapping("/saveEvent")
	public RedirectView saveEvent(@RequestParam("eventTitle") String eventTitle, RedirectAttributes att,  Model m) {

		Event event = new Event(eventTitle);
		eventService.saveEvent(event);

		att.addFlashAttribute(event);
		return new RedirectView("eventPage.html");
	}

	@RequestMapping("/knowMore")
	public ResponseEntity<Event> knowMore(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getEvent(id));
	}
}

package se.hoosierevents.project.springboot.controller;

import java.io.Console;
import java.util.Date;
import java.util.List;

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
import se.hoosierevents.project.model.EventCategory;
import se.hoosierevents.project.model.Ticket;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.service.EventService;
import se.hoosierevents.project.springboot.service.TicketService;
import se.hoosierevents.project.springboot.service.UserService;

//@org.springframework.stereotype.Controller
@RestController
public class EventController implements Controller {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    //@Override
    public String control() {
        return null;
    }

    @RequestMapping("/getEvent")
    public ResponseEntity<Event> getEvent(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @RequestMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @RequestMapping("/saveEvent")
    public RedirectView saveEvent(@RequestParam("eventTitle") String eventTitle, RedirectAttributes att,  Model m) {

        Event event = new Event(eventTitle);
        eventService.saveEvent(event);

        att.addFlashAttribute(event);
        return new RedirectView("eventpage.html");
    }

    @RequestMapping("/knowMore")
    public ResponseEntity<Event> knowMore(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @RequestMapping("/getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents(HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @RequestMapping("/getEventsbyCategory")
    public ResponseEntity<List<Event>> getEventsbyCategory(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getEventsbyCategory(id));
    }

    @RequestMapping("/getEventsbyLocation")
    public ResponseEntity<List<Event>> getEventsbyLocation(@RequestParam("location") String location, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getEventsbyLocation(location));
    }

    @RequestMapping("/getEventbyName")
    public ResponseEntity<Event> getEventbyName(@RequestParam("event") String event, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getEventbyName(event));
    }

    @RequestMapping("/getCategories")
    public ResponseEntity<List<EventCategory>> getAllCategories(HttpServletRequest request, Model model) {
        return ResponseEntity.ok(eventService.getAllCategories());
    }

    @RequestMapping("/saveEventTicket")
    public void saveEventTicket(@RequestParam("eventTitle") String eventTitle, RedirectAttributes att,  Model m) {
        ticketService.saveEventTicket(eventTitle);
    }

    @RequestMapping("/getEventsToBeAttendedByUser")
    public ResponseEntity<List<Ticket>> getEventsToBeAttendedByUser(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
        return ResponseEntity.ok(ticketService.getEventsToBeAttendedByUser(id));
    }
}

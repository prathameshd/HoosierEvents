package se.hoosierevents.project.springboot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;
import se.hoosierevents.project.model.Ticket;
import se.hoosierevents.project.model.TicketType;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.service.EventService;
import se.hoosierevents.project.springboot.service.FileSystemStorageService;
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
	FileSystemStorageService fileStoreService;

	@Autowired
	TicketService ticketService;

	@Override
	public String control() {
		return null;
	}

	@RequestMapping(value = "/getImage")
	@ResponseBody
	public byte[] getEventImage(@RequestParam("img") String imagePath) throws IOException {
		Resource file = fileStoreService.loadAsResource(imagePath);
		return IOUtils.toByteArray(file.getInputStream());
	}

	@RequestMapping("/getEvent")
	public ResponseEntity<Event> getEvent(@RequestParam("id") Long id, HttpSession session) {
		return ResponseEntity.ok(eventService.getEvent(id));
	}

	@RequestMapping("/saveEvent")
	public RedirectView saveEvent(@RequestParam("file") MultipartFile file, @ModelAttribute Event event,
			RedirectAttributes redirectAttributes, HttpSession session) {
		fileStoreService.store(file, eventService.getCurrentImageNameToCreate());
		event.setCreatedBy((User) session.getAttribute(USER_KEY));
		eventService.saveEvent(event, file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return new RedirectView("eventpage");
	}

	@RequestMapping("/createEvent")
	public RedirectView createEvent(Model model) {
		model.addAttribute("event", new Event());
		return new RedirectView("create_event.html");
	}

	//@RequestMapping("/getUser")
	//public ResponseEntity<User> getUser(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
	//	return ResponseEntity.ok(userService.getUser(id));
	//}

	@RequestMapping("/getUser")
	public User getUser(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}

	@RequestMapping("/getAllEvents")
	public ResponseEntity<List<Event>> getAllEvents(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getAllEvents());
	}
	
	@RequestMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(userService.getAllUsers(1));
	}
	
	@RequestMapping("/getAllOrganizers")
	public ResponseEntity<List<User>> getAllOrganizers(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(userService.getAllUsers(2));
	}

	@RequestMapping("/getEventsbyCategory")
	public ResponseEntity<List<Event>> getEventsbyCategory(@RequestParam("id") Long id, HttpServletRequest request,
			Model model) {
		return ResponseEntity.ok(eventService.getEventsbyCategory(id));
	}

	@RequestMapping("/getEventsbyLocation")
	public ResponseEntity<List<Event>> getEventsbyLocation(@RequestParam("location") String location,
			HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getEventsbyLocation(location));
	}

	@RequestMapping("/getEventbyName")
	public ResponseEntity<Event> getEventbyName(@RequestParam("event") String event, HttpServletRequest request,
			Model model) {
		return ResponseEntity.ok(eventService.getEventbyName(event));
	}

	@RequestMapping("/getCategories")
	public ResponseEntity<List<EventCategory>> getAllCategories(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getAllCategories());
	}

	@RequestMapping("/saveEventTicket")
	public void saveEventTicket(@RequestParam("eventTitle") String eventTitle, @RequestParam("tickets_bronze") String tickets_bronze, @RequestParam("tickets_silver") String tickets_silver, @RequestParam("tickets_gold") String tickets_gold, HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ticketService.saveEventTicket(eventTitle,user);
	}

	@RequestMapping("/getEventsToBeAttendedByUser")
	public ResponseEntity<List<Ticket>> getEventsToBeAttendedByUser(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		return ResponseEntity.ok(ticketService.getEventsToBeAttendedByUser(id));
	}
	
	@RequestMapping("/getEventsAttendedByUser")
	public ResponseEntity<List<Ticket>> getEventsAttendedByUser(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		return ResponseEntity.ok(ticketService.getEventsAttendedByUser(id));
	}
	
	@RequestMapping("/getFutureEventsByOrganizer")
	public ResponseEntity<List<Event>> getFutureEventsByOrganizer(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		return ResponseEntity.ok(eventService.getFutureEventsByOrganizer(id,true));
	}
	
	@RequestMapping("/getFutureEvents")
	public ResponseEntity<List<Event>> getFutureEvents(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getFutureEvents(true));
	}
	
	@RequestMapping("/getPendingEvents")
	public ResponseEntity<List<Event>> getPendingEvents(HttpServletRequest request, Model model) {
		return ResponseEntity.ok(eventService.getFutureEvents(false));
	}
	
	@RequestMapping("/getEventsByOrganizerToBeApproved")
	public ResponseEntity<List<Event>> getEventsByOrganizerToBeApproved(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		return ResponseEntity.ok(eventService.getFutureEventsByOrganizer(id,false));
	}
	
	public ResponseEntity<List<Ticket>> getTicketby(@RequestParam("id") Long id, HttpServletRequest request, Model model) {
		return ResponseEntity.ok(ticketService.getEventsToBeAttendedByUser(id));
	}
	
	@RequestMapping("/getUserDemographics")
	public List<User> getUserDemographics(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		List<Event> events_of_organizer = new ArrayList<Event>(eventService.getEventsByOrganizer(id));
		List<User> users = new ArrayList<User>();
//		List<Ticket> tickets = new ArrayList<Ticket>();
		
		for(int i=0; i < events_of_organizer.size(); i++) {
			List<Ticket> tickets = new ArrayList<Ticket>(ticketService.getTicketsbyEvent(events_of_organizer.get(i)));
			
			for(int j=0; j < tickets.size(); j++) {
				if(!users.contains(tickets.get(j).getUser()))
					users.add(tickets.get(j).getUser());
			}
		}
		return users;
	}
	
	@RequestMapping("/updateUserDetails")
	public void updateUserDetails(@RequestParam("user_name") String user_name, @RequestParam("user_address") String user_address, @RequestParam("user_phone") String user_phone, @RequestParam("user_email") String user_email, HttpServletRequest request, Model m) {
		HttpSession session = request.getSession();
		User session_user = (User) session.getAttribute("user");
		session_user.setName(user_name);
		session_user.setAddress(user_address);
		session_user.setPhoneNumber(user_phone);
		session_user.setEmail(user_email);
		userService.updateUserDetails(session_user);
		
	}
	
	@RequestMapping("/getRevenue")
	public float getRevenue(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long id = user.getId();
		List<Event> events_of_organizer = new ArrayList<Event>(eventService.getEventsByOrganizer(id));
		List<Ticket> tickets = new ArrayList<Ticket>();
		float revenue = 0;
		
		for(int i=0; i < events_of_organizer.size(); i++) {
			tickets.addAll(ticketService.getTicketsbyEvent(events_of_organizer.get(i)));
		}
		
		for(int j=0; j < tickets.size(); j++) {
			Long event_id = tickets.get(j).getEvent().getId();
			Long tickettype_id = tickets.get(j).getticketType();
			revenue += ticketService.getRevenue(event_id,tickettype_id);
		}
		return revenue;
	}
	
	@RequestMapping("/reportEvent")
	public String reportEvent(@RequestParam("eventId") Long id, String reportedStatement) {
		eventService.reportEvent(id);
		return "Reported the Event to admin";
	}
}

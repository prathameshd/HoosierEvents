package se.hoosierevents.project.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.springboot.repository.EventCategoryRepository;
import se.hoosierevents.project.springboot.repository.EventRepository;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component
public class HomeService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EventCategoryRepository eventCategoryRepository;

	public String retrieveWelcomeMessage() {
		// Complex Method
		return "Good Morning updated";
	}

	public void saveEvent(Event event) {
		event.setCreatedBy(userRepository.findById(123L));
		event.setEventCategory(eventCategoryRepository.findById(123L));
		eventRepository.save(event);
	}
	
	public String getAllEvents() {
		StringBuilder result = new StringBuilder();
		List<Event> allEvents = new ArrayList<Event>();
		
		allEvents = eventRepository.findAll();
		Iterator<Event> i = allEvents.iterator();
		
		while (i.hasNext()) {
			Event event = (Event) i.next();
			result.append(event.getId()+ ": " + event.getEventTitle() + ", ");
		}
		
		return new String(result);
	}
	
	
}

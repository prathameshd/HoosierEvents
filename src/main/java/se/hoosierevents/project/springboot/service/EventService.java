package se.hoosierevents.project.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;
import se.hoosierevents.project.springboot.repository.EventCategoryRepository;
import se.hoosierevents.project.springboot.repository.EventRepository;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component
public class EventService implements Service {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventCategoryRepository eventCategoryRepository;

	//@Override
	public String Serve() {
		// TODO Auto-generated method stub
		return null;
	}

	public Event getEvent(Long id) {
		return eventRepository.findById(id).get();
	}

	public void saveEvent(Event event) {
//		event.setCreatedBy(userRepository.findById(99L).get());
//		event.setEventCategory(eventCategoryRepository.findById(1L).get());
		eventRepository.save(event);
	}

	public List<Event> getAllEvents() {
		return new ArrayList<Event> (eventRepository.findAll());
	}

	public List<Event> getEventsbyCategory(Long id) {
		EventCategory eventcategory = eventCategoryRepository.findById(id).get();
		return new ArrayList<Event> (eventRepository.findAllByEventCategory(eventcategory));
	}

	public List<Event> getEventsbyLocation(String location) {
		return new ArrayList<Event> (eventRepository.findAllByLocation(location));
	}

	public Event getEventbyName(String event) {
		return eventRepository.findAllByEventTitle(event).get();
	}

	public List<EventCategory> getAllCategories() {
		return new ArrayList<EventCategory> (eventCategoryRepository.findAll());
	}


}

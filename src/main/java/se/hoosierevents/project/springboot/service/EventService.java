package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.springboot.repository.EventRepository;

@Component
public class EventService implements Service {
	@Autowired
	EventRepository eventRepository;

	@Override
	public String Serve() {
		// TODO Auto-generated method stub
		return null;
	}

	public Event getEvent(Long id) {
		return eventRepository.findById(id).get();
	}

}

package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.springboot.repository.EventRepository;

@Component
public class HomeService {

	@Autowired
	EventRepository eventRepository;

	public String retrieveWelcomeMessage() {
		return "Home Page served";
	}

}

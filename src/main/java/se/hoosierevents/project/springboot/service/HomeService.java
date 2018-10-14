package se.hoosierevents.project.springboot.service;

import org.springframework.stereotype.Component;

@Component
public class HomeService {

	public String retrieveWelcomeMessage() {
		//Complex Method
		return "Good Morning updated";
	}
}

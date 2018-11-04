package se.hoosierevents.project.springboot.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.model.CompositeKey;
import se.hoosierevents.project.model.EventAttend;
import se.hoosierevents.project.springboot.repository.EventAttendRepository;
//import se.hoosierevents.project.springboot.repository.EventRepository;

@Component
public class EventAttendService implements Service {





		@Autowired
		EventAttendRepository eventAttendRepository;
		
		public String Serve() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public Optional<EventAttend> giveDetails(CompositeKey c) {
			return null;

		}
	

}

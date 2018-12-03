package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.Ticket;
import se.hoosierevents.project.model.TicketDetails;
import se.hoosierevents.project.model.TicketType;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.EventCategoryRepository;
import se.hoosierevents.project.springboot.repository.EventRepository;
import se.hoosierevents.project.springboot.repository.TicketDetailsRepository;
import se.hoosierevents.project.springboot.repository.TicketRepository;
import se.hoosierevents.project.springboot.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TicketService implements Service {

	@Autowired
    EventRepository eventRepository;

	@Autowired
    UserRepository userRepository;

	@Autowired
    EventCategoryRepository eventCategoryRepository;
	
	@Autowired
    TicketRepository ticketRepository;
	
	@Autowired
    TicketDetailsRepository ticketDetailsRepository;
	
	public String Serve() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveEventTicket(String eventTitle, User user) {
		Event event = eventRepository.findAllByEventTitle(eventTitle).get();
		Ticket ticket = new Ticket(event,user);
		ticketRepository.save(ticket);
	}
	
	public List<Ticket> getEventsToBeAttendedByUser(Long id) {
		User user = userRepository.findById(id).get();
		ArrayList<Ticket> db_result = new ArrayList<Ticket> (ticketRepository.findAllByUser(user));
		ArrayList<Ticket> result = new ArrayList<Ticket> ();
		Date today = new Date();
		System.out.println(today);
		for(int i = 0; i< db_result.size(); i++) {
			Event temp_event = db_result.get(i).getEvent();
			Date event_date = temp_event.getEndDate();
			System.out.println(event_date);
			if(event_date.after(today)) {
				System.out.println("Correct!");
				int j;
				for( j = 0; j < result.size(); j++) {
					if(db_result.get(i).getEvent().getId() == result.get(j).getEvent().getId() ) {
						break;
					}
				}
				if( j == result.size()) {
					result.add(db_result.get(i));
				}	
			}
		}
		return result;
	}
	
	public List<Ticket> getEventsAttendedByUser(Long id) {
		User user = userRepository.findById(id).get();
		ArrayList<Ticket> db_result = new ArrayList<Ticket> (ticketRepository.findAllByUser(user));
		ArrayList<Ticket> result = new ArrayList<Ticket> ();
		Date today = new Date();
		System.out.println(today);
		for(int i = 0; i< db_result.size(); i++) {
			Event temp_event = db_result.get(i).getEvent();
			Date event_date = temp_event.getEndDate();
			System.out.println(event_date);
			if(event_date.before(today)) {
				System.out.println("Correct!");
				int j;
				for( j = 0; j < result.size(); j++) {
					if(db_result.get(i).getEvent().getId() == result.get(j).getEvent().getId() ) {
						break;
					}
				}
				if( j == result.size()) {
					result.add(db_result.get(i));
				}
				
			}
		}
		return result;
	}
	
	public List<Ticket> getTicketsbyEvent(Event event){
		ArrayList<Ticket> result = new ArrayList<Ticket> (ticketRepository.findAllByEvent(event));
		return result;
	}
	
	public float getRevenue(Long event_id, Long ticketType_id){
		return 0;//ticketDetailsRepository.findByEventAndType(event_id,ticketType_id).getPrice();
	}
	
	public Float getTicketPrice(String eventTitle, int ticket_type) {
		Event event = eventRepository.findAllByEventTitle(eventTitle).get();
//		Ticket ticket = new Ticket(event,user);
//		Integer event_id = Math.toIntExact(event.getId());
		return ticketDetailsRepository.findTicketPrice(event.getId(),ticket_type);
	}

}

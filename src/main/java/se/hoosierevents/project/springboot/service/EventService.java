package se.hoosierevents.project.springboot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.EventCategory;
import se.hoosierevents.project.model.TicketDetails;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.EventCategoryRepository;
import se.hoosierevents.project.springboot.repository.EventRepository;
import se.hoosierevents.project.springboot.repository.TicketDetailsRepository;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component
public class EventService implements Service {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventCategoryRepository eventCategoryRepository;

	@Autowired
	TicketDetailsRepository ticketDetailsRepository;

	@Override
	public String Serve() {
		return null;
	}

	public Event getEvent(Long id) {
		return eventRepository.findById(id).get();
	}

	public void saveEvent(Event event, MultipartFile file, HttpServletRequest request) {
		event.setStartDate(getDateFromRequest(request, START_DATE, START_TIME));
		event.setEndDate(getDateFromRequest(request, END_DATE, END_TIME));
		event.setEventCategory(eventCategoryRepository.findByName(request.getParameter(EVENT_CATEGORY)));
		eventRepository.save(event);
		TicketDetails ticketDetails = new TicketDetails(event, TicketDetailsRepository.TICKET_TYPE_BRONZE_ID,
				Integer.parseInt(getValue(request.getParameter(BRONZE_SEAT_AVAILABLE))),
				Integer.parseInt(getValue(request.getParameter(BRONZE_SEAT_AVAILABLE))),
				Float.parseFloat(getValue(request.getParameter(BRONZE_PRICE))));
		ticketDetailsRepository.save(ticketDetails);

		ticketDetails = new TicketDetails(event, TicketDetailsRepository.TICKET_TYPE_SILVER_ID,
				Integer.parseInt(getValue(request.getParameter(SILVER_SEAT_AVAILABLE))),
				Integer.parseInt(getValue(request.getParameter(SILVER_SEAT_AVAILABLE))),
				Float.parseFloat(getValue(request.getParameter(SILVER_PRICE))));
		ticketDetailsRepository.save(ticketDetails);

		ticketDetails = new TicketDetails(event, TicketDetailsRepository.TICKET_TYPE_GOLD_ID,
				Integer.parseInt(getValue(request.getParameter(GOLD_SEAT_AVAILABLE))),
				Integer.parseInt(getValue(request.getParameter(GOLD_SEAT_AVAILABLE))),
				Float.parseFloat(getValue(request.getParameter(GOLD_PRICE))));
		ticketDetailsRepository.save(ticketDetails);
	}

	private String getValue(String parameter) {
		if (parameter == null || parameter.isEmpty())
			return "0";

		return parameter;
	}

	private Date getDateFromRequest(HttpServletRequest request, String date, String time) {
		String startDateString = request.getParameterValues(date)[0];
		String startTimeString = request.getParameterValues(time)[0];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm");
		try {
			return formatter.parse(startDateString + " " + startTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Event> getAllEvents() {
		return new ArrayList<Event>(eventRepository.findAll());
	}

	public List<Event> getFutureEvents(Boolean approval) {
		ArrayList<Event> db_result = new ArrayList<Event>(eventRepository.findAll());
		ArrayList<Event> result = new ArrayList<Event>();
		Date today = new Date();
		System.out.println(today);
		for (int i = 0; i < db_result.size(); i++) {
			Date event_date = db_result.get(i).getEndDate();
			System.out.println(event_date);
			if (event_date.after(today) && (db_result.get(i).getIsApproved() == approval)) {
				System.out.println("Correct!");
				int j;
				for (j = 0; j < result.size(); j++) {
					if (db_result.get(i).getId() == result.get(j).getId()) {
						break;
					}
				}
				if (j == result.size()) {
					result.add(db_result.get(i));
				}
			}
		}
		return result;
	}

	public List<Event> getEventsbyCategory(String category_name) {
		EventCategory eventcategory = eventCategoryRepository.findByName(category_name);
		ArrayList<Event> db_result = new ArrayList<Event>(eventRepository.findAllByEventCategory(eventcategory));
		ArrayList<Event> result = new ArrayList<Event>();
		Date today = new Date();
		for (int i = 0; i < db_result.size(); i++) {

			if (db_result.get(i).getEndDate().after(today)) {
				System.out.println("Correct!");
				result.add(db_result.get(i));
			}
		}
		return result;
	}

	public List<Event> getEventsbyLocation(String location) {
		return new ArrayList<Event>(eventRepository.findAllByLocation(location));
	}

	public List<Event> getEventbyName(String event) {
		ArrayList<Event> db_result = new ArrayList<Event>(eventRepository.findAll());
		ArrayList<Event> result = new ArrayList<Event>();
		Date today = new Date();
		for (int i = 0; i < db_result.size(); i++) {
			if (db_result.get(i).getEventTitle().toLowerCase().contains(event) && db_result.get(i).getEndDate().after(today)) {
				System.out.println("Correct!");
				result.add(db_result.get(i));
			}
		}
		return result;
	}

//			if (db_result.get(i).getEventTitle().toLowerCase().contains(event)
//					&& db_result.get(i).getEndDate().after(today)) {
//				System.out.println("Correct!");
//				result.add(db_result.get(i));
//			}
//		}
//		return result;
//	}
	public List<Event> getEventsforFirstSearch(String search_text) {
		ArrayList<Event> db_result = new ArrayList<Event>(eventRepository.findAll());
		ArrayList<Event> result = new ArrayList<Event>();
		Date today = new Date();
		for (int i = 0; i < db_result.size(); i++) {
			if (db_result.get(i).getEndDate().after(today) && (db_result.get(i).getEventTitle().toLowerCase().contains(search_text) || db_result.get(i).getEventCategory().getName().toLowerCase().contains(search_text) || db_result.get(i).getDescription().toLowerCase().contains(search_text))) {
				System.out.println("Correct!");
				result.add(db_result.get(i));
			}
		}
		return result;
	}
	public List<EventCategory> getAllCategories() {
		return new ArrayList<EventCategory>(eventCategoryRepository.findAll());
	}

	public void saveEvent(Event event, MultipartFile file) {
		event.setImage(getCurrentImageNameToCreate());
		eventRepository.save(event);
	}

	public Long getCurrentEventId() {
		return eventRepository.findMaxEventId();
	}

	public String getCurrentImageNameToCreate() {
		return getCurrentEventId().toString() + ".jpg";
	}

	public List<Event> getEventsByCreator(User user) {
		return eventRepository.findEventsByCreator(user);
	}

	public List<Event> getFutureEventsByOrganizer(Long id, Boolean approval) {
		User user = userRepository.findById(id).get();
		ArrayList<Event> db_result = new ArrayList<Event>(eventRepository.findAllByCreatedBy(user));
		ArrayList<Event> result = new ArrayList<Event>();
		Date today = new Date();
		System.out.println(today);
		if (approval) {
			for (int i = 0; i < db_result.size(); i++) {
				Date event_date = db_result.get(i).getEndDate();
				System.out.println(event_date);
				if (event_date.after(today)) {
					System.out.println("Correct!");
					int j;
					for (j = 0; j < result.size(); j++) {
						if (db_result.get(i).getId() == result.get(j).getId()) {
							break;
						}
					}
					if (j == result.size()) {
						result.add(db_result.get(i));
					}
				}
			}
		} else {
			for (int i = 0; i < db_result.size(); i++) {
				Date event_date = db_result.get(i).getEndDate();
				System.out.println(event_date);
				if (event_date.before(today)) {
					System.out.println("Correct!");
					int j;
					for (j = 0; j < result.size(); j++) {
						if (db_result.get(i).getId() == result.get(j).getId()) {
							break;
						}
					}
					if (j == result.size()) {
						result.add(db_result.get(i));
					}
				}
			}
		}

		return result;
	}

	public List<Event> getEventsByOrganizer(Long id) {
		User user = userRepository.findById(id).get();
		return new ArrayList<Event>(eventRepository.findAllByCreatedBy(user));
	}

	public List<Event> getReportedEvents() {
		return eventRepository.findAllByReported(true);
	}

	public void deleteReportedEvent(Long id) {
		eventRepository.deleteById(id);
	}

	public void allowReportedEvent(Long id) {
		Event reportedEvent = eventRepository.findById(id).get();
		reportedEvent.setReported(false);
		eventRepository.save(reportedEvent);
	}

	public void reportEvent(Long id, String reportedStatement) {
		Event event = eventRepository.findById(id).get();
		event.setReported(true);
		event.setReportStatement(reportedStatement);
		eventRepository.save(event);
	}

	public void updateEvent(Event updatedEvent) {
		Event originalEvent = eventRepository.findById(updatedEvent.getId()).get();
		originalEvent.setEventTitle(updatedEvent.getEventTitle());
		originalEvent.setDescription(updatedEvent.getDescription());
		originalEvent.setLocation(updatedEvent.getLocation());
		eventRepository.save(originalEvent);
	}
}

//package se.hoosierevents.project.springboot.springboot.data.service;
//
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import se.hoosierevents.project.model.Event;
//import se.hoosierevents.project.springboot.springboot.repository.EventCategoryRepository;
//import se.hoosierevents.project.springboot.springboot.repository.EventRepository;
//import se.hoosierevents.project.springboot.springboot.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//
//@Component
//@Service
//public class EventService {
//    @Autowired
//    EventRepository eventRepository;
//
//
//@Autowired
//  UserRepository userRepository;
//
//    @Autowired
//    EventCategoryRepository eventCategoryRepository;
//
//    @Override
//    public String Serve() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public Event getEvent(Long id) {
//        return eventRepository.findById(id).get();
//    }
//
//    public void saveEvent(Event event) {
//        event.setCreatedBy(userRepository.findById(99L).get());
//        event.setEventCategory(eventCategoryRepository.findById(1L).get());
//        eventRepository.save(event);
//
//    }
//
//    public String getAllEvents() {
//        StringBuilder result = new StringBuilder();
//        List<Event> allEvents = new ArrayList<Event>();
//
//        allEvents = eventRepository.findAll();
//        Iterator<Event> i = allEvents.iterator();
//
//        while (i.hasNext()) {
//            Event event = (Event) i.next();
//            result.append(event.getId() + ": " + event.getEventTitle() + ", ");
//        }
//
//        return new String(result);
//    }
//}

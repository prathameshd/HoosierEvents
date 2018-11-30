package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import se.hoosierevents.project.model.Event;
import se.hoosierevents.project.model.Ticket;
import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component
public class UserService implements Service {
	
	@Autowired
    UserRepository userRepository;
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	public String Serve() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateUserDetails(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAllUsers(int type) {
		ArrayList<User> db_result = new ArrayList<User>(userRepository.findAll());
		ArrayList<User> result = new ArrayList<User> ();
		
		for(int i = 0; i< db_result.size(); i++) {
			if(db_result.get(i).getUserType() == type ) {
				result.add(db_result.get(i));
			}
		}
		
		return result;
	}

}

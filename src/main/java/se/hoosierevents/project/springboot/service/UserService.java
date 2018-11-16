package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

}

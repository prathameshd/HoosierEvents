package se.hoosierevents.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.hoosierevents.project.model.User;
import se.hoosierevents.project.springboot.repository.UserRepository;

@Component
public class AdminService {
	@Autowired
	UserRepository userRepository;

	public void approveOrganizer(Long id) {
		User user = userRepository.findById(id).get();
		user.setActive(1);
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.delete(userRepository.findById(id).get());
	}
}

package se.hoosierevents.project.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import se.hoosierevents.project.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	//public User findById(Long id);
}

package se.hoosierevents.project.springboot.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import se.hoosierevents.project.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findById(Long id);
}

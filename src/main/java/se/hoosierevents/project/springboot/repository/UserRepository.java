package se.hoosierevents.project.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import se.hoosierevents.project.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


    @Query("select u from User u where u.email=:email")
	public User findByEmail(@Param("email") String email);

    public Optional<User> findById(Long id);
}


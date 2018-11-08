package com.login.ldap.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.login.ldap.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findById(String email);
}

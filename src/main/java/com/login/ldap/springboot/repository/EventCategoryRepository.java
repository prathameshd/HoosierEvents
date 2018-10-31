package com.login.ldap.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.login.ldap.model.EventCategory;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Long> {

}

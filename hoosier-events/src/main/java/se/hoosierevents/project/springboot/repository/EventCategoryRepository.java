package se.hoosierevents.project.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import se.hoosierevents.project.model.EventCategory;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Long> {

}

package se.hoosierevents.project.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import se.hoosierevents.project.model.EventCategory;

import java.util.List;
import java.util.Optional;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Long> {

    public Optional<EventCategory> findById(Long id);
    public List<EventCategory> findAll();
    public EventCategory findByName(String categoryName);
}

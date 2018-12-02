package se.hoosierevents.project.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hoosierevents.project.model.TicketDetails;

@Repository
@Transactional
public interface TicketDetailsRepository extends CrudRepository<TicketDetails, Long>{

	final Long TICKET_TYPE_BRONZE_ID = 1L;
	final Long TICKET_TYPE_SILVER_ID = 2L;
	final Long TICKET_TYPE_GOLD_ID = 1L;
	//@Query("SELECT t FROM TicketDetails t where t.event_id = ?1 AND t.ticket_type = ?2")
    //public TicketDetails findByEventAndType(Long event_id, Long ticket_type);
	
	final String EVENT_FIND_PRICE = "select price from ticket_breakdown where event_id=?1 AND ticket_type=?2";
	
	@Query(value = EVENT_FIND_PRICE, nativeQuery = true)
	Float findTicketPrice(Long event_id, int ticket_type);
}

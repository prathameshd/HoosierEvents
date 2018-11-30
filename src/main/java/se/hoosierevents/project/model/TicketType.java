package se.hoosierevents.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_ticket")
public class TicketType {
	
	@Id
	@Column(name = "tickettype_id", updatable = false)
	private Long id;
	
	@Column(name = "tickettype")
	private String ticketTypeName;

	public String getTicketTypeName() {
		return ticketTypeName;
	}

	public void setTicketTypeName(String ticketType) {
		this.ticketTypeName = ticketType;
	}
	
	public Long getTicketId() {
		return id;
	}

	public void setTicketId(Long id) {
		this.id = id;
	}
}

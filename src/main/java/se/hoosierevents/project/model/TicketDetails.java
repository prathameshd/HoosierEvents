package se.hoosierevents.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "ticket_breakdown")
@Table(name = "ticket_breakdown")
public class TicketDetails {

	@Id
	// @NotNull // Samething at db level can be done by @Column (name="", nullable=
	// false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tb_id", updatable = false)
	private Long id;
	
	@Cascade(CascadeType.MERGE)
	@ManyToOne(targetEntity = Event.class)
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private Event event;
	
//	@Cascade(CascadeType.MERGE)
//	@ManyToOne(targetEntity = TicketType.class)
//	@JoinColumn(name = "ticket_type", referencedColumnName = "tickettype_id")
	@Column(name = "ticket_type")
	private Long ticketType;
	
	@Column(name = "ticket_max")
	private int maximumSeats;

	@Column(name = "ticket_avail")
	private int availableSeats;
	
	@Column(name = "price")
	private float price;
	
	public Long getTicketType() {
		return ticketType;
	}

	public void setTicketType(Long ticketType) {
		this.ticketType = ticketType;
	}
	
	public int getMaximumSeats() {
		return maximumSeats;
	}

	public TicketDetails() {
		super();
	}

	public TicketDetails(Event event, Long ticketType, int maximumSeats, int availableSeats, float price) {
		this.event = event;
		this.ticketType = ticketType;
		this.maximumSeats = maximumSeats;
		this.availableSeats = availableSeats;
		this.price = price;
	}

	public void setMaximumSeats(int maximumSeats) {
		this.maximumSeats = maximumSeats;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}

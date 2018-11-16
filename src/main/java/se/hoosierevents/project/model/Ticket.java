package se.hoosierevents.project.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity
@Table(name = "event_attend")
public class Ticket {
	
//	private Event event;
//	private User user;
	
	@Id
	// @NotNull // Samething at db level can be done by @Column (name="", nullable=
	// false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id", updatable = false)
	private Long id;
	
	@Cascade(CascadeType.MERGE)
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@Cascade(CascadeType.MERGE)
	@ManyToOne(targetEntity = Event.class)
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private Event event;
	
//	@ManyToOne
//	@JoinColumn(name = "event_id")
	public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
//    @ManyToOne
//	@JoinColumn(name = "user_id")
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Ticket() {
	}
    
    public Ticket(Event event, User user) {
    	this.event = event;
    	this.user = user;
	}
    
    public Ticket(Event event) {
    	this.event = event;
	}
}

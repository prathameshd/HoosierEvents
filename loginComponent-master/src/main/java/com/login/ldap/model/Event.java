package com.login.ldap.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "events_master")
public class Event {
	@Id
	@NotNull // Samething at db level can be done by @Column (name="", nullable= false)
	@Column(name = "event_id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "event_name")
	private String eventTitle;

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	//@Cascade(CascadeType.MERGE)
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User optional) {
		this.createdBy = optional;
	}

	@Column(name = "location")
	private String location;

	@Column(name = "timezone")
	private String timeZone;

	@Column(name = "start_time", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "end_time")
	private Date endDate;

	@Column(name = "description")
	private String Description;

	@Column(name = "ispublic", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isPublic;

	@Column(name = "ispromoted")
	private Boolean isPromoted;

	@OneToOne
	@JoinColumn(name="id")
	private EventCategory eventCategory;

	
	
	public Event() {
	}
	
	public Event(String title) {
		setEventTitle(title);
	}
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsPromoted() {
		return isPromoted;
	}

	public void setIsPromoted(Boolean isPromoted) {
		this.isPromoted = isPromoted;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
}

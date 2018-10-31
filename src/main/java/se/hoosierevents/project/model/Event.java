package se.hoosierevents.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "events_master")
public class Event {
	@Id
	// @NotNull // Samething at db level can be done by @Column (name="", nullable=
	// false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id", updatable = false)
	private Long id;

	@Column(name = "event_name")
	private String eventTitle;

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	// @Cascade(CascadeType.MERGE)
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "created_by", referencedColumnName = "user_id")
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
	private String description;

	@Column(name = "ispublic", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isPublic;

	@Column(name = "ispromoted")
	private Boolean isPromoted;

	@OneToOne
	@JoinColumn(name = "category")
	private EventCategory eventCategory;

	public Event() {
	}

	public Event(String title) {
		this.eventTitle = title;
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
		return description;
	}

	public void setDescription(String description) {
		description = description;
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

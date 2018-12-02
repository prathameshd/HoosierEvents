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

	@Column(name = "start_time", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "end_time")
	private Date endDate;

	@Column(name = "description")
	private String description;

	@Column(name = "reportstatement")
	private String reportStatement;

	public String getReportStatement() {
		return reportStatement;
	}

	public void setReportStatement(String reportStatement) {
		this.reportStatement = reportStatement;
	}

	@Column(name = "ispublic", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isPublic;

	@Column(name = "reported", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean reported;

	public Boolean getReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public int getMaximumSeats() {
		return maximumSeats;
	}

	public void setMaximumSeats(int maximumSeats) {
		this.maximumSeats = maximumSeats;
	}

	public int getAvaialableSeats() {
		return avaialableSeats;
	}

	public void setAvaialableSeats(int avaialableSeats) {
		this.avaialableSeats = avaialableSeats;
	}

	@Column(name = "category")
	private String eventCategory;

	@Column(name = "max")
	private int maximumSeats;

	@Column(name = "available")
	private int avaialableSeats;

	@Column(name = "isapproved", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isApproved;

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean approval) {
		this.isApproved = approval;
	}

	@Column(name = "img")
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "twitterhandle")
	private String twitterHandle;

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}

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
		this.description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
}
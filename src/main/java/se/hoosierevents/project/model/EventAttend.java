package se.hoosierevents.project.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event_attend")
@IdClass(CompositeKey.class)
public class EventAttend {
	
	@EmbeddedId
	private int eventid;
	private int userid;
	
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	/*
	public int getEventId() {
		return eventid;
	}
	
	public int getUserId() {
		return userid;
	}
	
	public void setUserId(int userid)
	{
		this.userid=userid;
	}
	
	public void setEventId(int eventid)
	{
		this.eventid=eventid;
	}*/
	/*@Id
	@Column(name = "event_id", nullable=false)
	private int eventid;
	@Id
	@Column(name = "user_id", nullable=false)
	private int userid;*/
	/*@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user_id;*/
	
	/*@ManyToOne(targetEntity = Event.class)
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private Event event_id;
	*/
//	@Column(name = "event_id")
	//private int event_id;
	
	//@Column(name = "user_id")
	//private int user_id;

	
	/*
	public int getEventId() {
		return eventid;
	}

	public void setEventId(int eventid) {
		this.eventid = eventid;
	}
	
	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}*/
}

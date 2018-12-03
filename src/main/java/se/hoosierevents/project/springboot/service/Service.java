package se.hoosierevents.project.springboot.service;

public interface Service {

	// EVENT Request query parameters
	final String START_TIME = "start_time";
	final String END_TIME = "end_time";

	final String START_DATE = "start_date";
	final String END_DATE = "end_date";

	final String BRONZE_PRICE = "bronzeP";
	final String SILVER_PRICE = "silverP";
	final String GOLD_PRICE = "goldP";

	final String BRONZE_SEAT_AVAILABLE = "bronzeQ";
	final String SILVER_SEAT_AVAILABLE = "silverQ";
	final String GOLD_SEAT_AVAILABLE = "goldQ";
	
	final String EVENT_CATEGORY = "event_category";

	public String Serve();
}

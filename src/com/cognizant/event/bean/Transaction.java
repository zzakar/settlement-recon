package com.cognizant.event.bean;

public class Transaction {
	
	private String eventId;
	
	private String eventType;
	
	private String eventMaturity;	

	public Transaction(String eventId, String eventType, String eventMaturity) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventMaturity = eventMaturity;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventMaturity() {
		return eventMaturity;
	}

	public void setEventMaturity(String eventMaturity) {
		this.eventMaturity = eventMaturity;
	}
	
	

}

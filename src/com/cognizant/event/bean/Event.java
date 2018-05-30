package com.cognizant.event.bean;

public class Event {
	
	private Transaction transaction;
	
	private Fee fee;
	

	public Event(Transaction transaction, Fee fee) {
		super();
		this.transaction = transaction;
		this.fee = fee;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}
	
}

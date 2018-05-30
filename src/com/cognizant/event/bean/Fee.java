package com.cognizant.event.bean;

public class Fee {
	
	private int sender;
	private int reciver;
	private int acqFee;
	private int issFee;
	
	public Fee(int sender, int reciver, int acqFee, int issFee) {
		super();
		this.sender = sender;
		this.reciver = reciver;
		this.acqFee = acqFee;
		this.issFee = issFee;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReciver() {
		return reciver;
	}

	public void setReciver(int reciver) {
		this.reciver = reciver;
	}

	public int getAcqFee() {
		return acqFee;
	}

	public void setAcqFee(int acqFee) {
		this.acqFee = acqFee;
	}

	public int getIssFee() {
		return issFee;
	}

	public void setIssFee(int issFee) {
		this.issFee = issFee;
	}
	
	

}

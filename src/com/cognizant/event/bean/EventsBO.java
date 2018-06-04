package com.cognizant.event.bean;

public class EventsBO {

	private String MerchantName;
	private String MerchantNumber;
	private String crdNumber;
	private String txnType;
	private String txnAmount;
	private String component;
	private String windowId;
	private String business;
	private long eventId;
	
	
	
	public String getWindowId() {
		return windowId;
	}
	public void setWindowId(String windowId) {
		this.windowId = windowId;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getMerchantName() {
		return MerchantName;
	}
	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}
	public String getMerchantNumber() {
		return MerchantNumber;
	}
	public void setMerchantNumber(String merchantNumber) {
		MerchantNumber = merchantNumber;
	}
	public String getCrdNumber() {
		return crdNumber;
	}
	public void setCrdNumber(String crdNumber) {
		this.crdNumber = crdNumber;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	@Override
	public String toString() {
		return "EventsBO [MerchantName=" + MerchantName + ", MerchantNumber=" + MerchantNumber + ", crdNumber="
				+ crdNumber + ", txnType=" + txnType + ", txnAmount=" + txnAmount + ", component=" + component + "]";
	}
}

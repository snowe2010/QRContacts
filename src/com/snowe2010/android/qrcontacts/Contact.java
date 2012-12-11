package com.snowe2010.android.qrcontacts;

public class Contact {
	
	private String firstName = "";
	private String lastName = "";
	private String phoneNumber = "";
	private String homeEmail = "";
	private String workEmail = "";
	private String birthday = "";
	
	
	/** Getters and Setters */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHomeEmail() {
		return homeEmail;
	}
	public void setHomeEmail(String homeEmail) {
		this.homeEmail = homeEmail;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}

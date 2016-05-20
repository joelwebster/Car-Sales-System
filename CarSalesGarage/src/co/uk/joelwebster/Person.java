package co.uk.joelwebster;

import java.util.Date;

public abstract class Person {
	private int id;
	private String surname;
	private String forenames;
	private String address;
	private String town;
	private String county;
	private String postcode;
	private String phone;
	private Date registrationDate;

	// Default constructor
	public Person() {
		this.registrationDate = new Date();
	}

	// Getters
	public int getId() {
		return id;
	}
	public String getSurname() {
		return surname;
	}
	public String getForenames() {
		return forenames;
	}
	public String getAddress() {
		return address;
	}
	public String getTown() {
		return town;
	}
	public String getCounty() {
		return county;
	}
	public String getPhone() {
		return phone;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public String getPostcode() {
		return postcode;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setForenames(String forenames) {
		this.forenames = forenames;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}

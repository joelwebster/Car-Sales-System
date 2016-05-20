package co.uk.joelwebster;

public class Car {
	// Instance variables
	private int id;
	private String manufacturer;
	private String model;
	private int year;
	private String registration;
	private double value;
	private boolean sold;

	// Default constructor
	public Car () {}

	public Car (String manufacturer, String model) {
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public Car (String manufacturer, String model, int year, String registration, double value) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
		this.registration = registration;
		this.value = value;
	}

	// Methods
	public String toString() {
		return manufacturer + " " + model + " " + year + " - " + registration ;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}
}

package co.uk.joelwebster;

import java.util.Date;

public class Sale {
	private int id;
	private Car car;
	private Customer customer;
	private Agent agent;
	private Date date;
	private double price;

	// Constructors
	public Sale() {
		date = new Date();
	}

	public Sale(Car car) {
		this.car = car;
		this.date = new Date();
	}

	public Sale(Car car, Customer cust, Agent agt) {
		this.car = car;
		this.customer = cust;
		this.agent = agt;
		this.date = new Date();
	}

	public Sale(Car car, Customer cust, Agent agt, double price) {
		this.car = car;
		this.customer = cust;
		this.agent = agt;
		this.price = price;
		this.date = new Date();
	}

	// Methods
	public String toString() {
		return "\nCar: " + car.getManufacturer() + " " + car.getModel() +  "\nCustomer: " + customer.getForenames() + " " + customer.getSurname() + "\nAgent: " + agent.getForenames() + " " + agent.getSurname() + "\nDate: " + date.toString() + "\nPrice: " + price;
	}

	// Getters and setters
	public int getId() {
		return id;
	}
	public Car getCar() {
		return car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Agent getAgent() {
		return agent;
	}
	public Date getDate() {
		return date;
	}
	public double getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	// Methods
	public Sale createSale() {
		Sale sale = new Sale();
		return sale;
	}
}

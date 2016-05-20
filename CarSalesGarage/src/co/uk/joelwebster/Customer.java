package co.uk.joelwebster;

public class Customer extends Person {

	// Default constructor
	public Customer () {}

	public Customer(String surname,String forenames,String address,String town,String county,String postcode,String phone) {
		this.setSurname(surname);
		this.setForenames(forenames);
		this.setAddress(address);
		this.setTown(town);
		this.setCounty(county);
		this.setPostcode(postcode);
		this.setPhone(phone);
	}

	public String toString() {
		return this.getSurname() + ", " + this.getForenames() + " - " + this.getPostcode() ;
	}

}

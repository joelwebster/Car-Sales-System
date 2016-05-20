package co.uk.joelwebster;

public class Agent extends Person {
	private String role;

	public Agent () {}

	public Agent(String surname,String forenames,String address,String town,String county,String postcode,String phone,String role) {
		this.setSurname(surname);
		this.setForenames(forenames);
		this.setAddress(address);
		this.setTown(town);
		this.setCounty(county);
		this.setPostcode(postcode);
		this.setPhone(phone);
		this.setRole(role);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return this.getSurname() + ", " + this.getForenames() + " - " + this.getRole();
	}

}

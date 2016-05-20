package co.uk.joelwebster;

public class Main {

	public static void main (String[] args) {

		Agent agent = new Agent();
		agent.setForenames("Joel James");
		agent.setSurname("Webster");
		agent.setRole("Sales Person");

		System.out.println("Agent information:");
		System.out.println("Name: " + agent.getForenames() + " " + agent.getSurname());
		System.out.println("Role: " + agent.getRole());
		System.out.println("Registration date: " + agent.getRegistrationDate());

	}

}

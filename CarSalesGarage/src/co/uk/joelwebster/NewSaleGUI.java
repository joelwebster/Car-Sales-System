package co.uk.joelwebster;

import javax.swing.*;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewSaleGUI {

	JFrame frame;
	JPanel inputPanel;
	JPanel buttonPanel;
	JButton addSale;

	JTextField salePrice;

	JComboBox<Car> carBox;
	JComboBox<Customer> customerBox;
	JComboBox<Agent> agentBox;

	JLabel carLabel;
	JLabel customerLabel;
	JLabel agentLabel;
	JLabel salePriceLabel;

	//final DefaultComboBoxModel registrationModel = new DefaultComboBoxModel();

	private static SessionFactory factory;
	List<Car> carResults;
	List<Customer> customerResults;
	List<Agent> agentResults;

	Sale sale;
	Car car;
	Customer customer;
	Agent agent;

	public static void main (String[] args) {
		// Instantiate GUI class
		NewSaleGUI gui = new NewSaleGUI();

		// Load objects from Hibernate
		gui.getAgents();
		gui.getCars();
		gui.getCustomers();


		// Start GUI
		gui.startGUI();
	}

	public void startGUI() {
		// Create JFrame
		frame = new JFrame("New Sale");
		// Create JPanel
		inputPanel = new JPanel();
		// Create JPanel
		buttonPanel = new JPanel();

		// Create menu bar
		// menuBar = new JMenuBar();

		// Create add car button
		addSale = new JButton("Save Sale");
		// Register ActionListener
		addSale.addActionListener(new AddSaleListener());
		salePrice = new JTextField(20);

		// Add labels
		carLabel = new JLabel("Car: ");
		customerLabel = new JLabel("Customer: ");		
		agentLabel = new JLabel("Sales Agent: ");		
		salePriceLabel = new JLabel("Sale Price: ");

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set frame non-resizable
		frame.setResizable(false);

		// Set panel layout to vertical stack
		inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));

		// Combo Box stuff

		JScrollPane carListScrollPane = new JScrollPane(carBox);
		JScrollPane customerListScrollPane = new JScrollPane(customerBox);
		JScrollPane agentListScrollPane = new JScrollPane(agentBox);

		// Add components to frame
		inputPanel.add(carLabel);
		inputPanel.add(carListScrollPane);

		inputPanel.add(customerLabel);
		inputPanel.add(customerListScrollPane);

		inputPanel.add(agentLabel);
		inputPanel.add(agentListScrollPane);

		inputPanel.add(salePriceLabel);
		inputPanel.add(salePrice);

		// Add "Add Car" button to button panel
		buttonPanel.add(addSale);

		// Add panels to the frame, defining positioning
		frame.getContentPane().add(BorderLayout.CENTER,inputPanel);
		frame.getContentPane().add(BorderLayout.SOUTH,buttonPanel);

		// Set frame size
		frame.setSize(400, 240);
		// Set frame visible
		frame.setVisible(true);

	}

	// Get Cars for combo box
	public void getCars() {
		// Create Hibernate session etc.
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(ExceptionInInitializerError ex) {
			System.err.println("Failed to create SessionFactory object." + ex);
			JOptionPane.showMessageDialog(frame, "Could not establish database connection.");
			ex.printStackTrace();
		}

		// Open session
		Session session = factory.openSession();
		// Begin transaction
		Transaction t = session.beginTransaction();

		// Get list of all cars in database
		carResults = session.createQuery("FROM co.uk.joelwebster.Car C WHERE C.sold != 1").list();

		// Print elements of the list
		for (int i=0;i<carResults.size();i++) {
			System.out.println(carResults.get(i).toString());
		}

		// Add each car to the combo box
		carBox = new JComboBox(carResults.toArray());

		// Commit the transaction
		t.commit();

		// Close the session
		session.close();
	}

	// Get Customers for combo box
	public void getCustomers() {
		// Create Hibernate session etc.
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(ExceptionInInitializerError ex) {
			System.err.println("Failed to create SessionFactory object." + ex);
			JOptionPane.showMessageDialog(frame, "Could not establish database connection.");
			ex.printStackTrace();
		}

		// Open session
		Session session = factory.openSession();
		// Begin transaction
		Transaction t = session.beginTransaction();
		// Get list of all customers in database
		customerResults = session.createQuery("FROM co.uk.joelwebster.Customer").list();

		// Print elements of the list
		for (int i=0;i<customerResults.size();i++) {
			System.out.println(customerResults.get(i).toString());
		}

		// Add each car to the combo box
		customerBox = new JComboBox(customerResults.toArray());

		// Commit the transaction
		t.commit();

		// Close the session
		session.close();
	}

	// Get Customers for combo box
	public void getAgents() {
		// Create Hibernate session etc.
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(ExceptionInInitializerError ex) {
			System.err.println("Failed to create SessionFactory object." + ex);
			JOptionPane.showMessageDialog(frame, "Could not establish database connection.");
			ex.printStackTrace();
		}

		// Open session
		Session session = factory.openSession();
		System.out.println("Session opened");
		// Begin transaction
		Transaction t = session.beginTransaction();
		System.out.println("Transaction started");
		// Get list of all agents in database
		System.out.println("Reading in Agents");
		agentResults = session.createQuery("FROM co.uk.joelwebster.Agent").list();

		System.out.println("Printing agents");

		// Print elements of the list
		for (int i=0;i<agentResults.size();i++) {
			System.out.println(agentResults.get(i).toString());
		}

		// Add each car to the combo box
		agentBox = new JComboBox(agentResults.toArray());

		// Commit the transaction
		t.commit();

		// Close the session
		session.close();
	}

	class AddSaleListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (!(carBox.getItemCount() == 0 || customerBox.getItemCount() == 0 || agentBox.getItemCount() == 0 || salePrice.getText().isEmpty())) {
				System.out.println("Passed validation");

				// Get car object to update sold flag
				Car car = (Car) carBox.getSelectedItem();

				// Save sale via hibernate
				try{
					// Create car
					sale = new Sale((Car) carBox.getSelectedItem(),(Customer) customerBox.getSelectedItem(),(Agent) agentBox.getSelectedItem(),Double.parseDouble(salePrice.getText()));
					// Create factory session
					factory = new Configuration().configure().buildSessionFactory();
				}
				catch (IllegalArgumentException ex) {
					//System.out.println("Could not create Car object, invalid parameters given.");
					JOptionPane.showMessageDialog(frame, "Invalid details entered.");
					ex.printStackTrace();
				}
				catch(ExceptionInInitializerError ex) {
					System.err.println("Failed to create SessionFactory object." + ex);
					JOptionPane.showMessageDialog(frame, "Could not establish database connection.");
					ex.printStackTrace();
				}

				// TODO: Add try/catch blocks to the below operations:

				// Create session object
				Session session = factory.openSession();
				// Create transaction object
				Transaction t = session.beginTransaction();
				// Persist the sale object
				session.persist(sale);

				// Set car to sold
				car.setSold(true);
				session.merge(car);

				// Commit the transaction
				t.commit();

				// Acknowledge saving of object
				//System.out.println("Sale was successfully saved.");
				JOptionPane.showMessageDialog(frame, "Sale was saved to the database.");

				// Close the session
				session.close();

				// Reload car list - to remove car which was just sold
				//getCars();

				// Clear text input fields
				salePrice.setText("");

				/*
				frame.revalidate();
				frame.repaint();
				 */

			} 
			else {
				JOptionPane.showMessageDialog(frame, "Details cannot be left empty.");
			}
		}
	}
}

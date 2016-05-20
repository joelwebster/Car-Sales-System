package co.uk.joelwebster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AddAgentGUI {
	
	JFrame frame;
	JPanel inputPanel;
	JPanel buttonPanel;
	JButton addAgent;
	
	JTextField surname;
	JTextField forenames;
	JTextField address;
	JTextField town;
	JTextField county;
	JTextField postcode;
	JTextField phone;
	JTextField role;
	
	JLabel surnameLabel;
	JLabel forenamesLabel;
	JLabel addressLabel;
	JLabel townLabel;
	JLabel countyLabel;
	JLabel postcodeLabel;
	JLabel phoneLabel;
	JLabel roleLabel;
	
	private static SessionFactory factory;
	Agent agent;

	public static void main(String[] args) {
		AddAgentGUI gui = new AddAgentGUI();
		gui.startGUI();
	}
	
	public void startGUI() {
		
		// Create JFrame
		frame = new JFrame("Add New Agent");
		// Create JPanels
		inputPanel = new JPanel();
		buttonPanel = new JPanel();
		// Create add car button
		addAgent = new JButton("Add Agent");
		// Register ActionListener
		addAgent.addActionListener(new AddAgentListener());
		// Add text fields
		surname = new JTextField(50);
		forenames = new JTextField(50);
		address = new JTextField(50);
		town = new JTextField(50);
		county = new JTextField(50);
		postcode = new JTextField(9);
		phone = new JTextField(20);
		role = new JTextField(20);

		// Add labels
		surnameLabel = new JLabel("Surname: ");
		forenamesLabel = new JLabel("Forenames: ");
		addressLabel = new JLabel("Address: ");	
		townLabel = new JLabel("Town: ");
		countyLabel = new JLabel("County: ");
		postcodeLabel = new JLabel("Postcode: ");	
		phoneLabel = new JLabel("Telephone: ");
		roleLabel = new JLabel("Role: ");

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set frame non-resizable
		frame.setResizable(false);

		// Set panel layout to vertical stack
		inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));

		// Add components to frame
		inputPanel.add(surnameLabel);
		inputPanel.add(surname);
		inputPanel.add(forenamesLabel);
		inputPanel.add(forenames);
		inputPanel.add(addressLabel);
		inputPanel.add(address);
		inputPanel.add(townLabel);
		inputPanel.add(town);
		inputPanel.add(countyLabel);
		inputPanel.add(county);
		inputPanel.add(postcodeLabel);
		inputPanel.add(postcode);
		inputPanel.add(phoneLabel);
		inputPanel.add(phone);
		inputPanel.add(roleLabel);
		inputPanel.add(role);
		

		// Add "Find Car" button to button panel
		buttonPanel.add(addAgent);

		// Add panels to the frame, defining positioning
		frame.getContentPane().add(BorderLayout.CENTER,inputPanel);
		frame.getContentPane().add(BorderLayout.SOUTH,buttonPanel);

		// Set frame size
		frame.setSize(300, 350);
		// Set frame visible
		frame.setVisible(true);
		
	}
	
	class AddAgentListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Code to run when Add Car button is clicked
			if (!(surname.getText().isEmpty() || forenames.getText().isEmpty() || address.getText().isEmpty() || town.getText().isEmpty() || county.getText().isEmpty() || postcode.getText().isEmpty() || phone.getText().isEmpty() || role.getText().isEmpty())) {
			
			try{
				agent = new Agent(surname.getText(),forenames.getText(),address.getText(),town.getText(),county.getText(),postcode.getText(),phone.getText(),role.getText());
				
				
			} catch (IllegalArgumentException ex) {
				System.out.println("Could not create Agent object, invalid parameters given.");
				JOptionPane.showMessageDialog(frame, "Details cannot be left empty.");
				ex.printStackTrace();
			}
			
			// Create Hibernate session etc.
			try{
				factory = new Configuration().configure().buildSessionFactory();
			}catch(ExceptionInInitializerError ex) {
				System.err.println("Failed to create SessionFactory object." + ex);
				JOptionPane.showMessageDialog(frame, "Could not establish database connection.");
				ex.printStackTrace();
			}
			
			// Create session object
			Session session = factory.openSession();
			// Create transaction object
			Transaction t = session.beginTransaction();
			// Persist the sale object
			session.persist(agent);
			// Commit the transaction
			t.commit();
			
			// Acknowledge saving of object
			System.out.println("Agent was successfully saved.");
			JOptionPane.showMessageDialog(frame, "Agent was saved to the database.");
			
			// Close the session
			session.close();
			
			// Clear text input fields
			surname.setText("");
			forenames.setText("");
			address.setText("");
			town.setText("");
			county.setText("");
			postcode.setText("");
			phone.setText("");
			role.setText("");
			
			}
			else {
				JOptionPane.showMessageDialog(frame, "Details cannot be left empty.");
			}
		}
	}
}

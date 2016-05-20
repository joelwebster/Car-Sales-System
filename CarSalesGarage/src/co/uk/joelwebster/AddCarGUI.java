package co.uk.joelwebster;

import javax.swing.*;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddCarGUI {

	JFrame frame;
	JPanel inputPanel;
	JPanel buttonPanel;
	JButton addCar;
	JTextField manufacturer;
	JTextField model;
	JTextField year;
	JTextField registration;
	JTextField value;
	JLabel manufacturerLabel;
	JLabel modelLabel;
	JLabel yearLabel;
	JLabel registrationLabel;
	JLabel valueLabel;

	JTextField carRegistration;
	JScrollPane scrollPane;
	JTextArea carDetailsArea;

	// JMenuBar menuBar;

	private static SessionFactory factory;
	Car car;


	public static void main (String[] args) {
		// Instantiate GUI class
		AddCarGUI gui = new AddCarGUI();
		// Start GUI
		gui.startGUI();

		//gui.viewCarGUI();
	}

	public void startGUI() {
		// Create JFrame
		frame = new JFrame("Add Car");
		// Create JPanel
		inputPanel = new JPanel();
		// Create JPanel
		buttonPanel = new JPanel();

		// Create menu bar
		// menuBar = new JMenuBar();

		// Create add car button
		addCar = new JButton("Add Car");
		// Register ActionListener
		addCar.addActionListener(new AddCarListener());
		// Add text fields
		manufacturer = new JTextField(20);
		model = new JTextField(20);
		year = new JTextField(4);
		registration = new JTextField(8);
		value = new JTextField(20);

		// Add labels
		manufacturerLabel = new JLabel("Manufacturer: ");
		modelLabel = new JLabel("Model: ");	
		yearLabel = new JLabel("Year: ");
		registrationLabel = new JLabel("Registration: ");
		valueLabel = new JLabel("Value: ");

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set frame non-resizable
		frame.setResizable(false);

		// Set panel layout to vertical stack
		inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));

		// Add components to frame
		inputPanel.add(manufacturerLabel);
		inputPanel.add(manufacturer);
		inputPanel.add(modelLabel);
		inputPanel.add(model);

		inputPanel.add(yearLabel);
		inputPanel.add(year);
		inputPanel.add(registrationLabel);
		inputPanel.add(registration);
		inputPanel.add(valueLabel);
		inputPanel.add(value);

		// Add "Add Car" button to button panel
		buttonPanel.add(addCar);

		// Add panels to the frame, defining positioning
		frame.getContentPane().add(BorderLayout.CENTER,inputPanel);
		frame.getContentPane().add(BorderLayout.SOUTH,buttonPanel);

		// Set frame size
		frame.setSize(300, 270);
		// Set frame visible
		frame.setVisible(true);
	}
	
	public void createTestCars() {
		// Create loads of test cars and add them to the database
		int howMany = 20;
		
		for (int i=0;i<howMany;i++) {
			// Create random car
			
			//
			//ArrayList ca
			
			// Add it to the database via hibernate
			
			
		}
	}

	class AddCarListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Empty field validation
			if (!(manufacturer.getText().isEmpty() || model.getText().isEmpty() || year.getText().isEmpty() || registration.getText().isEmpty() || value.getText().isEmpty())) {
				try{
					// Create car
					car = new Car(manufacturer.getText(), model.getText(), Integer.parseInt(year.getText()), registration.getText(), Double.parseDouble((value.getText())));
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
				session.persist(car);
				// Commit the transaction
				t.commit();

				// Acknowledge saving of object
				//System.out.println("Car was successfully saved.");
				JOptionPane.showMessageDialog(frame, "Car was saved to the database.");

				// Close the session
				session.close();

				manufacturer.setText("");
				model.setText("");
				year.setText("");
				registration.setText("");
				value.setText("");

			} else {
				JOptionPane.showMessageDialog(frame, "Details cannot be left empty.");
			}
		}
	}
}

package co.uk.joelwebster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

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

public class ViewCarGUI {

	JFrame frame;
	JPanel displayPanel;
	JPanel buttonPanel;
	JPanel regPanel;
	JButton findCar;
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

	// JMenuBar menuBar;

	private static SessionFactory factory;
	Car car;

	public static void main (String[] args) {
		// Instantiate GUI class
		ViewCarGUI gui = new ViewCarGUI();
		// Start GUI
		gui.startGUI();
	}

	public void startGUI() {
		// Create JFrame
		frame = new JFrame("Car Lookup");
		// Create JPanels
		displayPanel = new JPanel();
		buttonPanel = new JPanel();
		regPanel = new JPanel();

		// Create menu bar
		// menuBar = new JMenuBar();

		// Create add car button
		findCar = new JButton("Find Car");
		// Register ActionListener
		findCar.addActionListener(new ViewCarListener());
		// Add text fields
		manufacturer = new JTextField(20);
		model = new JTextField(20);
		year = new JTextField(4);
		registration = new JTextField(8);
		value = new JTextField(20);

		// Set text fields to non-editable
		manufacturer.setEditable(false);
		model.setEditable(false);
		year.setEditable(false);
		value.setEditable(false);

		// Add labels
		registrationLabel = new JLabel("Enter registration: ");

		manufacturerLabel = new JLabel("Manufacturer: ");
		modelLabel = new JLabel("Model: ");	
		yearLabel = new JLabel("Year: ");
		valueLabel = new JLabel("Value: ");

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set frame non-resizable
		frame.setResizable(false);

		// Set panel layout to vertical stack
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));

		// Add components to frame
		regPanel.add(registrationLabel);
		regPanel.add(registration);

		displayPanel.add(manufacturerLabel);
		displayPanel.add(manufacturer);
		displayPanel.add(modelLabel);
		displayPanel.add(model);

		displayPanel.add(yearLabel);
		displayPanel.add(year);
		displayPanel.add(valueLabel);
		displayPanel.add(value);

		// Add "Find Car" button to button panel
		regPanel.add(findCar);

		// Add panels to the frame, defining positioning
		frame.getContentPane().add(BorderLayout.NORTH,regPanel);
		frame.getContentPane().add(BorderLayout.CENTER,displayPanel);

		// Set frame size
		frame.setSize(400, 230);
		// Set frame visible
		frame.setVisible(true);
	}

	class ViewCarListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Code to run when View Car button clicked
			if (!(registration.getText().isEmpty())) {

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
				// Get registration
				String reg = registration.getText();

				// Get results in a list
				List<Car> carResults = session.createQuery("FROM co.uk.joelwebster.Car C WHERE C.registration=" + "'" + reg + "'").list();

				// Iterate over list
				Iterator<Car> iterator = carResults.iterator();

				Car carFound = null;

				while (iterator.hasNext()){
					carFound = iterator.next();
					// Set text fields from retrieved object
					manufacturer.setText(carFound.getManufacturer());
					model.setText(carFound.getModel()); 
					year.setText(String.valueOf(carFound.getYear())); 
					value.setText(String.valueOf(carFound.getValue()));
				}

				// If car is not found
				if(carFound==null) {
					JOptionPane.showMessageDialog(frame, "Car with this registration could not be found");
				}

				// Commit the transaction
				t.commit();

				// Close the session
				session.close();
			} else {
				JOptionPane.showMessageDialog(frame, "Enter a registration.");
			}
		}
	}
}

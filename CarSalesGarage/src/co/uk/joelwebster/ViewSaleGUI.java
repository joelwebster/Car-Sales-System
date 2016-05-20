package co.uk.joelwebster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class ViewSaleGUI {

	JFrame frame;

	JPanel displayPanel;
	JPanel buttonPanel;

	JButton findSale;

	JTextField saleId;
	JTextField carManufacturer;
	JTextField carModel;
	JTextField carYear;
	JTextField carRegistration;
	JTextField customerSurname;
	JTextField customerForenames;
	JTextField agentSurname;
	JTextField agentForenames;
	JTextField saleDate;
	JTextField salePrice;

	JLabel saleIdLabel;
	JLabel carManufacturerLabel;
	JLabel carModelLabel;
	JLabel carYearLabel;
	JLabel carRegistrationLabel;
	JLabel customerSurnameLabel;
	JLabel customerForenamesLabel;
	JLabel agentSurnameLabel;
	JLabel agentForenamesLabel;
	JLabel saleDateLabel;
	JLabel salePriceLabel;

	// JMenuBar menuBar;

	private static SessionFactory factory;
	Sale sale;

	public static void main (String[] args) {
		// Instantiate GUI class
		ViewSaleGUI gui = new ViewSaleGUI();
		// Start GUI
		gui.startGUI();
	}

	public void startGUI() {
		// Create JFrame
		frame = new JFrame("Sale Lookup");
		// Create JPanels
		displayPanel = new JPanel();
		buttonPanel = new JPanel();

		// Create menu bar
		// menuBar = new JMenuBar();

		// Create add car button
		findSale = new JButton("Find Sale");
		// Register ActionListener
		findSale.addActionListener(new ViewSaleListener());
		// Add text fields
		saleId = new JTextField(6);
		carManufacturer = new JTextField(20);
		carModel = new JTextField(20);
		carYear = new JTextField(20);
		carRegistration = new JTextField(20);
		customerSurname = new JTextField(20);
		customerForenames = new JTextField(20);
		agentSurname = new JTextField(20);
		agentForenames = new JTextField(20);
		saleDate = new JTextField(20);
		salePrice = new JTextField(20);

		// Set text fields to non-editable
		carManufacturer.setEditable(false);
		carModel.setEditable(false);
		carYear.setEditable(false);
		carRegistration.setEditable(false);
		customerSurname.setEditable(false);
		customerForenames.setEditable(false);
		agentSurname.setEditable(false);
		agentForenames.setEditable(false);
		saleDate.setEditable(false);
		salePrice.setEditable(false);

		// Add labels
		saleIdLabel = new JLabel("Enter sale ID: ");

		carManufacturerLabel = new JLabel("Manufacturer: ");
		carModelLabel = new JLabel("Model: ");	
		carYearLabel = new JLabel("Year: ");
		carRegistrationLabel = new JLabel("Registration: ");
		customerSurnameLabel = new JLabel("Customer Surname: ");
		customerForenamesLabel = new JLabel("Customer Forenames: ");	
		agentSurnameLabel = new JLabel("Agent Surname: ");
		agentForenamesLabel = new JLabel("Agent Forenames: ");
		saleDateLabel = new JLabel("Sale Date: ");
		salePriceLabel = new JLabel("Sale Price: ");

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set frame non-resizable
		frame.setResizable(false);

		// Set panel layout to vertical stack
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));

		// Add components to frame
		buttonPanel.add(saleIdLabel);
		buttonPanel.add(saleId);
		buttonPanel.add(findSale);

		displayPanel.add(carManufacturerLabel);
		displayPanel.add(carManufacturer);
		displayPanel.add(carModelLabel);
		displayPanel.add(carModel);
		displayPanel.add(carYearLabel);
		displayPanel.add(carYear);
		displayPanel.add(carRegistrationLabel);
		displayPanel.add(carRegistration);
		displayPanel.add(customerSurnameLabel);
		displayPanel.add(customerSurname);
		displayPanel.add(customerForenamesLabel);
		displayPanel.add(customerForenames);
		displayPanel.add(agentSurnameLabel);
		displayPanel.add(agentSurname);
		displayPanel.add(agentForenamesLabel);
		displayPanel.add(agentForenames);
		displayPanel.add(saleDateLabel);
		displayPanel.add(saleDate);
		displayPanel.add(salePriceLabel);
		displayPanel.add(salePrice);

		// Add panels to the frame, defining positioning
		frame.getContentPane().add(BorderLayout.NORTH,buttonPanel);
		frame.getContentPane().add(BorderLayout.CENTER,displayPanel);

		// Set frame size
		frame.setSize(350, 500);
		// Set frame visible
		frame.setVisible(true);

	}

	class ViewSaleListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!(saleId.getText().isEmpty())) {
				// Code to run when View Car button clicked

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

				// Get results in a list
				List<Sale> saleResults = session.createQuery("FROM co.uk.joelwebster.Sale S WHERE S.id=" + saleId.getText()).list();

				// Iterate over list
				Iterator<Sale> iterator = saleResults.iterator();
				Sale saleFound = null;

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				//String s = df.format(saleFound.getDate());

				while (iterator.hasNext()){
					saleFound = iterator.next();
					// Get Car, Customer, Agent objects
					Car car = (Car) saleFound.getCar();
					Customer customer = (Customer) saleFound.getCustomer();
					Agent agent = (Agent) saleFound.getAgent();

					// Set text fields from retrieved object
					carManufacturer.setText(car.getManufacturer());
					carModel.setText(car.getModel());
					carYear.setText(String.valueOf((car.getYear())));
					carRegistration.setText(car.getRegistration());

					customerSurname.setText(customer.getSurname());
					customerForenames.setText(customer.getForenames());

					agentSurname.setText(agent.getSurname());
					agentForenames.setText(agent.getForenames());

					saleDate.setText(df.format(saleFound.getDate()));
					salePrice.setText(String.valueOf(saleFound.getPrice()));

				}

				// If car is not found
				if(saleFound==null) {
					JOptionPane.showMessageDialog(frame, "Sale with entered ID could not be found.");
				}



				// Commit the transaction
				t.commit();

				// Close the session
				session.close();

			}
			else {
				JOptionPane.showMessageDialog(frame, "Enter a sale ID.");
			}

		}
	}
}

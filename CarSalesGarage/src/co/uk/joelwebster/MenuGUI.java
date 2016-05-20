package co.uk.joelwebster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuGUI {
	JFrame frame;
	JPanel menuPanel;
	//JPanel buttonPanel;
	JButton addCar;
	JButton viewCar;

	JButton addCustomer;
	JButton addAgent;
	JButton addSale;
	JButton viewSale;

	public void startGUI() {

		// Create JFrame
		frame = new JFrame("Car Sales System");
		// Create JPanel
		menuPanel = new JPanel();
		// Create JButtons
		addCar = new JButton("Add Car");
		viewCar = new JButton("View Car");
		addCustomer = new JButton("Add Customer");
		addAgent = new JButton("Add Agent");
		addSale = new JButton("Add Sale");
		viewSale = new JButton("View Sale"); // view by saleid

		// Set close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set panel layout to vertical stack
		menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.X_AXIS));
		// Add components to panel
		menuPanel.add(addCar);
		// Register ActionListener
		addCar.addActionListener(new AddCarListener());

		menuPanel.add(viewCar);
		// Register ActionListener
		viewCar.addActionListener(new ViewCarListener());

		menuPanel.add(addCustomer);
		// Register ActionListener
		addCustomer.addActionListener(new AddCustomerListener());

		menuPanel.add(addAgent);
		// Register ActionListener
		addAgent.addActionListener(new AddAgentListener());

		menuPanel.add(addSale);
		// Register ActionListener
		addSale.addActionListener(new NewSaleListener());

		menuPanel.add(viewSale);
		// Register ActionListener
		viewSale.addActionListener(new ViewSaleListener());

		// Add panels to frame
		frame.getContentPane().add(BorderLayout.CENTER,menuPanel);
		// Set frame size
		frame.setSize(550, 64);
		// Set frame visible
		frame.setVisible(true);
		// Set frame non-resizable
		frame.setResizable(false);

	}

	class AddCarListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AddCarGUI addCar = new AddCarGUI();
			addCar.startGUI();
		}
	}

	class ViewCarListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ViewCarGUI viewCar = new ViewCarGUI();
			viewCar.startGUI();
		}
	}

	class AddCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AddCustomerGUI addCustomer = new AddCustomerGUI();
			addCustomer.startGUI();
		}
	}

	class AddAgentListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AddAgentGUI addAgent = new AddAgentGUI();
			addAgent.startGUI();
		}
	}

	class NewSaleListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			NewSaleGUI addSale = new NewSaleGUI();
			addSale.getAgents();
			addSale.getCars();
			addSale.getCustomers();
			addSale.startGUI();
		}
	}

	class ViewSaleListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ViewSaleGUI viewSale = new ViewSaleGUI();
			viewSale.startGUI();
		}
	}

	public static void main (String[] args) {
		MenuGUI gui = new MenuGUI();
		gui.startGUI();
	}

}

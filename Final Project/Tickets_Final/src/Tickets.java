// Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program controls the GUI
 */
// import required packages
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

//suppress warning
@SuppressWarnings("serial")
// class starts
public class Tickets extends JFrame implements ActionListener {

	// class level member objects
	Dao dao = new Dao(); // for CRUD operations
	Boolean chkIfAdmin = null;
	String username;

	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuTickets = new JMenu("Tickets");

	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItemExit;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem mnuItemOpenTicket;
	JMenuItem mnuItemViewTicket;
	JMenuItem mnuItemViewTicketByNum; 
	JMenuItem mnuItemCloseTicket; 
	JMenuItem mnuItemUpdateTicketDesc; 

	// Tickets starts
	public Tickets(Boolean isAdmin, String user) {
		chkIfAdmin = isAdmin;
		username = user;
		createMenu();
		prepareGUI();
	} // Tickets ends

	// createMenu starts
	private void createMenu() {
		/* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);

		// initialize first sub menu items for Admin main menu
		mnuItemUpdate = new JMenuItem("Update Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemUpdate);

		// initialize second sub menu items for Admin main menu
		mnuItemDelete = new JMenuItem("Delete Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemDelete);

		// initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		// initialize second sub menu item for Tickets main menu
		mnuItemViewTicket = new JMenuItem("View Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemViewTicket);
		
		
		mnuItemViewTicketByNum = new JMenuItem("View Ticket by Ticket Numbers");
		mnuTickets.add(mnuItemViewTicketByNum);
				
		
		mnuItemCloseTicket = new JMenuItem("Close Ticket");
		mnuAdmin.add(mnuItemCloseTicket);
				
		
		mnuItemUpdateTicketDesc = new JMenuItem("Update Ticket Description");
		mnuTickets.add(mnuItemUpdateTicketDesc);


		// initialize any more desired sub menu items below

		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemUpdate.addActionListener(this);
		mnuItemDelete.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);
		mnuItemViewTicketByNum.addActionListener(this);
		mnuItemCloseTicket.addActionListener(this);
		mnuItemUpdateTicketDesc.addActionListener(this);
	} // createMenu ends

	// prepareGUI starts
	private void prepareGUI() {

		// create JMenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		if (chkIfAdmin)
			bar.add(mnuAdmin);
		bar.add(mnuTickets);
		// add menu bar components to frame
		setJMenuBar(bar);

		addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		setSize(400, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	} //prepareGUI ends

	// Override
	@Override
	// actionPerformed starts
	public void actionPerformed(ActionEvent e) {
		// implement actions for sub menu items
		// if starts
		if (e.getSource() == mnuItemExit) {
			System.exit(0);
		} // if ends
		// else if starts
		else if (e.getSource() == mnuItemOpenTicket) {

			// get ticket information
			String ticketName = JOptionPane.showInputDialog(null, "Please enter your name");
			String ticketDesc = JOptionPane.showInputDialog(null, "Please enter a ticket description");
			String startDate = JOptionPane.showInputDialog(null, "Please enter a ticket start date in the YYYY-MM-DD format");

			// insert ticket information to database

			int id = dao.insertRecords(ticketName, ticketDesc, startDate);

			// display results if successful or not to console / dialog box
			// if starts
			if (id != 0) {
				System.out.println("Ticket ID : " + id + " created successfully!!!");
				JOptionPane.showMessageDialog(null, "Ticket id: " + id + " created");
			} // if ends 
			else
				System.out.println("Ticket cannot be created!!!");
		} // else if ends 
		//else if starts
		else if(e.getSource() == mnuItemCloseTicket) { 
			
			String ticketNum = JOptionPane.showInputDialog(null, "Please enter the number of the ticket you would like to close");
			String endDate = JOptionPane.showInputDialog(null, "Please enter the end date for the ticket in the YYYY-MM-DD format");
			int confirmation = JOptionPane.showConfirmDialog(null, ("You want to close ticket " + ticketNum), "Confirm?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (confirmation == JOptionPane.NO_OPTION)
				System.out.println("Ticket Closing Cancelled");
			else
				dao.closeRecords(Integer.valueOf(ticketNum), endDate);
		} // else if ends
		// else if starts
		else if (e.getSource() == mnuItemViewTicket) {
			// retrieve all tickets details for viewing in JTable
			// try starts
			try {
				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readRecords(chkIfAdmin, username)));
				jt.setBounds(30, 40, 200, 400);
				JScrollPane sp = new JScrollPane(jt);
				add(sp);
				setVisible(true); // refreshes or repaints frame on screen
			} // try ends
			// catch starts
			catch (SQLException e1) {
				e1.printStackTrace();
			} // catch ends
		} // else if ends
		// else if starts
		else if (e.getSource() == mnuItemViewTicketByNum) {
			// retrieve all tickets details for viewing in JTable
			// try starts
			try {
				int ticketNum = Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter the number of the ticket you would like to view"));

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.ticketByNum(chkIfAdmin, ticketNum, username)));
				jt.setBounds(30, 40, 200, 400);
				JScrollPane sp = new JScrollPane(jt);
				add(sp);
				setVisible(true); // refreshes or repaints frame on screen
			} // try ends
			// catch starts
			catch (SQLException e1) {
				e1.printStackTrace();
			} // catch ends
		} // else if ends 
		// else if starts
		else if (e.getSource() == mnuItemDelete) {
			String ticketNum = JOptionPane.showInputDialog(null, "Please enter the number of the ticket you would like to delete");
			int confirmation = JOptionPane.showConfirmDialog(null, "Delete ticket " + ticketNum + "?","Confirm",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
			
			if (confirmation == JOptionPane.NO_OPTION)
				System.out.println("Deletion Cancelled");
			else
				dao.deleteRecords(Integer.valueOf(ticketNum));
		} // else if ends 
		// else if starts
		else if(e.getSource() == mnuItemUpdateTicketDesc) {
			String ticketNum = JOptionPane.showInputDialog(null, "Please enter the number of the ticket you would like to update");
			String ticketDesc = JOptionPane.showInputDialog(null, "Please enter the new ticket description");
			
			int confirmation = JOptionPane.showConfirmDialog(null, "Update ticket " + ticketNum + " description?","Confirm",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
			
			if (confirmation == JOptionPane.NO_OPTION)
				System.out.println("Update Cancelled");
			else
				dao.updateRecords(chkIfAdmin, Integer.valueOf(ticketNum), username, ticketDesc);
		} // else if ends
	} // actionPerformed ends
} // class ends

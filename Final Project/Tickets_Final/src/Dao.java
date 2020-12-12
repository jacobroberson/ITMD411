// Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program connects and interacts with the database
 */
// import required packages
import java.io.*;
import java.sql.*;
import java.util.*;

// class starts
public class Dao {
	// instance fields
	static Connection connect = null;
	Statement statement = null;

	// constructor
	// Dao starts
	public Dao() {
	  
	} // Dao ends

	// getConnection starts
	public Connection getConnection() {
		// Setup the connection with the DB
		// try starts
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3307/tickets?autoReconnect=true&useSSL=false"
							+ "&user=fp411&password=411");
		} // try ends
		// catch starts
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
		return connect;
	} // getConnection ends

	// CRUD implementation

	// createTables starts
	public void createTables() {
		// variables for SQL Query table creations
		final String createTicketsTable = "CREATE TABLE jrobe_tickets(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200), start_date DATE, end_date DATE)";
		final String createUsersTable = "CREATE TABLE jrobe_users(uid INT AUTO_INCREMENT PRIMARY KEY, uname VARCHAR(30), upass VARCHAR(30), admin int)";

		//try starts
		try {

			// execute queries to create tables

			statement = getConnection().createStatement();

			statement.executeUpdate(createTicketsTable);
			statement.executeUpdate(createUsersTable);
			System.out.println("Created tables in given database...");

			// end create table
			// close connection/statement object
			statement.close();
			connect.close();
		} // try ends
		// catch starts
		catch (Exception e) {
			System.out.println(e.getMessage());
		} // catch ends
		// add users to user table
		addUsers();
	} // createTables ends

	// addUsers starts
	public void addUsers() {
		// add list of users from userlist.csv file to users table

		// variables for SQL Query inserts
		String sql;

		Statement statement;
		BufferedReader br;
		List<List<String>> array = new ArrayList<>(); // list to hold (rows & cols)

		// read data from file
		// try starts
		try {
			br = new BufferedReader(new FileReader(new File("userlist.csv")));

			String line;
			// while loop starts
			while ((line = br.readLine()) != null) {
				array.add(Arrays.asList(line.split(",")));
			} // while loop ends
		} // try ends
		// catch starts
		catch (Exception e) {
			System.out.println("There was a problem loading the file");
		} // catch ends
		//try starts
		try {
			// Setup the connection with the DB
			statement = getConnection().createStatement();
			// create loop to grab each array index containing a list of values
			// and PASS (insert) that data into your User table
			// for loop starts
			for (List<String> rowData : array) {
				sql = "INSERT INTO jrobe_users(uname,upass,admin) " + "VALUES('" + rowData.get(0) + "'," + " '"
						+ rowData.get(1) + "','" + rowData.get(2) + "');";
				statement.executeUpdate(sql);
			} // for loop ends
			System.out.println("Inserts completed in the given database...");
			// close statement object
			statement.close();
		} // try ends 
		// catch starts
		catch (Exception e) {
			System.out.println(e.getMessage());
		} // catch ends
	} // addUsers ends

	//insertRecords starts
	public int insertRecords(String ticketName, String ticketDesc, String startDate) {
		int id = 0;
		// try starts
		try {
			statement = getConnection().createStatement();
			statement.executeUpdate("INSERT INTO jrobe_tickets" + "(ticket_issuer, ticket_description, start_date) VALUES(" + " '"
					+ ticketName + "','" + ticketDesc + "','" + startDate + "')", Statement.RETURN_GENERATED_KEYS);

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			// if starts
			if (resultSet.next()) {
				// retrieve first field in table
				id = resultSet.getInt(1);
			} // if ends
		} // try ends 
		// catch starts
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
		return id;

	} // insertRecords ends

	// readRecords starts
	public ResultSet readRecords(boolean isAdmin, String user) {

		ResultSet results = null;
		// try starts
		try {
			statement = getConnection().createStatement();
			if(isAdmin)
				results = statement.executeQuery("SELECT * FROM jrobe_tickets");
			else
				results = statement.executeQuery("SELECT * FROM jrobe_tickets WHERE ticket_issuer = '" + user + "'");
		} // try ends 
		// catch starts
		catch (SQLException e1) {
			e1.printStackTrace();
		} // catch ends
		return results;
	} // readRecords ends
	
	// ticketByNum starts
	public ResultSet ticketByNum(boolean isAdmin, int ticketNum, String user) {
		
		ResultSet results = null;
		// try starts
		try {
			statement = getConnection().createStatement();
			if(isAdmin) 
				results = statement.executeQuery("SELECT * FROM jrobe_tickets WHERE ticket_id = " + ticketNum);
			else 
				results = statement.executeQuery("SELECT * FROM jrobe_tickets WHERE ticket_id = '" + ticketNum + "' AND ticket_issuer = '" + user + "'");
		} // try ends 
		// catch ends
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
		
		return results;
	} // ticketByNum ends
	// continue coding for updateRecords implementation
public void updateRecords(boolean isAdmin, int ticketNum, String user, String desc) {
		// try starts
		try {
			statement = getConnection().createStatement();
			int updated;
			if (isAdmin)
				updated = statement.executeUpdate("UPDATE jrobe_tickets SET ticket_description = '" + desc + "' WHERE ticket_id = " + ticketNum);
			else 
				updated = statement.executeUpdate("UPDATE jrobe_tickets SET ticket_description = '" + desc + "' WHERE ticket_issuer = '" + user + "', AND ticket_num = " + ticketNum);
			
			if (updated != 0)
				System.out.println("Ticket " + ticketNum + " has been updated. Updated Description: " + desc);
			else
				System.out.println("No ticket has been updated");
		} // try ends
		// catch starts
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
	} // updateRecords ends

	// continue coding for deleteRecords implementation
	public void deleteRecords(int ticketNum) {
		// try starts
		try { 
			statement = connect.createStatement();
			int deleted = statement.executeUpdate("DELETE FROM jrobe_tickets WHERE ticket_id = " + ticketNum);
			
			if (deleted != 0)
				System.out.println("Ticket " + ticketNum+ " has been deleted");
			else
				System.out.println("No ticket has been deleted. Ticket " + ticketNum + " could not be found");

		} // try ends 
		// catch starts
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
			
	} // deleteRecords ends
	
	// continue coding for closeRecords
	public void closeRecords(int ticketNum, String endDate) {
		// try starts
		try {
			statement = getConnection().createStatement();
			
			int updated = statement.executeUpdate("UPDATE jrobe_tickets SET end_date = '" + endDate + "' WHERE ticket_id = '" + ticketNum + "'");
			
			if (updated != 0)
				System.out.println("Ticket " + ticketNum + " has been successfully closed");
			else
				System.out.println("No ticket has been closed");
		} // try ends
		// catch starts
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch ends
	} // closeRecords ends
} // class ends

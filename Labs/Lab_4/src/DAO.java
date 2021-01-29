//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program creates a table, inserts data, and retrieves data
 */
//import java.sql.*
import java.sql.*;
//class starts
public class DAO {

	//Declare DB objects 
DBConnect conn = null;
Statement stmt = null;

	// constructor
	public DAO() { //create db object instance
	 conn = new DBConnect();
}
	// CREATE TABLE METHOD
	public void createT() {
		//try starts
		try {
			 // Open a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

				String sql = "CREATE TABLE j_robe_tab " + 
				             "(pid INTEGER not NULL AUTO_INCREMENT, " +
				  	       " id VARCHAR(10), " +
						 " income numeric(8,2), " + 
						 " pep VARCHAR(4), " + 
					 " PRIMARY KEY ( pid ))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); //close db connection 
			} //try ends
		//catch starts
		catch (SQLException se) {
			// Handle errors for JDBC
			 se.printStackTrace();
			 } //catch ends
	} //method ends
	
	// INSERT INTO METHOD
	public void insertR(BankRecords[]rec) {
		//try starts
		  try {
			  // Execute a query
			  System.out.println("Inserting records into the table...");
			  stmt = conn.connect().createStatement();
		  String sql = null;
			
		        // Include all object data to the database table
			  for (int n = 0; n < rec.length; ++n) {
		        
		// finish string assignment to insert all object data 
		// (id, income, pep) into your database table
			  	
				  sql = "INSERT INTO j_robe_tab(id, income, pep)" + "VALUES(' " + rec[n].getId().toUpperCase() + "', ' "
							+ rec[n].getIncome() + "', ' " + rec[n].getPep() + "')";

				stmt.executeUpdate(sql);
			    } //for ends
			  System.out.println("Records added to database...");
		      conn.connect().close();
			   } //try ends
		  //catch starts
		  catch (SQLException se) { 
			  se.printStackTrace(); 
		  } //catch ends

	} //method ends
	
	//RETREIEVE METHOD
	public  ResultSet retrieveR() {
		ResultSet Rset = null;
		//try starts
		try {
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");
			stmt = conn.connect().createStatement();
			System.out.println("");
			String sql = "SELECT * from j_robe_tab";
			Rset = stmt.executeQuery(sql);
			System.out.println("Records retrieved from the database...");
			conn.connect().close();
		} //try ends
		//catch starts
		catch (SQLException se) {
			se.printStackTrace();
		} //catch ends
		return Rset; //return Rset
	} //method ends
} //class ends

//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program connects to the database
 */
//import java.sql.*
import java.sql.*;
//class starts
public class DBConnect {
	// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/411labs?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "db411", PASS = "411";
	//establish connection
	public Connection connect() throws SQLException {

	 return DriverManager.getConnection(DB_URL, USER, PASS);

	}
} //class ends

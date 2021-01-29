//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program extends BankRecords and utilizes DAO.java to generate data
 */
//import java.sql.*
import java.sql.*;
//class starts
public class LoanProcessor extends BankRecords {
	//main starts
	public static void main(String[] args) {
		BankRecords br = new BankRecords();
		br.readData();
		DAO DAO = new DAO();
		DAO.createT();
		DAO.insertR(rec); // perform inserts
		ResultSet Rset = DAO.retrieveR(); // fill result set object

		// Create heading for display
		System.out.println("\nLoan Analysis Report:\n");
		System.out.printf("%s %8.2s %s\n", "ID", "INCOME", "PEP");
		
		// Extract data from result set
			try {
				System.out.println("ID:\t\tINCOME\t\tPEP");
				//while starts
				while (Rset.next()) {
				// Retrieve data by column name (i.e., for id,income,pep)
					String id = Rset.getString("id");
					double income = Rset.getDouble("income");
					String pep = Rset.getString("pep").strip();
				// Display values for id,income,pep
					System.out.printf("%7s %8.2f %7s\n", id, income, pep);
				  } //while ends
				Rset.close();
				} //try ends
			//catch starts
			catch (SQLException se) {
				se.printStackTrace();
			} //catch ends
	} //main ends
} //class ends
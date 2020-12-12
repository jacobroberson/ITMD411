// Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program creates the table model
 */
// import required packages
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

// class starts
public class ticketsJTable {

	// suppress warning
	@SuppressWarnings("unused")
	private final DefaultTableModel tableModel = new DefaultTableModel();

	// buildTableModel starts
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		// for loop starts
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		} // for loop ends

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		// while loop starts
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			// for loop starts
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			} // for loop ends
			data.add(vector);
		} // while loop ends
		// return data/col.names for JTable
		return new DefaultTableModel(data, columnNames); 

	} // buildTableModel ends

} // class ends

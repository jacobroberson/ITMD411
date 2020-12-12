// Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program controls the login to the ticketing system
 */
// import required packages
import java.awt.GridLayout; //useful for layouts
import java.awt.event.*;
import java.sql.*;
//controls-label text fields, button
import javax.swing.*;

//suppress warning
@SuppressWarnings("serial")
// class starts
public class Login extends JFrame {

	Dao conn;

	// Login starts
	public Login() {

		super("IIT HELP DESK LOGIN");
		conn = new Dao();
		conn.createTables();
		setSize(400, 210);
		setLayout(new GridLayout(4, 2));
		setLocationRelativeTo(null); // centers window

		// SET UP CONTROLS
		JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
		JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
		JLabel lblStatus = new JLabel(" ", JLabel.CENTER);
		// JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);

		JTextField txtUname = new JTextField(10);

		JPasswordField txtPassword = new JPasswordField();
		JButton btn = new JButton("Submit");
		JButton btnExit = new JButton("Exit");

		// constraints

		lblStatus.setToolTipText("Contact help desk to unlock password");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
 
		// ADD OBJECTS TO FRAME
		add(lblUsername);  // 1st row filler
		add(txtUname);
		add(lblPassword); // 2nd row
		add(txtPassword);
		add(btn);         // 3rd row
		add(btnExit);
		add(lblStatus);   // 4th row

		btn.addActionListener(new ActionListener() {
			int count = 0; // count agent

			// suppress warning
			@SuppressWarnings("deprecation")
			// Override
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean admin = false;
				count = count + 1;
				// verify credentials of user (MAKE SURE TO CHANGE TO YOUR TABLE NAME BELOW)

				// prepared statement
				String query = "SELECT * FROM jrobe_users WHERE uname = ? and upass = ?;";
				// try starts
				try (PreparedStatement stmt = conn.getConnection().prepareStatement(query)) {
					stmt.setString(1, txtUname.getText());
					stmt.setString(2, txtPassword.getText());
					ResultSet rs = stmt.executeQuery();
					// if starts
					if (rs.next()) {
						admin = rs.getBoolean("admin"); // get table column value
						System.out.println("Is admin: " + admin);
						new Tickets(admin, txtUname.getText());
						setVisible(false); // HIDE THE FRAME
						dispose(); // CLOSE OUT THE WINDOW
					} // if ends
					// else starts
					else {
						lblStatus.setText("Try again! " + (3 - count) + " / 3 attempts left");
						// if starts
						if (count == 3) {
							System.out.println("Too many wrong attemps\nClosing Program...");
							System.exit(0); // ends program after too many failed login attempts
						} // if ends
					} // else ends
				} // try ends 
				catch (SQLException ex) {
					ex.printStackTrace();
				}
 			 
			}
		});
		// exit button
		btnExit.addActionListener(e -> System.exit(0));

		setVisible(true); // SHOW THE FRAME
	} // Login ends

	// main starts
	public static void main(String[] args) {

		new Login();
	} //main ends
} // class ends

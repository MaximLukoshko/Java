package main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPasswordBase {

	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost/users";
	private static final String user = "maxim";
	private static final String password = "maxim";

	// DataBase Variables
	private static String table = "authentic_data";
	@SuppressWarnings("unused")
	private static String idField = "id";
	private static String userNameField = "userName";
	private static String userPasswordField = "userPassword";
	@SuppressWarnings("unused")
	private static String userOnlineStatusField = "userNameOnlineStatus";

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;

	public UserPasswordBase() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static boolean authorize(String userName, String userPassword) {
		boolean result = false;
		;
		try {
			String query = "select * from " + table;
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);

			// getting Statement object to execute query
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			result = findUser(rs, userName, userPassword);

			con.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				rs.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}
		return result;
	}

	private static boolean findUser(ResultSet rs2, String userName, String userPassword) throws SQLException {
		boolean found = false;
		while (rs2.next()) {
			System.out.println(rs2.getString(userNameField));
			if (rs2.getString(userNameField).equals(userName)) {
				found = true;
				break;
			}
		}
		if (found && rs2.getString(userPasswordField).equals(userPassword)) {
			setOnline(userName);
			return true;
		} else if (found) {
			return false;
		} else {
			addOnlineUser(userName, userPassword);
			return true;
		}
	}

	private static void addOnlineUser(String userName, String userPassword) {
		// TODO Auto-generated method stub

	}

	private static void setOnline(String userName) {
		// TODO Auto-generated method stub

	}

	public static void logOut(String username) {
		// TODO Auto-generated method stub

	}

}

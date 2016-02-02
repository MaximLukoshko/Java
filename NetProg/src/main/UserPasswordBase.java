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
	private static String userOnlineStatusField = "userOnlineStatus";

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
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			result = findUser(userName, userPassword);
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

	private static boolean findUser(String userName, String userPassword) throws SQLException {
		String query = "select * from " + table;
		rs = stmt.executeQuery(query);
		boolean found = false;
		while (rs.next()) {
			System.out.println(rs.getString(userNameField));
			if (rs.getString(userNameField).equals(userName)) {
				found = true;
				break;
			}
		}
		if (found && rs.getString(userPasswordField).equals(userPassword)) {
			if (rs.getBoolean(userOnlineStatusField)) {
				return false;
			} else {
				setOnline(userName);
				return true;
			}
		} else if (found) {
			return false;
		} else {
			addOnlineUser(userName, userPassword);
			return true;
		}
	}

	private static void addOnlineUser(String userName, String userPassword) throws SQLException {
		String addQuery = "insert into authentic_data values (null, '" + userName + "', '" + userPassword + "', '1')";
		stmt.executeUpdate(addQuery);
	}

	private static void setOnline(String userName) {
		// TODO Auto-generated method stub
	}

	public static void logOut(String username) {
		setOffline(username);
	}

	private static void setOffline(String username) {
		// TODO Auto-generated method stub

	}

}

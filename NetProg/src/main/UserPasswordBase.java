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
	private static final String idField = "id";
	private static final String userNameField = "userName";
	private static final String userPasswordField = "userPassword";
	private static final String userOnlineStatusField = "userOnlineStatus";

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
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			String refreshQuery = "update " + table + " set " + userOnlineStatusField + "='0'";
			stmt.executeUpdate(refreshQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean authorize(String userName, String userPassword) {
		boolean userExist = false;
		try {
			userExist = findUser(userName, userPassword);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return userExist;
	}

	private static boolean findUser(String userName, String userPassword) throws SQLException {
		String query = "select * from " + table + " where " + userNameField + "='" + userName + "'";
		rs = stmt.executeQuery(query);
		boolean found = rs.first();

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

	private static void setOnline(String userName) throws SQLException {
		String setOnlineQuerry = "update " + table + " set " + userOnlineStatusField + "='1'" + " where "
				+ userNameField + "='" + userName + "'";
		stmt.executeUpdate(setOnlineQuerry);
	}

	public static void logOut(String userName) throws SQLException {
		setOffline(userName);
	}

	private static void setOffline(String userName) throws SQLException {
		String setOfflineQuerry = "update " + table + " set " + userOnlineStatusField + "='0'" + " where "
				+ userNameField + "='" + userName + "'";
		stmt.executeUpdate(setOfflineQuerry);
	}

	public void stopConnection() {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

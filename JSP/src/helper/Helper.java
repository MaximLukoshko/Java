package helper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Helper {
	private static Connection conn;

	public Helper() throws NamingException {
		super();
		if (conn == null) {
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/jsp");
			try {
				conn = ds.getConnection();
				System.out.println("Server is started");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//
//	public static void main(String[] args) {
//		try {
//			Helper helper = new Helper();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}

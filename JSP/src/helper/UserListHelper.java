package helper;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import entity.UserList;

public abstract class UserListHelper extends Helper {

	public UserListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static UserList readUserList(ServletContext servletContext) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void saveUserList(UserList users) {
		// TODO Auto-generated method stub

	}
}

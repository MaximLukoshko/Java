package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.User;
import entity.UserList;

public class Login extends SimpleTagSupport {
	// Поле данных для атрибута login
	private String login;

	// Поле данных для атрибута password
	private String password;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String errorMessage = null;
		UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
		if (login == null || login.equals("")) {
			errorMessage = "Login can not be empty!";
		} else {
			User user = userList.findUser(login);
			if (user == null || !user.getPassword().equals(password)) {
				errorMessage = "Check login/passowrd";
			} else {
				getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
			}
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
	}

}

package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.User;
import entity.UserList;
import entity.UserList.UserExistsException;
import helper.UserListHelper;

public class AddUser extends SimpleTagSupport {

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Изначально описание ошибки = null (т.е. ошибки нет)
		String errorMessage = null;
		// Извлечь из контекста приложения общий список пользователей
		UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
		// Проверить, что логин не пустой
		if (user.getLogin() == null || user.getLogin().equals("")) {
			errorMessage = "Login cannot be empty!";
		} else {
			// Проверить, что имя не пустое
			if (user.getName() == null || user.getName().equals("")) {
				errorMessage = "User name can not be empty!";
			}
		}
		// Если ошибки не было - добавить пользователя
		if (errorMessage == null) {
			try {
				// Непосредственное добавление пользователя делает UserList
				userList.addUser(user);
				// Записать обновлѐнный список пользователей в файл
				UserListHelper.saveUserList(userList);
			} catch (UserExistsException e) {
				// Ошибка - пользователь с таким логином уже
				errorMessage = "such username is already used!";
			}
		}
		// Сохранить описание ошибки (текст или null) в сессии
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);

	}

}

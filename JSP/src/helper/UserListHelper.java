package helper;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletContext;

import entity.UserList;

public abstract class UserListHelper extends Helper {
	private static final String USERS_FILENAME = "WEB-INF/users.dat";

	private static String USERS_PATH = null;

	public UserListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static UserList readUserList(ServletContext servletContext) {
		try {
			// Определяем физический путь к файлу
			USERS_PATH = servletContext.getRealPath(USERS_FILENAME);
			// Создаем объектный поток ввода на основе файлового потока
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERS_PATH));
			return (UserList) in.readObject();
			// return (UserList)in.readObject();
		} catch (Exception e) {
			// Если возникли проблемы с чтением из файла, возвращаем
			// пустой список
			return new UserList();
		}
	}

	public static void saveUserList(UserList users) {
		// TODO Auto-generated method stub
		synchronized (users) {
			try {
				// Создаем объектный поток вывода на основе файлового
				// потока
				@SuppressWarnings("resource")
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
				// Записываем содержимое объекта в поток
				out.writeObject(users);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

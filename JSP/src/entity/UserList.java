package entity;

@SuppressWarnings("serial")
public class UserList extends ListOfIdentifiables<User> {
	public synchronized User findUser(String login) {
		// Ищем пользователя с заданным логином
		for (User user : items) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}

	public synchronized User findUser(Integer id) {
		// Ищем пользователя с заданным идентификатором
		for (User user : items) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public synchronized User addUser(User user) throws UserExistsException {
		// Если пользователь с данным логином уже зарегистрирован,
		// рируем исключение
		if (findUser(user.getLogin()) != null)
			throw new UserExistsException();
		// Выбрать следующий незанятый id для автора
		user.setId(getNextId());
		// Добавить автора в список
		items.add(user);
		return user;
	}

	public static class UserExistsException extends Exception {

	}

}
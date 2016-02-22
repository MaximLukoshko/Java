package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entity.Ad;
import entity.AdList;
import entity.UserList;
import helper.AdListHelper;
import helper.UserListHelper;

/**
 * Servlet implementation class StartupServlet
 */
@WebServlet("/StartupServlet.do")
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Загрузить список пользователей
		UserList userList = UserListHelper.readUserList(getServletContext());
		// Сохранить список пользователей в контексте сервлета
		// (для JSP это тождественно равно applicationContext)
		getServletContext().setAttribute("users", userList);
		// Загрузить список сообщений
		AdList adList = AdListHelper.readAdList(getServletContext());
		// Сохранить список объявлений в контексте сервлета
		// (для JSP это тождественно равно applicationContext)
		getServletContext().setAttribute("ads", adList);
		for (Ad ad : adList.getAds()) {
			// Т.к. в сообщениях изначально присутствует только id
			// автора, для удобства установим ссылки
			ad.setAuthor(userList.findUser(ad.getAuthorId()));
			// Инициализировать значения свойства lastModifiedDate
			ad.setLastModified(ad.getLastModified());
		}
		System.out.println("Servlet is loaded");
	}
	
}

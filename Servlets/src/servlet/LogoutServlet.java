package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatUser;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout.do")
public class LogoutServlet extends WebChatServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LogoutServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getSession().getAttribute("name");
		if (name != null) {
			ChatUser aUser = activeUsers.get(name);
			if (aUser.getSessionId().equals(
					(String) request.getSession().getId())) {
				synchronized (activeUsers) {
					activeUsers.remove(name);
				}
				request.getSession().setAttribute("name", null);
				response.addCookie(new Cookie("sessionId", null));
				response.sendRedirect(response.encodeRedirectURL(""));
			} else {
				response.sendRedirect(response.encodeRedirectURL("view.html"));
			}
		} else {
			response.sendRedirect(response.encodeRedirectURL("view.html"));
		}

	}
}

package servlet;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entity.ChatMessage;
import entity.ChatUser;

/**
 * Servlet implementation class WebChatServlet
 */
@WebServlet("/WebChatServlet")
public class WebChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */

	HashMap<String, ChatUser> activeUsers;
	ArrayList<ChatMessage> messages;

	public void init(ServletConfig config) throws ServletException {
		super.init();
		activeUsers = (HashMap<String, ChatUser>) getServletContext()
				.getAttribute("activeUsers");
		messages = (ArrayList<ChatMessage>) getServletContext().getAttribute(
				"messages");
		if (activeUsers == null) {
			activeUsers = new HashMap<String, ChatUser>();
			getServletContext().setAttribute("activeUsers", activeUsers);
		}
		if (messages == null) {
			messages = new ArrayList<ChatMessage>();
			getServletContext().setAttribute("messages", messages);
		}
	}

}

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

	protected HashMap<String, ChatUser> activeUsers;
	protected ArrayList<ChatMessage> messages;

	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		super.init();
		activeUsers = (HashMap<String, ChatUser>) getServletContext()
				.getAttribute("activeUsers");
		messages = (ArrayList<ChatMessage>) getServletContext().getAttribute(
				"messages");
		if (activeUsers == null) {
			activeUsers = new HashMap<String, ChatUser>();
			getServletContext().setAttribute("activeUsers", activeUsers);
			activeUsers
					.put("Admin", new ChatUser("Admin", 0, "0", "red"));
		}
		if (messages == null) {
			messages = new ArrayList<ChatMessage>();
			getServletContext().setAttribute("messages", messages);
			messages.add(new ChatMessage("Hi, everybody", activeUsers
					.get("Admin"), 0, activeUsers.get("Admin")
					.getLettersColour()));
		}
	}

}

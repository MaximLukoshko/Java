package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatMessage;

/**
 * Servlet implementation class NewMessageServlet
 */
@WebServlet("/send_message.do")
public class NewMessageServlet extends WebChatServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public NewMessageServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String) request.getParameter("name");
		String message = (String) request.getParameter("message");
		if (name == null || name == "") {
			PrintWriter pw = response.getWriter();
			pw.println("You have to log in.(");
			// response.sendRedirect("Login.html");
		} else {
			if (message != null && !message.equals("")) {
				synchronized (messages) {
					ChatMessage currentMessage;
					messages.add(new ChatMessage(message, activeUsers
							.get((String) request.getSession().getAttribute(
									"name")), Calendar.getInstance()
							.getTimeInMillis()));
				}
			}
			response.sendRedirect("compose_message.html");
		}
	}

}

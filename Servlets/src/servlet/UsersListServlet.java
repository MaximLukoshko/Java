package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatUser;

/**
 * Servlet implementation class UsersListServlet
 */
@WebServlet("/users.do")
public class UsersListServlet extends WebChatServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UsersListServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
		pw.println("<body>");
		response.setCharacterEncoding("utf8");
		for (ChatUser user : activeUsers.values()) {
			pw.println("<div>" + "<strong>" + user.getName() + "</strong>"
					+ "</div>");
		}
	}

}

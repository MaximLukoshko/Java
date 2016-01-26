package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatMessage;

/**
 * Servlet implementation class MessageListServlet
 */
@WebServlet("/messages.do")
public class MessageListServlet extends WebChatServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MessageListServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
		pw.println("<body>");
		response.setCharacterEncoding("utf8");
		synchronized (messages) {
			for (int i = messages.size() - 1; i >= 0; i--) {
				ChatMessage aMessage = messages.get(i);
				pw.println("<font color=" + aMessage.getMessageColour() + ">"
						+ "<div><strong>" + aMessage.getAuthor().getName()
						+ "</strong>: " + aMessage.getMessage() + "</div>"
						+ "</font>");
			}
		}
		pw.println("</body></html>");

	}
}

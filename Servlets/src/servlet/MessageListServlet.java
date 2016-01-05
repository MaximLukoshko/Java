package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		// Установить кодировку HTTP-ответа UTF-8
		response.setCharacterEncoding("utf8");
		// Получить доступ к потоку вывода HTTP-ответа
		PrintWriter pw = response.getWriter();
		// Записть в поток HTML-разметку страницы
		pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
		pw.println("<body>");
		// В обратном порядке записать в поток HTML-разметку для каждого
		// сообщения
		for (int i = 3; i >= 0; i--) {
			pw.print("<div>" + "Hello" + "</div>");
		}
	}
}

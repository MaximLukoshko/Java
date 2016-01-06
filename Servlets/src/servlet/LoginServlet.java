package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login.do" }, initParams = { @WebInitParam(name = "SESSION_TIMEOUT", value = "3600") })
public class LoginServlet extends WebChatServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	private int sessionTimeout = 600;

	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		super.init();
		String value = getServletConfig().getInitParameter("SESSION_TIMEOUT");
		if (value != null) {
			sessionTimeout = Integer.parseInt(value);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String) request.getParameter("name");

		if (name == null || name.equals("")) {
			System.out.print(1);
			response.sendRedirect("Login.html");
//			response.setCharacterEncoding("utf-8");
//			PrintWriter pw = response.getWriter();
//			pw.println("<html><head><title>Mega-Chat</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head>");
//
//			pw.println("<form action='login.do' method='get'>Enter your name:<input type='text' name='name' value=''><input type='submit' value='Log in chat'>");
//			pw.println("</form></body></html>");

		} else {
			response.sendRedirect("view.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

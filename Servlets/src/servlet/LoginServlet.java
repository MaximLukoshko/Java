package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login.do" }, initParams = { @WebInitParam(name = "SESSION_TIMEOUT", value = "600") })
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
		String name = (String) request.getSession().getAttribute("name");
		String errorMessage = (String) request.getSession().getAttribute(
				"error");
		String previousSessionId = null;
		if (name != null) {
			for (Cookie aCookie : request.getCookies()) {
				if (aCookie.getName().equals("sessionId")) {
					previousSessionId = aCookie.getValue();
					break;
				}
			}
			if (previousSessionId != null) {
				for (ChatUser aUser : activeUsers.values()) {
					if (aUser.getSessionId().equals(previousSessionId)) {
						name = aUser.getName();
						aUser.setSessionId(request.getSession().getId());
					}
				}
			}
		}
		if (name != null && !"".equals(name)) {
			errorMessage = processLogonAttempt(name, request, response);
		}
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>Mega-Chat</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head>");
		if (errorMessage != null) {
			pw.println("<p><font color='red'>" + errorMessage + "</font></p>");
		}
		pw.println("<form action='login.do' method='post'>Enter your name:<input type='text' name='name' value=''><input type='submit' value='Log in'>");
		pw.println("</form></body></html>");
		request.getSession().setAttribute("error", null);

	}

	private String processLogonAttempt(String name, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String sessionId = request.getSession().getId();
		ChatUser aUser = activeUsers.get(name);
		if (aUser == null) {
			aUser = new ChatUser(name,
					Calendar.getInstance().getTimeInMillis(), sessionId);
			synchronized (activeUsers) {
				activeUsers.put(aUser.getName(), aUser);
			}
		}
		if (aUser.getSessionId().equals(sessionId)
				|| aUser.getLastInteractionTime() < (Calendar.getInstance()
						.getTimeInMillis() - sessionTimeout * 1000)) {
			request.getSession().setAttribute("name", name);
			aUser.setLastInteractionTime(Calendar.getInstance()
					.getTimeInMillis());
			Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
			sessionIdCookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(sessionIdCookie);
			response.sendRedirect(response.encodeRedirectURL("view.html"));
			return null;
		} else {
			return "Sorry, but name <strong>" + name
					+ "</strong> is used. Please, choose another name!";
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = (String) request.getParameter("name");
		String errorMessage = null;
		if (name == null || "".equals(name)) {
			errorMessage = "An User name can not be empty!";
		} else {
			errorMessage = processLogonAttempt(name, request, response);
		}
		if (errorMessage != null) {
			request.getSession().setAttribute("name", null);
			request.getSession().setAttribute("error", errorMessage);
			response.sendRedirect(response.encodeRedirectURL(""));
		}
	}

}

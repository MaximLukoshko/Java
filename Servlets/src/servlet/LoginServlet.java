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
		String colour = (String) request.getSession().getAttribute("color");
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
						colour = aUser.getLettersColour();
						aUser.setSessionId(request.getSession().getId());
					}
				}
			}
		}
		if (name != null && !"".equals(name)) {
			errorMessage = processLogonAttempt(name, colour, request, response);
		}
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>Mega-Chat</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head>");
		if (errorMessage != null) {
			pw.println("<p><font color='red'>" + errorMessage + "</font></p>");
		}
		pw.println("<form action='login.do' method='post'>Enter your name:<input type='text' name='name' value=''><input type='submit' value='Log in'>");
		pw.println("<p><b>Choose your colour:</b><Br>");
		pw.println("<input type='radio' checked name='color' value='black'> black<Br>");
		pw.println("<input type='radio' name='color' value='green'> <font color='green'>green</font><Br>");
		pw.println("<input type='radio' name='color' value='blue'> <font color='blue'>blue</font><Br>");
		pw.println("<input type='radio' name='color' value='aqua'> <font color='aqua'>aqua</font><Br>");
		pw.println("<input type='radio' name='color' value='fuchsia'> <font color='fuchsia'>fuchsia</font><Br>");
		pw.println("<input type='radio' name='color' value='gray'> <font color='grey'>grey</font><Br>");
		pw.println("<input type='radio' name='color' value='lime'> <font color='lime'>lime</font><Br>");
		pw.println("<input type='radio' name='color' value='maroon'> <font color='maroon'>maroon</font><Br>");
		pw.println("<input type='radio' name='color' value='navy'> <font color='navy'>navy</font><Br>");
		pw.println("<input type='radio' name='color' value='olive'> <font color='olive'>olive</font><Br>");
		pw.println("<input type='radio' name='color' value='purple'> <font color='purple'>purple</font><Br>");
		pw.println("<input type='radio' name='color' value='silver'> <font color='silver'>silver</font><Br>");
		pw.println("<input type='radio' name='color' value='teal'> <font color='teal'>teal</font><Br>");
		pw.println("<input type='radio' name='color' value='yellow'> <font color='yellow'>yellow</font><Br>");
		pw.println("</p>");
		pw.println("</form></body></html>");
		request.getSession().setAttribute("error", null);

	}

	private String processLogonAttempt(String name, String colour,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String sessionId = request.getSession().getId();
		ChatUser aUser = activeUsers.get(name);
		if (aUser == null) {
			aUser = new ChatUser(name,
					Calendar.getInstance().getTimeInMillis(), sessionId, colour);
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
		String colour = (String) request.getParameter("color");
		String errorMessage = null;
		if (name == null || "".equals(name)) {
			errorMessage = "An User name can not be empty!";
		} else {
			errorMessage = processLogonAttempt(name, colour, request, response);
		}
		if (errorMessage != null) {
			request.getSession().setAttribute("name", null);
			request.getSession().setAttribute("error", errorMessage);
			response.sendRedirect(response.encodeRedirectURL(""));
		}
	}

}

package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ClearErrorMessage extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().setAttribute("errorMessage", null, PageContext.SESSION_SCOPE);
	}

}

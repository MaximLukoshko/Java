package helper;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import entity.AdList;

public abstract class AdListHelper extends Helper{

	public AdListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static AdList readAdList(ServletContext servletContext) {

		return null;
	}

	public static void saveAdList(AdList ads) {
		// TODO Auto-generated method stub

	}
}

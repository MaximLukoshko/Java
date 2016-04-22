package tag;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.Ad;
import entity.AdList;

public class FindAd extends SimpleTagSupport {
	private String key;
	// Поле данных для атрибута var (контейнер результата)
	private String var;

	public void setVar(String var) {
		this.var = var;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Извлечь из контекста приложения общий список объявлений
		final AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);

		// System.out.println("KEY: " + key);

		// В этом списке будут содержаться только отобранные объявления
		ArrayList<Ad> sortedList = new ArrayList<Ad>();

		for (Ad ad : adList.getAds()) {
			sortedList.add(ad);
		}

		getJspContext().setAttribute(FindAd.this.var, sortedList, PageContext.PAGE_SCOPE);
	}

}

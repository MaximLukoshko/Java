package tag;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.Ad;
import entity.AdList;

public class FindAd extends SimpleTagSupport {
	private final String badSymbols = ".,!?_";

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

		ArrayList<Ad> sortedList = new ArrayList<Ad>();
		if (key.equals("")) {
			for (Ad ad : adList.getAds()) {
				sortedList.add(ad);
			}
		} else {

			for (int i = 0; i < badSymbols.length(); i++) {
				key = key.replace(badSymbols.charAt(i), ' ');
				System.out.println("Sym " + badSymbols.substring(i, i + 1));
			}

			System.out.println("KEY: " + key);

			String[] keyWords = key.split(" ");

			for (Ad ad : adList.getAds()) {
				boolean needToAd = true;

				for (String keyWord : keyWords) {
					if ((ad.getSubject().toLowerCase().indexOf(keyWord.toLowerCase()) == -1)
							&& (ad.getBody().toLowerCase().indexOf(keyWord.toLowerCase()) == -1)) {
						needToAd = false;
						break;
					}
				}

				if (needToAd) {
					sortedList.add(ad);
				}
			}
		}
		getJspContext().setAttribute(FindAd.this.var, sortedList, PageContext.PAGE_SCOPE);
	}

}

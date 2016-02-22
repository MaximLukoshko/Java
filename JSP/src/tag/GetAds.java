package tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.Ad;
import entity.AdList;
import entity.User;

public class GetAds extends SimpleTagSupport {
	private int id = 0;
	// Поле данных для атрибута range (диапазон объявлений)
	private String range;
	// Поле данных для атрибута sort (основание сортировки)
	private String sort;
	// Поле данных для атрибута dir (направление сортировки)
	private char dir;
	// Поле данных для атрибута var (контейнер результата)
	private String var;

	public void setId(int id) {
		this.id = id;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Извлечь из контекста приложения общий список объявлений
		final AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
		if (id > 0) {
			for (Ad ad : adList.getAds()) {
				if (ad.getId() == id) {
					getJspContext().setAttribute(GetAds.this.var, ad, PageContext.PAGE_SCOPE);
				}
			}
		} else {
			final User authUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
			// В этом списке будут содержаться только отобранные объявления
			ArrayList<Ad> sortedList = new ArrayList<Ad>();

			for (Ad ad : adList.getAds()) {
				// Если режим фильтрации собственные сообщений выключен
				// или текущее объявление принадлежит пользователю
				if (!"my".equals(range) || (authUser != null && authUser.getId() == ad.getAuthorId())) {
					// Добавить объявление в список
					sortedList.add(ad);
				}
			}
			Comparator<Ad> comparator = new Comparator<Ad>() {

				@Override
				public int compare(Ad ad1, Ad ad2) {
					int result = 0;
					if (GetAds.this.sort != null && GetAds.this.sort.equals("date")) {
						result = ad1.getLastModified() < ad2.getLastModified() ? -1
								: ad1.getLastModified() > ad2.getLastModified() ? 1 : 0;
					} else if (GetAds.this.sort != null && GetAds.this.sort.equals("subject")) {
						result = ad1.getSubject().compareTo(ad2.getSubject());
					} else {
						result = ad1.getAuthor().getName().compareTo(ad2.getAuthor().getName());
					}

					if (GetAds.this.dir == 'd') {
						result = -result;
					}

					return result;
				}

			};
			if (sortedList.size() == 0) {
				sortedList = null;
			} else {
				Collections.sort(sortedList, comparator);
			}

			getJspContext().setAttribute(GetAds.this.var, sortedList, PageContext.PAGE_SCOPE);
		}
	}
}

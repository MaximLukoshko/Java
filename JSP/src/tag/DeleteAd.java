package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import entity.Ad;
import entity.AdList;
import entity.User;
import helper.AdListHelper;

public class DeleteAd extends SimpleTagSupport {

	// Поле данных для атрибута
	private Ad ad;

	// Метод-сеттер для установки атрибута (вызывается контейнером)
	public void setAd(Ad ad) {
		this.ad = ad;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Изначально описание ошибки = null (т.е. ошибки нет)
		String errorMessage = null;
		// Извлечь из контекста приложения общий список объявлений
		AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
		// Извлечь из сессии описание текущего пользователя
		User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
		// Проверить, что объявление изменяется его автором, а не чужаком
		if (currentUser == null || (ad.getId() > 0 && currentUser.getId() != ad.getAuthorId())) {
			errorMessage = "You can not change this add";
		}
		if (errorMessage == null) {
			adList.deleteAd(ad);
			AdListHelper.saveAdList(adList);
		}
		getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);

	}

}

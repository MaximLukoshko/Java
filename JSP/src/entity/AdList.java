package entity;

import java.util.HashSet;

@SuppressWarnings("serial")
public class AdList extends ListOfIdentifiables<Ad> {

	// Выполняет добавление нового объявления с автоматическим присвоением
	// идентификационного номера. Метод синхронизирован.
	public synchronized Ad addAd(User author, Ad ad) {
		// Связать автора с объявлением
		ad.setAuthorId(author.getId());
		ad.setAuthor(author);
		// Выбрать следующий незанятый id для объявления
		ad.setId(getNextId());
		// Добавить сообщение в список
		items.add(ad);
		return ad;
	}

	// Обновляет объявление
	public synchronized void updateAd(Ad ad) {
		items.add(ad);
	}

	// Удаляет объявление
	public synchronized void deleteAd(Ad ad) {
		items.remove(ad);
	}

	// Вовзращает копию содержимого списка объявлений. Метод
	// онизирован.
	@SuppressWarnings("unchecked")
	public synchronized HashSet<Ad> getAds() {
		// Клонируем объект в целях обеспечения синхронизации
		return (HashSet<Ad>) items.clone();
	}
}

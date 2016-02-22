package helper;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;

import entity.AdList;

public abstract class AdListHelper extends Helper{
	// Логический путь к файлу, в котором хранятся данные об объявлени
	private static final String ADS_FILENAME = "WEB-INF/ads.dat";
	// Полный путь к файлу, в котором хранятся данные об объявлениях
	private static String ADS_PATH = null;

	public AdListHelper() throws NamingException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static AdList readAdList(ServletContext servletContext) {
		try {
			// Определяем физический путь к файлу
			ADS_PATH = servletContext.getRealPath(ADS_FILENAME);
			// Создаем объектный поток ввода на основе файлового потока
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(ADS_PATH));
			return (AdList) in.readObject();
		} catch (Exception e) {
			// Если возникли проблемы с чтением из файла, возвращаем
			// пустой список
			return new AdList();
		}

	}

	public static void saveAdList(AdList ads) {
		// TODO Auto-generated method stub
		synchronized (ads) {
			try {
				// Создаем объектный поток вывода на основе файлового
				// потока
				@SuppressWarnings("resource")
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADS_PATH));
				// Записываем содержимое объекта в поток
				out.writeObject(ads);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

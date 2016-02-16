package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Ad implements Identifiable, Serializable {

	private int id = 0;
	// Заголовок сообщения
	private String subject = "";
	// Текст сообщения
	private String body = "";
	// Автор сообщения (id)
	private int authorId;
	// Автор сообщения (ссылка, не сериализуется)
	transient private User author;
	// Последнее время модификации сообщения
	private Long lastModified;
	// Последнее время модификации сообщения как объект Date
	transient private Date lastModifiedDate;

	public Ad() {
		lastModified = Calendar.getInstance().getTimeInMillis();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
		lastModifiedDate = new Date(lastModified);

	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public int getId() {
		return id;
	}

}

package entity;

public class ChatMessage {
	private String message;
	private ChatUser author;
	private long timestamp;
	private String messageColour;

	public ChatMessage(String message, ChatUser author, long timestamp,
			String messageColour) {
		super();
		this.message = message;
		this.author = author;
		this.timestamp = timestamp;
		this.messageColour = messageColour;
	}

	public String getMessageColour() {
		return messageColour;
	}

	public void setMessageColour(String messageColour) {
		this.messageColour = messageColour;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChatUser getAuthor() {
		return author;
	}

	public void setAuthor(ChatUser author) {
		this.author = author;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}

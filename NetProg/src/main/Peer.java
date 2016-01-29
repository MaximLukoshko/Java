package main;

public class Peer {
	String senderName;
	String address;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Peer(String senderName, String address) {
		super();
		this.senderName = senderName;
		this.address = address;
	}

}

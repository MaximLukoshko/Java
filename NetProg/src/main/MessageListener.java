package main;

import java.io.IOException;

public class MessageListener {
	private MainFrame frame;
	private static int CURRENT_FREE_IP = 1;
	private int IP;
	private String name;

	public MessageListener(MainFrame frame) {
		super();
		IP = CURRENT_FREE_IP;
		CURRENT_FREE_IP++;
		this.frame = frame;
		this.frame.getInstantMessenger().addMessageListener(this);
	}

	public String getIP() {
		return new String("127.0.0." + IP);
	}

	void messageReceived(Peer sender, String message) throws IOException {
		frame.appendIncoming(sender.toString() + ": " + message + "\n");
		frame.clearOutgoing();
	}

	public String getName() {
		return name;
	}

	public void setName(String text) {
		name = text;
	}
}

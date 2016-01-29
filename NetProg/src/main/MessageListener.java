package main;

import java.io.IOException;

public class MessageListener {
	MainFrame frame;

	public MessageListener(MainFrame frame) {
		super();
		this.frame = frame;
		this.frame.getInstantMessenger().addMessageListener(this);
	}

	void messageReceived(Peer sender, String message) throws IOException {
		frame.appendIncoming(sender.toString() + ": " + message + "\n");
		frame.clearOutgoing();
	}
}

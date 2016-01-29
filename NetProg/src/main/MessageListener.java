package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

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

package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageListener {
	MainFrame frame;

	public MessageListener(MainFrame frame) {
		super();
		this.frame = frame;
		this.frame.getInstantMessenger().addMessageListener(this);
	}

	void messageReceived(String senderName, String message) throws IOException {
		final Socket socket = frame.getInstantMessenger().getSocket();
		final DataInputStream in = new DataInputStream(socket.getInputStream());
		senderName = in.readUTF();
		message = in.readUTF();
		socket.close();
		frame.appendIncoming(senderName + " (" + /* address + */"): " + message
				+ "\n");
		frame.clearOutgoing();
		// final String address = ((InetSocketAddress) socket
		// .getRemoteSocketAddress()).getAddress().getHostAddress();
	}
}

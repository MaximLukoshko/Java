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
		try {
			final Socket socket = frame.getInstantMessenger().getSocket();
			final DataInputStream in = new DataInputStream(
					socket.getInputStream());
			sender.setSenderName(in.readUTF());
			message = in.readUTF();
			socket.close();
			sender.setAddress(((InetSocketAddress) socket
					.getRemoteSocketAddress()).getAddress().getHostAddress());
			frame.appendIncoming(sender.getSenderName() + " ("
					+ sender.getAddress() + "): " + message + "\n");
			frame.clearOutgoing();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error while working server",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

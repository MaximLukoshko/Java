package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InstantMessenger {
	private static final int SERVER_PORT = 4567;
	private ArrayList<MessageListener> listeners;

	public InstantMessenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sendMessage(String senderName, String destinationAddress,
			String message) throws UnknownHostException, IOException {
		final Socket socket = new Socket(destinationAddress, SERVER_PORT);

		final DataOutputStream out = new DataOutputStream(
				socket.getOutputStream());
		out.writeUTF(senderName);
		out.writeUTF(message);
		socket.close();
	}

	@SuppressWarnings("unused")
	private void startServer() {

	}

	public void addMessageListener(MessageListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeMessageListener(MessageListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	@SuppressWarnings("unused")
	private void notifyListeners(Peer sender, String message) {
		synchronized (listeners) {
			for (MessageListener listener : listeners) {
				listener.messageReceived(sender, message);
			}
		}
	}

}

package main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InstantMessenger {
	private ArrayList<MessageListener> listeners;

	public InstantMessenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sendMessage(String senderName, String destinationAddress,
			String message) throws UnknownHostException, IOException {

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
	private void notifyListeners(String sender, String message) {
		synchronized (listeners) {
			for (MessageListener listener : listeners) {
				listener.messageReceived(sender, message);
			}
		}
	}

}

package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InstantMessenger {
	private static final int SERVER_PORT = 4567;
	private ArrayList<MessageListener> listeners;
	private ServerSocket serverSocket;

	public InstantMessenger() throws IOException {
		super();
		listeners = new ArrayList<MessageListener>();
		startServer();
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

	private void startServer() throws IOException {
		serverSocket = new ServerSocket(SERVER_PORT);
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

	public void notifyListeners(String sender, String message)
			throws IOException {
		synchronized (listeners) {
			for (MessageListener listener : listeners) {
				listener.messageReceived(sender, message);
			}
		}
	}

	public Socket getSocket() throws IOException {
		return serverSocket.accept();
	}

}

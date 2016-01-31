package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InstantMessenger {
	private static final int SERVER_PORT = 4567;
	private ArrayList<MessageListener> listeners;
	private static ServerSocket serverSocket;

	public InstantMessenger() throws IOException {
		super();
		listeners = new ArrayList<MessageListener>();
		startServer();
	}

	public void sendMessage(Peer sender, String message, Peer recepient) throws UnknownHostException, IOException {

		if (recepient.getAddress().isEmpty()) {
			if ((recepient.getSenderName().toLowerCase()).equals("all")) {
				recepient.setAddress(sender.getAddress());
			} else {
				for (MessageListener messageListener : listeners) {
					if (recepient.getSenderName().equals(messageListener.getName())) {
						recepient.setAddress(messageListener.getIP());
					}
				}
			}
		}

		final Socket socket = new Socket(recepient.getAddress(), SERVER_PORT);
		final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeUTF(sender.getSenderName());
		out.writeUTF(sender.getAddress());
		out.writeUTF(message);
		out.writeUTF(recepient.getSenderName());
		out.writeUTF(recepient.getAddress());
		socket.close();
	}

	private void startServer() throws IOException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(SERVER_PORT);

					while (!Thread.interrupted()) {
						final Socket socket = serverSocket.accept();
						final DataInputStream in = new DataInputStream(socket.getInputStream());
						Peer sender = new Peer(null, null);
						Peer recepient = new Peer(null, null);
						sender.setSenderName(in.readUTF());
						sender.setAddress(in.readUTF());
						// sender.setAddress(
						// ((InetSocketAddress)
						// socket.getRemoteSocketAddress()).getAddress().getHostAddress());
						String message = new String();
						message = in.readUTF();
						recepient.setSenderName(in.readUTF());
						recepient.setAddress(in.readUTF());
						socket.close();
						notifyListeners(sender, message, recepient);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
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

	public void notifyListeners(Peer sender, String message, Peer recepient) throws IOException {
		synchronized (listeners) {
			for (MessageListener listener : listeners) {
				if (listener.getIP().equals(recepient.getAddress())
						|| recepient.getSenderName().toLowerCase().equals("all")) {
					listener.messageReceived(sender, message);
				}
			}
		}
	}

	public Socket getSocket() throws IOException {
		return serverSocket.accept();
	}
}

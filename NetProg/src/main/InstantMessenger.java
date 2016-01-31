package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
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

	public void sendMessage(Peer sender, String message)
			throws UnknownHostException, IOException {
		final Socket socket = new Socket(sender.getAddress(), SERVER_PORT);
		final DataOutputStream out = new DataOutputStream(
				socket.getOutputStream());
		out.writeUTF(sender.getSenderName());
		out.writeUTF(message);
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
						final DataInputStream in = new DataInputStream(
								socket.getInputStream());
						Peer sender = new Peer(null, null);
						sender.setSenderName(in.readUTF());
						sender.setAddress(((InetSocketAddress) socket
								.getRemoteSocketAddress()).getAddress()
								.getHostAddress());
						String message = new String();
						message = in.readUTF();
						socket.close();
						notifyListeners(sender, message);
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

	public void notifyListeners(Peer sender, String message) throws IOException {
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

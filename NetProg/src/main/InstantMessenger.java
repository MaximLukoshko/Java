package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InstantMessenger {
	private static final int SERVER_PORT = 4567;
	private ArrayList<MessageListener> listeners;
	private static ServerSocket serverSocket;

	private static UserPasswordBase userPasswordBase;

	public InstantMessenger() throws IOException {
		super();
		listeners = new ArrayList<MessageListener>();
		userPasswordBase = new UserPasswordBase();
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
					ExecutorService executorService = Executors.newCachedThreadPool();
					// ExecutorService executorService = Executors
					// .newFixedThreadPool(2);
					while (!Thread.interrupted()) {
						final Socket socket = serverSocket.accept();
						executorService.submit(new Runnable() {

							@Override
							public void run() {
								try {
									DataInputStream in = new DataInputStream(socket.getInputStream());
									Peer sender = new Peer(null, null);
									Peer recepient = new Peer(null, null);
									sender.setSenderName(in.readUTF());
									sender.setAddress(in.readUTF());
									// sender.setAddress(((InetSocketAddress)
									// socket.getRemoteSocketAddress()).getAddress()
									// .getHostAddress());
									String message = new String();
									message = in.readUTF();
									recepient.setSenderName(in.readUTF());
									recepient.setAddress(in.readUTF());
									socket.close();
									notifyListeners(sender, message, recepient);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					}
					executorService.shutdown();
					userPasswordBase.stopConnection();
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

	public static boolean logIn(String username, String password) {
		return UserPasswordBase.authorize(username, password);
	}

	public void logOut(String username) throws SQLException {
		UserPasswordBase.logOut(username);
	}

	public String getActiveUsers() {
		String activeUsers = new String();
		Peer user = new Peer(null, null);
		for (MessageListener messageListener : listeners) {
			user.setSenderName(messageListener.getName());
			user.setAddress(messageListener.getIP());
			activeUsers += user.toString() + "\n";
		}
		return activeUsers;
	}
}

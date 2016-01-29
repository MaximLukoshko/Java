package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

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
						notifyListeners(new Peer(null, null), new String());
					}
				} catch (IOException e) {
					e.printStackTrace();
					// JOptionPane.showMessageDialog(MainFrame.this,
					// "Error while working server", "Error",
					// JOptionPane.ERROR_MESSAGE);
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

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					new InstantMessenger();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}

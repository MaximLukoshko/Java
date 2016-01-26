package main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final String FRAME_TITLE = "Chat Client";

	private static final int FRAME_MINIMUM_WIDTH = 500;
	private static final int FRAME_MINIMUM_HEIGHT = 500;

	private static final int FROM_FIELD_DEFAULT_COLUMNS = 10;
	private static final int TO_FIELD_DEFAULT_COLUMNS = 20;

	private static final int INCOMING_AREA_DEFAULT_ROWS = 10;
	private static final int OUTGOING_AREA_DEFAULT_ROWS = 5;

	private static final int SERVER_PORT = 4567;

	private static final int SMALL_GAP = 5;
	private static final int MEDIUM_GAP = 10;
	private static final int LARGE_GAP = 15;

	private final JTextField textFieldFrom;
	private final JTextField textFieldTo;

	private final JTextArea textAreaIncoming;
	private final JTextArea textAreaOutgoing;

	public MainFrame() throws HeadlessException {
		super(FRAME_TITLE);

		setLocationAndSize();

		textAreaIncoming = new JTextArea(INCOMING_AREA_DEFAULT_ROWS, 0);
		textAreaIncoming.setEditable(false);

		textAreaOutgoing = new JTextArea(OUTGOING_AREA_DEFAULT_ROWS, 0);

		textFieldTo = new JTextField(TO_FIELD_DEFAULT_COLUMNS);
		textFieldFrom = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);

		fillFrame();
		setThread();
	}

	private void setLocationAndSize() {
		setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - getWidth()) / 2,
				(kit.getScreenSize().height - getHeight()) / 2);
	}

	private void fillFrame() {
		final JLabel labelRecepient = new JLabel("Recepient");
		final JLabel labelSeder = new JLabel("Sender");

		final JButton buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		final JPanel messagePanel = new JPanel();
		messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));

		final JScrollPane scrollPaneOutgoing = new JScrollPane(textAreaOutgoing);
		final JScrollPane scrollPaneIncoming = new JScrollPane(textAreaIncoming);

		final GroupLayout layout2 = new GroupLayout(messagePanel);
		messagePanel.setLayout(layout2);

		layout2.setHorizontalGroup(layout2
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout2.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										layout2.createSequentialGroup()
												.addComponent(labelSeder)
												.addGap(SMALL_GAP)
												.addComponent(textFieldFrom)
												.addGap(LARGE_GAP)
												.addComponent(labelRecepient)
												.addGap(SMALL_GAP)
												.addComponent(textFieldTo))
								.addComponent(scrollPaneOutgoing)
								.addComponent(buttonSend)).addContainerGap());
		layout2.setVerticalGroup(layout2
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout2.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelSeder)
								.addComponent(textFieldFrom)
								.addComponent(labelRecepient)
								.addComponent(textFieldTo)).addGap(MEDIUM_GAP)
				.addComponent(scrollPaneOutgoing).addGap(MEDIUM_GAP)
				.addComponent(buttonSend).addContainerGap());

		final GroupLayout layout1 = new GroupLayout(getContentPane());
		setLayout(layout1);

		layout1.setHorizontalGroup(layout1
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout1.createParallelGroup()
								.addComponent(scrollPaneIncoming)
								.addComponent(messagePanel)).addContainerGap());
		layout1.setVerticalGroup(layout1.createSequentialGroup()
				.addContainerGap().addComponent(scrollPaneIncoming)
				.addComponent(messagePanel).addContainerGap());
	}

	protected void sendMessage() {
		// TODO Auto-generated method stub

	}

	private void setThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					final ServerSocket serverSocket = new ServerSocket(
							SERVER_PORT);
					while (!Thread.interrupted()) {
						final Socket socket = serverSocket.accept();
						final DataInputStream in = new DataInputStream(socket
								.getInputStream());
						final String sender = in.readUTF();
						final String message = in.readUTF();
						socket.close();
						final String address = ((InetSocketAddress) socket
								.getRemoteSocketAddress()).getAddress()
								.getHostAddress();
						textAreaIncoming.append(sender + " (" + address + "): "
								+ message + "\n");

					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
					JOptionPane.showMessageDialog(MainFrame.this,
							"Error while working server", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		}).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				final MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

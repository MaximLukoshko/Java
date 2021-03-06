package main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

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

	private static final int SMALL_GAP = 5;
	private static final int MEDIUM_GAP = 10;
	private static final int LARGE_GAP = 15;

	private final JTextField textFieldFrom;
	private final JTextField textFieldFromIP;
	private final JTextField textFieldTo;
	private final JTextField textFieldToIP;

	private final JTextArea textAreaIncoming;
	private final JTextArea textAreaOutgoing;

	private MessageListener listener;

	private InstantMessenger instantMessenger;

	public MainFrame(final InstantMessenger IM) throws HeadlessException, IOException {
		super(FRAME_TITLE);

		setLocationAndSize();

		textAreaIncoming = new JTextArea(INCOMING_AREA_DEFAULT_ROWS, 0);
		textAreaIncoming.setEditable(false);

		textAreaOutgoing = new JTextArea(OUTGOING_AREA_DEFAULT_ROWS, 0);

		textFieldTo = new JTextField(TO_FIELD_DEFAULT_COLUMNS);
		textFieldFromIP = new JTextField(TO_FIELD_DEFAULT_COLUMNS);
		textFieldFrom = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);
		textFieldToIP = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);

		instantMessenger = IM;
		listener = new MessageListener(MainFrame.this);
		fillFrame();
		listener.setName(textFieldFrom.getText());
	}

	private void setLocationAndSize() {
		setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - getWidth()) / 2, (kit.getScreenSize().height - getHeight()) / 2);
	}

	private void fillFrame() {
		final JLabel labelRecepient = new JLabel("Recepient");
		final JLabel labelSender = new JLabel("Sender");

		final JButton buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		final JButton buttonShowUsers = new JButton("Show all Users");
		buttonShowUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowUsers();
			}
		});

		final JPanel messagePanel = new JPanel();
		messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));

		final JScrollPane scrollPaneOutgoing = new JScrollPane(textAreaOutgoing);
		final JScrollPane scrollPaneIncoming = new JScrollPane(textAreaIncoming);

		final GroupLayout layout2 = new GroupLayout(messagePanel);
		messagePanel.setLayout(layout2);

		layout2.setHorizontalGroup(layout2.createSequentialGroup().addContainerGap()
				.addGroup(layout2.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout2.createSequentialGroup().addComponent(labelSender).addGap(SMALL_GAP)
								.addGroup(layout2.createParallelGroup().addComponent(textFieldFrom)
										.addComponent(textFieldFromIP))
								.addGap(LARGE_GAP).addComponent(labelRecepient).addGap(SMALL_GAP)
								.addGroup(layout2.createParallelGroup().addComponent(textFieldTo)
										.addComponent(textFieldToIP)))
						.addComponent(scrollPaneOutgoing).addGroup(layout2.createSequentialGroup()
								.addComponent(buttonShowUsers).addGap(LARGE_GAP).addComponent(buttonSend)))
				.addContainerGap());
		layout2.setVerticalGroup(layout2.createSequentialGroup().addContainerGap()
				.addGroup(layout2.createParallelGroup(Alignment.BASELINE).addComponent(labelSender)
						.addGroup(layout2.createSequentialGroup().addComponent(textFieldFrom)
								.addComponent(textFieldFromIP))
						.addComponent(labelRecepient).addGroup(
								layout2.createSequentialGroup().addComponent(textFieldTo).addComponent(textFieldToIP)))
				.addGap(MEDIUM_GAP).addComponent(scrollPaneOutgoing).addGap(MEDIUM_GAP).addGroup(layout2
						.createParallelGroup().addComponent(buttonShowUsers).addGap(LARGE_GAP).addComponent(buttonSend))
				.addContainerGap());

		final GroupLayout layout1 = new GroupLayout(getContentPane());
		setLayout(layout1);

		layout1.setHorizontalGroup(layout1.createSequentialGroup().addContainerGap()
				.addGroup(layout1.createParallelGroup().addComponent(scrollPaneIncoming).addComponent(messagePanel))
				.addContainerGap());
		layout1.setVerticalGroup(layout1.createSequentialGroup().addContainerGap().addComponent(scrollPaneIncoming)
				.addComponent(messagePanel).addContainerGap());

		textFieldToIP.setText("127.0.0.1");
		{
			String userName;
			String userPassword;
			do {
				userName = "";
				userPassword = "";
				while (userName == null || userName.toLowerCase().equals("all") || userName.isEmpty()) {
					userName = JOptionPane.showInputDialog(MainFrame.this, "Enter your name:\n", "Log in dialog",
							JOptionPane.INFORMATION_MESSAGE);
				}
				while (userPassword.isEmpty()) {
					userPassword = JOptionPane.showInputDialog(MainFrame.this, "Password for '" + userName + "':\n",
							"Log in dialog", JOptionPane.INFORMATION_MESSAGE);
				}
			} while (!InstantMessenger.logIn(userName, userPassword));
			textFieldFrom.setText(userName.toLowerCase());
		}
		textFieldFromIP.setText(listener.getIP());

		textFieldFrom.setEditable(false);
		textFieldFromIP.setEditable(false);

		this.setTitle(FRAME_TITLE + " <" + textFieldFrom.getText() + "> (" + listener.getIP() + ")");
	}

	protected void ShowUsers() {
		JOptionPane.showMessageDialog(this, instantMessenger.getActiveUsers(), "Active Users",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void sendMessage() {
		try {
			final String senderName = textFieldFrom.getText();
			final String destinationName = textFieldTo.getText();
			final String destinationAddress = textFieldToIP.getText();
			final String message = textAreaOutgoing.getText();

			if (senderName.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Enter Sender Name", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (destinationAddress.isEmpty() && destinationName.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Enter address of recepient socket", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (message.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Message is empty", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Peer recepient = new Peer(destinationName, destinationAddress);

			instantMessenger.sendMessage(new Peer(senderName, new String(listener.getIP())), message, recepient);

			textAreaIncoming.append("Me -> " + recepient.toString() + ": " + message + "\n");
			textAreaOutgoing.setText("");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(MainFrame.this, "Error while sending message: socket-recepient is not found",
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(MainFrame.this, "Error while sending message", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(final InstantMessenger IM) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					final MainFrame frame = new MainFrame(IM);
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public InstantMessenger getInstantMessenger() {
		return instantMessenger;
	}

	public void appendIncoming(String string) {
		textAreaIncoming.append(string);
	}

	public void clearOutgoing() {
		textAreaOutgoing.setText("");
	}

	@Override
	@Deprecated
	public void hide() {
		super.hide();
		try {
			instantMessenger.logOut(this.listener.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instantMessenger.removeMessageListener(listener);
	}
}

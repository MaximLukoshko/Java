package main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

	private static final int SMALL_GAP = 5;
	private static final int MEDIUM_GAP = 10;
	private static final int LARGE_GAP = 15;

	private final JTextField textFieldFrom;
	private final JTextField textFieldTo;

	private final JTextArea textAreaIncoming;
	private final JTextArea textAreaOutgoing;

	public MainFrame() throws HeadlessException {
		super(FRAME_TITLE);
		// TODO Auto-generated constructor stub
		// Центрирование окна
		setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - getWidth()) / 2,
				(kit.getScreenSize().height - getHeight()) / 2);

		textAreaIncoming = new JTextArea(INCOMING_AREA_DEFAULT_ROWS, 0);
		textAreaIncoming.setEditable(false);

		textAreaOutgoing = new JTextArea(OUTGOING_AREA_DEFAULT_ROWS, 0);

		textFieldTo = new JTextField(TO_FIELD_DEFAULT_COLUMNS);
		textFieldFrom = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);

		// Панель ввода сообщения
		final JPanel messagePanel = new JPanel();
		messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));

		final GroupLayout layout2 = new GroupLayout(messagePanel);
		messagePanel.setLayout(layout2);

		layout2.setHorizontalGroup(layout2
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout2.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										layout2.createSequentialGroup()
												.addComponent(textFieldFrom)
												.addComponent(textFieldTo)))
				.addContainerGap());
		layout2.setVerticalGroup(layout2
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldFrom)
								.addComponent(textFieldTo)).addContainerGap());

		final GroupLayout layout1 = new GroupLayout(getContentPane());
		setLayout(layout1);

		layout1.setHorizontalGroup(layout1
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						layout1.createParallelGroup()
								.addComponent(textAreaIncoming)
								.addComponent(messagePanel)).addContainerGap());
		layout1.setVerticalGroup(layout1.createSequentialGroup()
				.addContainerGap().addComponent(textAreaIncoming)
				.addComponent(messagePanel).addContainerGap());
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

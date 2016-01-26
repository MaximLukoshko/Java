package main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final String FRAME_TITLE = "Chat Client";

	private static final int FRAME_MINIMUM_WIDTH = 500;
	private static final int FRAME_MINIMUM_HEIGHT = 500;

	public MainFrame() throws HeadlessException {
		super(FRAME_TITLE);
		// TODO Auto-generated constructor stub
		// Центрирование окна
		setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - getWidth()) / 2,
				(kit.getScreenSize().height - getHeight()) / 2);
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

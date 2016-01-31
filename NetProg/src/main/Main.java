package main;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final int FRAME_WIDTH = 150;
	private static final int FRAME_HEIGHT = 75;
	static InstantMessenger IM;

	public static void main(String args[]) throws IOException {
		IM = new InstantMessenger();
		new Thread(new Runnable() {

			@Override
			public void run() {
				final Main frame = new Main();
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		}).start();
	}

	public Main() throws HeadlessException {
		super();
		setLocationAndSize();
		JButton btn = new JButton("New User");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.main(IM);
			}
		});
		add(btn);
	}

	private void setLocationAndSize() {
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}
}

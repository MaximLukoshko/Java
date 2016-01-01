package main;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	int WIDTH = 900;
	int HEIGHT = 700;

	JMenuBar MenuBarMyMenu = new JMenuBar();

	JMenu MenuBalls = new JMenu("Balls");
	JMenu MenuControl = new JMenu("Control");

	JMenuItem MenuBallsAdd = new JMenuItem();
	JMenuItem MenuControlStart = new JMenuItem();
	JMenuItem MenuControlStop = new JMenuItem();

	void SetPosition() {
		setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);
	}

	void FillFrame() {
		SetListeners();

		MenuBalls.add(MenuBallsAdd);
		MenuControl.add(MenuControlStart);
		MenuControl.add(MenuControlStop);

		MenuBarMyMenu.add(MenuBalls);
		MenuBarMyMenu.add(MenuControl);

		this.setJMenuBar(MenuBarMyMenu);
	}

	void SetListeners() {
		Action ActionMenuBallsAdd = new AbstractAction("Add") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		MenuBallsAdd.setAction(ActionMenuBallsAdd);

		Action ActionMenuControlStart = new AbstractAction("Start") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		MenuControlStart.setAction(ActionMenuControlStart);

		Action ActionMenuControlStop = new AbstractAction("Stop") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		MenuControlStop.setAction(ActionMenuControlStop);
	}

	public MainFrame() throws HeadlessException {
		super("Flying Balls");
		// TODO Auto-generated constructor stub
		SetPosition();
		FillFrame();
		SetListeners();
	}

}

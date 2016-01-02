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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6148383319193422241L;
	int WIDTH = 900;
	int HEIGHT = 700;

	JMenuBar MenuBarMyMenu = new JMenuBar();

	JMenu MenuBalls = new JMenu("Balls");
	JMenu MenuControl = new JMenu("Control");
	JMenu MenuEffects = new JMenu("Effects");

	JMenuItem MenuBallsAdd = new JMenuItem();
	JMenuItem MenuControlResume = new JMenuItem();
	JMenuItem MenuControlPause = new JMenuItem();
	JMenuItem MenuEffectsCharisma = new JMenuItem();

	Field field = new Field();

	void SetPosition() {
		setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);
	}

	void FillFrame() {
		SetListeners();

		MenuBalls.add(MenuBallsAdd);
		MenuControl.add(MenuControlResume);
		MenuControl.add(MenuControlPause);
		MenuEffects.add(MenuEffectsCharisma);

		MenuBarMyMenu.add(MenuBalls);
		MenuBarMyMenu.add(MenuControl);
		MenuBarMyMenu.add(MenuEffects);

		this.setJMenuBar(MenuBarMyMenu);

		MenuControlResume.setEnabled(false);

		this.add(field);
	}

	void SetListeners() {
		Action ActionMenuBallsAdd = new AbstractAction("Add") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7213446475314211005L;

			@Override
			public void actionPerformed(ActionEvent e) {
				field.addBall();
			}
		};
		MenuBallsAdd.setAction(ActionMenuBallsAdd);

		Action ActionMenuControlResume = new AbstractAction("Resume") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5144801925819024079L;

			@Override
			public void actionPerformed(ActionEvent e) {
				MenuControlResume.setEnabled(false);
				MenuControlPause.setEnabled(true);
				field.resume();
			}
		};
		MenuControlResume.setAction(ActionMenuControlResume);

		Action ActionMenuControlPause = new AbstractAction("Pause") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8607383871481050813L;

			@Override
			public void actionPerformed(ActionEvent e) {
				MenuControlResume.setEnabled(true);
				MenuControlPause.setEnabled(false);
				field.pause();
			}
		};
		MenuControlPause.setAction(ActionMenuControlPause);

		Action ActionMenuEffectsCharisma = new AbstractAction("Charisma") {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3645263919659057764L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		};
		MenuEffectsCharisma.setAction(ActionMenuEffectsCharisma);
	}

	public MainFrame() throws HeadlessException {
		super("Flying Balls");
		SetPosition();
		FillFrame();
	}

}

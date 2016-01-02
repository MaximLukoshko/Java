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
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6148383319193422241L;
	private int WIDTH = 900;
	private int HEIGHT = 700;

	private boolean charisma = false;

	private JMenuBar MenuBarMyMenu = new JMenuBar();

	private JMenu MenuBalls = new JMenu("Balls");
	private JMenu MenuControl = new JMenu("Control");
	private JMenu MenuEffects = new JMenu("Effects");

	private JMenuItem MenuBallsAdd = new JMenuItem();
	private JMenuItem MenuBallsRemove = new JMenuItem();
	private JMenuItem MenuControlResume = new JMenuItem();
	private JMenuItem MenuControlPause = new JMenuItem();
	private JMenuItem MenuEffectsCharisma = new JMenuItem();

	private Field field = new Field();

	private void SetPosition() {
		setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);
	}

	private void FillFrame() {
		SetListeners();

		MenuBalls.add(MenuBallsAdd);
		MenuBalls.add(MenuBallsRemove);
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

	private void SetListeners() {
		Action ActionMenuBallsAdd = new AbstractAction("Add") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7213446475314211005L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String Number = JOptionPane.showInputDialog(
						"Input Balls Number \n from 0 to 100", "1");
				try {
					Integer numberAddingBalls = Integer.parseInt(Number);
					if (Integer.valueOf(Number) < 0
							|| Integer.valueOf(Number) > 100) {
						throw new Exception();
					}
					for (int i = 0; i < numberAddingBalls; i++) {
						field.addBall();
					}
				} catch (Exception ex) {
					JOptionPane
							.showMessageDialog(MainFrame.this, "Check input");
				}
			}
		};
		MenuBallsAdd.setAction(ActionMenuBallsAdd);

		Action ActionMenuBallsRemove = new AbstractAction("Remove") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5144801925819024079L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Number = JOptionPane.showInputDialog(
						"Input Balls Number \n from 0 to 100", "1");
				try {
					Integer numberAddingBalls = Integer.parseInt(Number);
					if (Integer.valueOf(Number) < 0
							|| Integer.valueOf(Number) > 100) {
						throw new Exception();
					}
					boolean go = true;
					for (int i = 0; i < numberAddingBalls && go; i++) {
						go = field.removeBall();
					}
				} catch (Exception ex) {
					JOptionPane
							.showMessageDialog(MainFrame.this, "Check input");
				}
			}
		};
		MenuBallsRemove.setAction(ActionMenuBallsRemove);
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
				charisma = !charisma;
				field.Charisma(charisma);
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

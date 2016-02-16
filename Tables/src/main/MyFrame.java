package main;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	final int WIDTH = 600;
	final int HEIGHT = 600;

	private boolean MenuItemOpenSaveToFileEnabled = false;
	private boolean MenuItemOpenSaveToGraphicEnabled = false;
	private boolean MenuItemTableFindValueEnabled = false;

	private Double[] Coefficients = { -3.0, 2.0, 1.0 };

	private GornerTableModel data;
	private GornerTableCellRenderer renderer = new GornerTableCellRenderer();

	private JMenuBar MenuBarMyMenu = new JMenuBar();

	private JMenu MenuFile = new JMenu("File");
	private JMenu MenuTable = new JMenu("Table");
	private JMenu MenuHelp = new JMenu("Help");

	private JMenuItem MenuItemOpenSaveToFile;
	private JMenuItem MenuItemOpenSaveToGraphic;
	private JMenuItem MenuItemTableFindValue;
	private JMenuItem MenuItemHelpAbout;

	private JFileChooser FileChooser;

	private Box HorizontalBoxVariables = Box.createHorizontalBox();
	private Box HorizontalBoxButtons = Box.createHorizontalBox();
	private Box VertucalBoxNorth = Box.createVerticalBox();
	private Box VertucalBoxSouth = Box.createVerticalBox();
	private Box HorizontalBoxResult = Box.createHorizontalBox();

	private JButton ButtonCount = new JButton("Count");
	private JButton ButtonClearFields = new JButton("ClearFields");

	private JTextField TextFieldFrom = new JTextField();
	private JTextField TextFieldTo = new JTextField();
	private JTextField TextFieldStep = new JTextField();

	private JLabel LabelFrom = new JLabel("From:");
	private JLabel LabelTo = new JLabel("To:");
	private JLabel LabelStep = new JLabel("Step:");

	private ActionListener ActionListenerButtonCount = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Double From = Double.parseDouble((TextFieldFrom.getText()));
			Double To = Double.parseDouble((TextFieldTo.getText()));
			Double Step = Double.parseDouble((TextFieldStep.getText()));

			data = new GornerTableModel(From, To, Step, Coefficients);
			JTable table = new JTable(data);
			table.setDefaultRenderer(Double.class, renderer);
			table.setRowHeight(30);

			HorizontalBoxResult.removeAll();
			HorizontalBoxResult.add(new JScrollPane(table));
			MenuItemOpenSaveToFileEnabled = true;
			MenuItemOpenSaveToFile.setEnabled(MenuItemOpenSaveToFileEnabled);
			MenuItemOpenSaveToGraphicEnabled = true;
			MenuItemOpenSaveToGraphic
					.setEnabled(MenuItemOpenSaveToGraphicEnabled);
			MenuItemTableFindValueEnabled = true;
			MenuItemTableFindValue.setEnabled(MenuItemTableFindValueEnabled);

			getContentPane().validate();
		}
	};

	private ActionListener ActionListenerButtonClearFields = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			TextFieldFrom.setText("");
			TextFieldTo.setText("");
			TextFieldStep.setText("");
			HorizontalBoxResult.removeAll();
			HorizontalBoxResult.add(new JPanel());
			getContentPane().validate();

			MenuItemOpenSaveToFileEnabled = false;
			MenuItemOpenSaveToFile.setEnabled(MenuItemOpenSaveToFileEnabled);
			MenuItemOpenSaveToGraphicEnabled = false;
			MenuItemOpenSaveToGraphic
					.setEnabled(MenuItemOpenSaveToGraphicEnabled);
			MenuItemTableFindValueEnabled = false;
			MenuItemTableFindValue.setEnabled(MenuItemTableFindValueEnabled);
		}
	};

	protected void SaveToTextFile(File SelectedFile) {
		try {
			PrintStream out = new PrintStream(SelectedFile);
			out.println("Polynomial:");
			for (int i = 0; i < Coefficients.length - 1; i++) {
				out.print(Coefficients[i] + "*" + "X^"
						+ (Coefficients.length - i - 1) + " + ");
			}
			out.print(Coefficients[Coefficients.length - 1]);

			out.print("\n\nA range from " + TextFieldFrom.getText() + " to "
					+ TextFieldTo.getText() + " with step of "
					+ TextFieldStep.getText());
			out.println("\n====================================================\n");

			DecimalFormat formatter = (DecimalFormat) NumberFormat
					.getInstance();
			formatter.setMaximumFractionDigits(3);

			for (int i = 0; i < data.getRowCount(); i++) {
				out.println(formatter.format(data.getValueAt(i, 0)) + "     "
						+ formatter.format(data.getValueAt(i, 1)));
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void SaveToBinaryFile(File SelectedFile) {
		try {
			DataOutputStream dataOut = new DataOutputStream(
					new FileOutputStream(SelectedFile));
			for (int i = 0; i < data.getRowCount(); i++) {
				dataOut.writeDouble((Double) data.getValueAt(i, 0));
				dataOut.writeDouble((Double) data.getValueAt(i, 1));
			}
			dataOut.close();
		} catch (Exception e) {

		}
	}

	private void FindOptimalPosition() {
		setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);
	}

	private void FillFrame() {

		MenuBarMyMenu.add(MenuFile);
		MenuBarMyMenu.add(MenuTable);
		MenuBarMyMenu.add(MenuHelp);

		this.setJMenuBar(MenuBarMyMenu);

		HorizontalBoxVariables.add(Box.createHorizontalGlue());
		HorizontalBoxVariables.add(LabelFrom);
		HorizontalBoxVariables.add(Box.createHorizontalStrut(WIDTH / 100));
		HorizontalBoxVariables.add(TextFieldFrom);
		HorizontalBoxVariables.add(Box.createHorizontalStrut(WIDTH / 50));
		HorizontalBoxVariables.add(LabelTo);
		HorizontalBoxVariables.add(Box.createHorizontalStrut(WIDTH / 100));
		HorizontalBoxVariables.add(TextFieldTo);
		HorizontalBoxVariables.add(Box.createHorizontalStrut(WIDTH / 50));
		HorizontalBoxVariables.add(LabelStep);
		HorizontalBoxVariables.add(Box.createHorizontalStrut(WIDTH / 100));
		HorizontalBoxVariables.add(TextFieldStep);

		VertucalBoxNorth.add(Box.createVerticalStrut(HEIGHT / 50));
		VertucalBoxNorth.add(HorizontalBoxVariables);
		VertucalBoxNorth.add(Box.createVerticalStrut(HEIGHT / 50));

		getContentPane().add(VertucalBoxNorth, BorderLayout.NORTH);

		HorizontalBoxButtons.add(Box.createHorizontalGlue());
		HorizontalBoxButtons.add(ButtonCount);
		HorizontalBoxButtons.add(Box.createHorizontalStrut(WIDTH / 10));
		HorizontalBoxButtons.add(ButtonClearFields);
		HorizontalBoxButtons.add(Box.createHorizontalGlue());

		VertucalBoxSouth.add(Box.createVerticalStrut(HEIGHT / 25));
		VertucalBoxSouth.add(HorizontalBoxButtons);
		VertucalBoxSouth.add(Box.createVerticalStrut(HEIGHT / 25));

		getContentPane().add(HorizontalBoxResult);
		getContentPane().add(VertucalBoxSouth, BorderLayout.SOUTH);
	}

	private void SetListeners() {

		Action SaveToTextAct = new AbstractAction("Save to text file") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (FileChooser == null) {
					FileChooser = new JFileChooser();
					FileChooser.setCurrentDirectory(new File("."));
				}

				if (FileChooser.showSaveDialog(MyFrame.this) == JFileChooser.APPROVE_OPTION) {
					SaveToTextFile(FileChooser.getSelectedFile());
				}

			}
		};

		MenuItemOpenSaveToFile = MenuFile.add(SaveToTextAct);
		MenuItemOpenSaveToFile.setEnabled(MenuItemOpenSaveToFileEnabled);

		Action SaveToGraphic = new AbstractAction("Save to Binary File") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (FileChooser == null) {
					FileChooser = new JFileChooser();
					FileChooser.setCurrentDirectory(new File("."));
				}

				if (FileChooser.showSaveDialog(MyFrame.this) == JFileChooser.APPROVE_OPTION) {
					SaveToBinaryFile(FileChooser.getSelectedFile());
				}
			}
		};

		MenuItemOpenSaveToGraphic = MenuFile.add(SaveToGraphic);
		MenuItemOpenSaveToGraphic.setEnabled(MenuItemOpenSaveToGraphicEnabled);

		Action AboutAct = new AbstractAction("About...") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JLabel labelAbout = new JLabel("Lukoshko Maxim, 7 Group",
							new ImageIcon("photo.jpeg"), JLabel.CENTER);
					JOptionPane.showMessageDialog(MyFrame.this, labelAbout,
							"About Developer...", JOptionPane.PLAIN_MESSAGE);

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		};
		MenuItemHelpAbout = MenuHelp.add(AboutAct);

		Action FindValueAct = new AbstractAction("FindValue") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String needle = JOptionPane.showInputDialog(MyFrame.this,
						"Your wish value:", "Searching Value",
						JOptionPane.QUESTION_MESSAGE);
				renderer.setNeedle(needle);
				getContentPane().repaint();
			}
		};
		MenuItemTableFindValue = MenuTable.add(FindValueAct);
		MenuItemTableFindValue.setEnabled(MenuItemTableFindValueEnabled);

		ButtonCount.addActionListener(ActionListenerButtonCount);
		ButtonClearFields.addActionListener(ActionListenerButtonClearFields);
	}

	public MyFrame(String[] args) throws HeadlessException {
		super();
		// TODO Aprivate Double From;
		FindOptimalPosition();
		SetListeners();
		FillFrame();
	}
}

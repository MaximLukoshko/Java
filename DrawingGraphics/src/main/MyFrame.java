package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MyFrame extends JFrame {

	final int WIDTH = 600;
	final int HEIGHT = 700;

	private boolean DataIsAvailable = false;

	JMenuBar MenuBar = new JMenuBar();

	JMenu MenuFile = new JMenu("File");
	JMenu MenuPlot = new JMenu("Plot");

	JMenuItem MenuItemFileOpenFileWithData = new JCheckBoxMenuItem();
	JCheckBoxMenuItem MenuItemPlotShowDatumLine = new JCheckBoxMenuItem();;
	JCheckBoxMenuItem MenuItemPlotShowPointMarkers;
	JCheckBoxMenuItem MenuItemPlotShowZeroMarkers;

	JFileChooser fileChooser;

	GraphicsDisplay drawer = new GraphicsDisplay();

	private void SetSizeAndLocation() {
		this.setSize(WIDTH, HEIGHT);

		Toolkit kit = Toolkit.getDefaultToolkit();

		this.setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);

		setExtendedState(MAXIMIZED_BOTH);
	}

	private void FillFrame() {

		Action ActionOpenDataFile = new AbstractAction("Open file...") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fileChooser == null) {
					fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("."));
				}

				if (fileChooser.showOpenDialog(MyFrame.this) == JFileChooser.APPROVE_OPTION) {
					ReadDataFromFile(fileChooser.getSelectedFile());
				}
			}
		};

		Action ActionShowDatumLine = new AbstractAction("Show Datum Line") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawer.setShowAxis(MenuItemPlotShowDatumLine.getState());
			}
		};

		Action ActionShowPointMarkers = new AbstractAction("Show Point Markers") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawer.setShowMarkers(MenuItemPlotShowPointMarkers.getState());
			}
		};

		Action ActionShowZeroMarkers = new AbstractAction("Show Zero Markers") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawer.setShowZeroMarkers(MenuItemPlotShowZeroMarkers
						.getState());
			}
		};

		MenuPlot.addMenuListener(new PlotMenuListener());

		MenuItemFileOpenFileWithData = new JMenuItem(ActionOpenDataFile);
		MenuItemPlotShowDatumLine = new JCheckBoxMenuItem(ActionShowDatumLine);
		MenuItemPlotShowPointMarkers = new JCheckBoxMenuItem(
				ActionShowPointMarkers);
		MenuItemPlotShowZeroMarkers = new JCheckBoxMenuItem(
				ActionShowZeroMarkers);

		MenuFile.add(MenuItemFileOpenFileWithData);
		MenuPlot.add(MenuItemPlotShowDatumLine);
		MenuPlot.add(MenuItemPlotShowPointMarkers);
		MenuPlot.add(MenuItemPlotShowZeroMarkers);

		MenuBar.add(MenuFile);
		MenuBar.add(MenuPlot);
		this.setJMenuBar(MenuBar);

		getContentPane().add(drawer, BorderLayout.CENTER);
	}

	public MyFrame() throws HeadlessException {
		super("Plot");
		// TODO Auto-generated constructor stub

		SetSizeAndLocation();

		FillFrame();
	}

	private class PlotMenuListener implements MenuListener {

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void menuSelected(MenuEvent e) {
			// TODO Auto-generated method stub
			MenuItemPlotShowDatumLine.setEnabled(DataIsAvailable);
			MenuItemPlotShowPointMarkers.setEnabled(DataIsAvailable);
			MenuItemPlotShowZeroMarkers.setEnabled(DataIsAvailable);
		}
	}

	private void ReadDataFromFile(File SelectedFile) {

		try {
			DataInputStream in = new DataInputStream(new FileInputStream(
					SelectedFile));
			Double[][] DataForPlot = new Double[in.available()
					/ (2 * Double.SIZE / 8)][2];
			for (int i = 0; in.available() > 0; i++) {
				Double x = in.readDouble();
				Double y = in.readDouble();
				DataForPlot[i] = new Double[] { x, y };
			}

			if ((DataForPlot != null) && (DataForPlot.length > 0)) {
				DataIsAvailable = true;
				drawer.showGraphics(DataForPlot);
			}

			in.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(MyFrame.this,
					"ERROR while opening file", "", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {
			// TODO: handle exception

			JOptionPane.showMessageDialog(MyFrame.this,
					"ERROR while reading data", "", JOptionPane.ERROR_MESSAGE);
		}

	}
}

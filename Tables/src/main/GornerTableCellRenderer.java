package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {

	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();

	private String needle = null;

	private DecimalFormat formatter;

	public GornerTableCellRenderer() {
		formatter = (DecimalFormat) NumberFormat.getInstance();

		formatter.setMaximumFractionDigits(3);
		formatter.setGroupingUsed(false);

		DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();

		formatter.getDecimalFormatSymbols();
		dottedDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dottedDouble);

		panel.add(label);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void setNeedle(String needle) {
		this.needle = needle;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		// TODO Auto-generated method stub

		String formattedDouble = formatter.format(value);
		label.setText(formattedDouble);

		if ((col == 1) && (needle != null) && (needle.equals(formattedDouble))) {
			panel.setBackground(Color.RED);
		} else {
			formattedDouble += ".";
			int pos = formattedDouble.indexOf(".");
			int summary = 10;
			for (int i = 0; i < pos; i++) {
				if (formattedDouble.charAt(i) == '-') {
					i++;
				}
				summary += Integer.parseInt(String.valueOf(formattedDouble
						.charAt(i)));
			}
			boolean isDividedTen = ((summary % 10) == 0);
			if ((col == 1) && (isDividedTen)) {
				panel.setBackground(Color.GREEN);
			} else {
				panel.setBackground(Color.WHITE);
			}
		}
		return panel;
	}
}

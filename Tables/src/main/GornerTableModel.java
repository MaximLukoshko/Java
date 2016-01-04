package main;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

	private Double From;
	private Double To;
	private Double Step;
	private Double[] Coefficients;

	public GornerTableModel(Double from, Double to, Double step,
			Double[] coefficients) {
		super();
		// TODO Auto-generated constructor stub
		this.From = from;
		this.To = to;
		this.Step = step;
		this.Coefficients = coefficients;
	}

	public Double getFrom() {
		return From;
	}

	public Double getTo() {
		return To;
	}

	public Double getStep() {
		return Step;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 2) {
			return Boolean.class;
		}
		return Double.class;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0:
			return "The Value of X";
		case 1:
			return "The Value of polynomial";
		default:
			return "Marginal symmetry";
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return new Double(Math.ceil((To - From) / Step)).intValue() + 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Double x = From + Step * row;

		if (col == 0) {
			return x;
		} else if (col == 1) {
			return CountGorner(Coefficients, x);
		} else {
			DecimalFormat formatter = (DecimalFormat) NumberFormat
					.getInstance();
			formatter.setMaximumFractionDigits(3);

			String Number = formatter.format(CountGorner(Coefficients, x))
					.toString();
			if (Number.charAt(0) == Number.charAt(Number.length() - 1)) {
				return true;
			} else
				return false;
		}
	}

	private Double CountGorner(Double[] coefficients, Double x) {
		Double result = coefficients[0];

		for (int i = 1; i < coefficients.length; i++) {
			result *= x;
			result += coefficients[i];
		}

		return result;
	}
}

package MainPackage;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyMainFrame extends JFrame {

	final int WIDTH = 600;
	final int HEIGHT = 400;

	private int FunctionID;
	private int MemoryID;

	private double Mem[] = new double[3];

	private Label LabelsVariables[] = new Label[3];
	private JTextField TextFieldsVariables[] = new JTextField[3];

	private Label LabelResult = new Label("Result:");
	private JTextField TextFieldResult = new JTextField();

	private Button ButtonCalculate = new Button("Calculate");
	private Button ButtonClearFields = new Button("ClearFields");

	private Button Button_M_Plus = new Button("M+");
	private Button Button_MC = new Button("MC");

	private JRadioButton RadioButtonFunction1 = new JRadioButton("Function1");
	private JRadioButton RadioButtonFunction2 = new JRadioButton("Function2");

	private JRadioButton RadioButtonsMemoriesArray[] = new JRadioButton[3];

	private ButtonGroup RadioButtonsFunctions = new ButtonGroup();
	private ButtonGroup RadioButtonsMemories = new ButtonGroup();

	private Box MyBox = Box.createVerticalBox();
	private Box BoxVariables = Box.createHorizontalBox();
	private Box BoxFunctions = Box.createHorizontalBox();
	private Box BoxButtonsCalcClear = Box.createHorizontalBox();
	private Box BoxResult = Box.createHorizontalBox();
	private Box BoxButtons_M_Plus_Mc = Box.createHorizontalBox();
	private Box BoxRadioButtonsMemories = Box.createHorizontalBox();

	private double Function1(double x, double y, double z) {
		return (1 / Math.sqrt(x) + Math.cos(Math.exp(y)) + Math
				.cos(Math.exp(z)))
				/ (Math.pow(
						Math.log((1 + z) * (1 + z))
								+ Math.sqrt(Math.exp(Math.cos(y))
										+ Math.sin(Math.PI * x)), 1 / 3));
	}

	private double Function2(double x, double y, double z) {
		return Math.atan(Math.pow(z, 1 / x))
				/ (y * y + z * Math.sin(Math.log(x)));
	}

	private void addRadioButtonListenerFunc(JRadioButton radio, final int ID) {
		radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MyMainFrame.this.FunctionID = ID;
				JOptionPane.showMessageDialog(BoxFunctions,
						"X must be larger than ZERO");
			}
		});
	}

	private void addRadioButtonListenerMem(JRadioButton radio, final int ID) {
		radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MyMainFrame.this.MemoryID = ID;
				JOptionPane.showMessageDialog(BoxRadioButtonsMemories,
						"The value of this cell is " + Mem[MemoryID], "",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	private void FillFrame() {
		LabelsVariables[0] = new Label("X:");
		LabelsVariables[1] = new Label("Y:");
		LabelsVariables[2] = new Label("Z:");

		MyBox.add(Box.createVerticalGlue());

		Dimension size = new Dimension(WIDTH, HEIGHT);
		BoxVariables.setMaximumSize(size);
		BoxResult.setMaximumSize(size);
		size.setSize(WIDTH * 0.7, 20);
		BoxVariables.setPreferredSize(size);
		BoxResult.setPreferredSize(size);
		for (int i = 0; i < 3; i++) {
			TextFieldsVariables[i] = new JTextField();
			TextFieldsVariables[i].setPreferredSize(TextFieldsVariables[i]
					.getPreferredSize());
		}

		BoxVariables.add(Box.createHorizontalGlue());
		BoxVariables.add(LabelsVariables[0]);
		BoxVariables.add(Box.createHorizontalStrut(1));
		BoxVariables.add(TextFieldsVariables[0]);
		for (int i = 1; i < 3; i++) {
			BoxVariables.add(Box.createHorizontalStrut(60));
			BoxVariables.add(LabelsVariables[i]);
			BoxVariables.add(Box.createHorizontalStrut(1));
			BoxVariables.add(TextFieldsVariables[i]);
		}
		BoxVariables.add(Box.createHorizontalGlue());
		MyBox.add(BoxVariables);
		MyBox.add(Box.createVerticalGlue());

		BoxFunctions.add(Box.createHorizontalGlue());
		RadioButtonsFunctions.add(RadioButtonFunction1);
		RadioButtonsFunctions.add(RadioButtonFunction2);
		BoxFunctions.add(RadioButtonFunction1);
		BoxFunctions.add(Box.createHorizontalGlue());
		addRadioButtonListenerFunc(RadioButtonFunction1, 0);
		BoxFunctions.add(RadioButtonFunction2);
		BoxFunctions.add(Box.createHorizontalGlue());
		addRadioButtonListenerFunc(RadioButtonFunction2, 1);
		MyBox.add(BoxFunctions);
		MyBox.add(Box.createVerticalGlue());

		BoxButtonsCalcClear.add(Box.createHorizontalGlue());
		BoxButtonsCalcClear.add(ButtonCalculate);
		BoxButtonsCalcClear.add(Box.createHorizontalStrut(20));
		BoxButtonsCalcClear.add(ButtonClearFields);
		BoxButtonsCalcClear.add(Box.createHorizontalGlue());
		MyBox.add(BoxButtonsCalcClear);
		MyBox.add(Box.createVerticalGlue());

		BoxResult.add(Box.createHorizontalGlue());
		BoxResult.add(LabelResult);
		BoxResult.add(Box.createHorizontalStrut(10));
		TextFieldResult.setEditable(false);
		BoxResult.add(TextFieldResult);
		BoxResult.add(Box.createHorizontalGlue());

		MyBox.add(BoxResult);
		MyBox.add(Box.createVerticalGlue());

		for (int i = 0; i < 3; i++) {
			RadioButtonsMemoriesArray[i] = new JRadioButton("Mem" + (i + 1));
			addRadioButtonListenerMem(RadioButtonsMemoriesArray[i], i);
			RadioButtonsMemories.add(RadioButtonsMemoriesArray[i]);
			BoxRadioButtonsMemories.add(RadioButtonsMemoriesArray[i]);
		}
		MyBox.add(BoxRadioButtonsMemories);
		MyBox.add(Box.createVerticalGlue());

		BoxButtons_M_Plus_Mc.add(Box.createHorizontalGlue());
		BoxButtons_M_Plus_Mc.add(Button_M_Plus);
		BoxButtons_M_Plus_Mc.add(Box.createHorizontalStrut(20));
		BoxButtons_M_Plus_Mc.add(Button_MC);
		BoxButtons_M_Plus_Mc.add(Box.createHorizontalGlue());
		MyBox.add(BoxButtons_M_Plus_Mc);

		MyBox.add(Box.createVerticalGlue());

		this.add(MyBox);

		RadioButtonFunction1.setSelected(true);
		RadioButtonsMemoriesArray[0].setSelected(true);
	}

	private void setListeners() {
		ButtonCalculate.addActionListener(ListenerButtonCalculate);
		ButtonClearFields.addActionListener(ListenerButtonClearFields);
		Button_M_Plus.addActionListener(ListenerButtonM_plus);
		Button_MC.addActionListener(ListenerButtonMC);
	}

	private ActionListener ListenerButtonMC = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Mem[MemoryID] = 0.0;
			JOptionPane.showMessageDialog(Button_MC, "Chosen cell is cleared");
		}
	};

	private ActionListener ListenerButtonClearFields = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 3; i++) {
				TextFieldsVariables[i].setText("");
				TextFieldResult.setText("");
			}
		}
	};

	private ActionListener ListenerButtonM_plus = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				if (TextFieldResult.getText().equals("")) {
					throw new Exception(
							"Are you kidding?\nNothing is about to be added");
				}
				if (TextFieldResult.getText().equals("NaN")) {
					throw new ArithmeticException();
				}
				Mem[MemoryID] += Double.parseDouble(TextFieldResult.getText());
			} catch (ArithmeticException ex) {
				JOptionPane.showMessageDialog(Button_M_Plus,
						"Error while calculating\nCheck input data");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(Button_M_Plus, ex.getMessage());
			}
		}
	};

	private ActionListener ListenerButtonCalculate = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				if (FunctionID == 0) {
					TextFieldResult
							.setText(Double.toString(Function1(Double
									.parseDouble(TextFieldsVariables[0]
											.getText()), Double
									.parseDouble(TextFieldsVariables[1]
											.getText()), Double
									.parseDouble(TextFieldsVariables[2]
											.getText()))));
				} else if (FunctionID == 1) {
					TextFieldResult
							.setText(Double.toString(Function2(Double
									.parseDouble(TextFieldsVariables[0]
											.getText()), Double
									.parseDouble(TextFieldsVariables[1]
											.getText()), Double
									.parseDouble(TextFieldsVariables[2]
											.getText()))));
				} else {
					throw new Exception("Check Chosen Function");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(ButtonCalculate,
						"Check Input Data");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(ButtonCalculate, ex.getMessage());
			}
		}
	};

	public MyMainFrame(String Name) {
		super(Name);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2,
				(kit.getScreenSize().height - HEIGHT) / 2);
		setSize(WIDTH, HEIGHT);

		FillFrame();

		setListeners();

	}

}

package MainPackage;

import javax.swing.JFrame;

public class MainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyMainFrame MyFrame = new MyMainFrame("FunctionCalculator");
		MyFrame.setVisible(true);
		MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

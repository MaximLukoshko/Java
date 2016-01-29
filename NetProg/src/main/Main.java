package main;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main() throws IOException {
		final InstantMessenger IM = new InstantMessenger();
		for (int i = 0; i < 3; i++) {
			MainFrame.main(IM);
		}
	}

}

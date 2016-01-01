package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093514227845785559L;
	private ArrayList<BouncingBall> ArrayBalls = new ArrayList<BouncingBall>();
	private boolean paused = false;
	private Timer TimerRepaint = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			repaint();
		}
	});

	public void addBall() {
		ArrayBalls.add(new BouncingBall(this));
	}

	public synchronized void pause() {
		paused = true;
	}

	public synchronized void resume() {
		paused = false;
		notifyAll();
	}

	public synchronized void canMove(BouncingBall ball)
			throws InterruptedException {
		if (paused) {
			wait();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Graphics2D canvas = (Graphics2D) g;
		for (BouncingBall ball : ArrayBalls) {
			ball.Paint(canvas);
		}
	}

	public Field() {
		super();
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		TimerRepaint.start();
		ArrayBalls.add(new BouncingBall(this));
	}

}

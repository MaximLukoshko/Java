package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093514227845785559L;
	private ArrayList<BouncingBall> ArrayBalls = new ArrayList<BouncingBall>();
	private boolean paused = false;
	private Timer TimerRepaint = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});

	private boolean charisma = false;
	private int mouseX;
	private int mouseY;

	public void Charisma(boolean flag) {
		charisma = flag;
		if (!charisma) {
			for (BouncingBall ball : ArrayBalls) {
				ball.GenerateSpeed();
			}
		}
	}

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
		super.paintComponent(g);

		Graphics2D canvas = (Graphics2D) g;
		for (BouncingBall ball : ArrayBalls) {
			if (charisma) {
				ball.CountSpeedXY(mouseX, mouseY);
			}
			ball.Paint(canvas);
		}
	}

	public Field() {
		super();
		setBackground(Color.WHITE);
		TimerRepaint.start();
		ArrayBalls.add(new BouncingBall(this));
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (paused) {
			resume();
		} else {
			pause();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean removeBall() {
		if (ArrayBalls.isEmpty()) {
			return false;
		}
		ArrayBalls.remove(0);
		return true;
	}

}

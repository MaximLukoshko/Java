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
	private MainFrame frame;
	private ArrayList<BouncingBall> ArrayBalls = new ArrayList<BouncingBall>();
	private boolean paused = false;
	private Timer TimerRepaint = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});

	private int mouseX;
	private int mouseY;
	private int mouseX_UP;
	private int mouseY_UP;

	public void addBall() {
		ArrayBalls.add(new BouncingBall(this));
	}

	public synchronized void pause() {
		paused = true;
		frame.pause();
	}

	public synchronized void resume() {
		paused = false;
		frame.start();
		notifyAll();
	}

	public synchronized void canMove(BouncingBall ball) throws InterruptedException {
		if (paused) {
			wait();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D canvas = (Graphics2D) g;
		for (BouncingBall ball : ArrayBalls) {
			ball.Paint(canvas);
		}
	}

	public Field(MainFrame F) {
		super();
		frame = F;
		setBackground(Color.WHITE);
		TimerRepaint.start();
		ArrayBalls.add(new BouncingBall(this));
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public boolean removeBall() {
		if (ArrayBalls.isEmpty()) {
			return false;
		}
		ArrayBalls.remove(0);
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (paused) {
			resume();
		} else {
			pause();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pause();
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX_UP = e.getX();
		mouseY_UP = e.getY();
		this.resume();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

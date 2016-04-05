package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable {

	private static final int MAX_SPEED = 15;
	private static final int MIN_RADIUS = 3;
	private static final int MAX_RADIUS = 40;

	private Field field;
	private int radius;
	private Color color;

	private double x;
	private double y;

	private int speed;
	private double speedX;
	private double speedY;

	@Override
	public void run() {
		try {
			while (true) {
				field.canMove(this);
				if (x + speedX <= radius) {
					speedX = -speedX;
					x = radius;
				} else if (x + speedX >= field.getWidth() - radius) {
					speedX = -speedX;
					x = new Double(field.getWidth() - radius).intValue();
				} else if (y + speedY <= radius) {
					speedY = -speedY;
					y = radius;
				} else if (y + speedY >= field.getHeight() - radius) {
					speedY = -speedY;
					y = new Double(field.getHeight() - radius).intValue();
				} else {
					x += speedX;
					y += speedY;
				}
				Thread.sleep(16 - speed);
			}
		} catch (InterruptedException ex) {

		}

	}

	public void Paint(Graphics2D canvas) {
		canvas.setColor(color);
		canvas.setPaint(color);
		Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
		canvas.draw(ball);
		canvas.fill(ball);

	}

	public BouncingBall(Field field) {
		super();
		this.field = field;
		radius = new Double(Math.random() * (MAX_RADIUS - MIN_RADIUS)).intValue() + MIN_RADIUS;
		speed = new Double(Math.round(5 * MAX_SPEED / radius)).intValue();
		if (speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
		double angle = Math.random() * 2 * Math.PI;
		speedX = speed * Math.cos(angle);
		speedY = speed * Math.sin(angle);
		color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
		x = Math.random() * (field.getSize().getWidth() - 2 * radius) + radius;
		y = Math.random() * (field.getSize().getHeight() - 2 * radius) + radius;
		Thread thisThread = new Thread(this);
		thisThread.start();
	}

	public void onChangeSpeed(int x1, int y1, int x2, int y2) {
		if (((x - x1) * (x - x1) + (y - y1) * (y - y1)) < radius * radius) {
			speedX = (x2 - x1) * 10 / field.getWidth();
			speedY = (y2 - y1) * 10 / field.getHeight();
			speed = (int) Math.sqrt(speedX * speedX + speedY * speedY);
		}
	}
}

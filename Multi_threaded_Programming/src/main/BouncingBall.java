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
		// TODO Auto-generated method stub
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
		Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius,
				2 * radius, 2 * radius);
		canvas.draw(ball);
		canvas.fill(ball);

	}

	public BouncingBall(Field field) {
		super();
		// TODO Auto-generated constructor stub
		this.field = field;
		radius = new Double(Math.random() * (MAX_RADIUS - MIN_RADIUS))
				.intValue() + MIN_RADIUS;
		GenerateSpeed();
		color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());
		x = Math.random() * (field.getSize().getWidth() - 2 * radius) + radius;
		y = Math.random() * (field.getSize().getHeight() - 2 * radius) + radius;
		Thread thisThread = new Thread(this);
		thisThread.start();
	}

	public void GenerateSpeed() {
		speed = new Double(Math.round(5 * MAX_SPEED / radius)).intValue();
		if (speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
		double angle = Math.random() * 2 * Math.PI;
		speedX = 3 * Math.cos(angle);
		speedY = 3 * Math.sin(angle);
	}

	public void CountSpeedXY(int mouseX, int mouseY) {
		speedX = Math.abs(speed)
				* ((mouseX - x) / (Math.sqrt((x - mouseX) * (x - mouseX)
						+ (y - mouseY) * (y - mouseY))));
		speedY = Math.abs(speed)
				* ((mouseY - y) / (Math.sqrt((x - mouseX) * (x - mouseX)
						+ (y - mouseY) * (y - mouseY))));
	}
}

package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JPanel;

public class GraphicsDisplay extends JPanel {

	private Double[][] graphicsData;

	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private double scale;

	private boolean showAxis;
	private boolean showMarkers;
	private boolean showModule;
	private boolean showZeroMarkers;

	private Font axisFont;
	private BasicStroke StrokeAxis;
	private BasicStroke StrokeGraphics;
	private BasicStroke StrokeMarker;

	public GraphicsDisplay() {
		setBackground(Color.WHITE);
		StrokeGraphics = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND, 10.0f, new float[] { 4, 1, 2, 1, 2, 1,
						4, 1, 1 }, 0.0f);
		StrokeAxis = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		StrokeMarker = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, null, 0.0f);
		axisFont = new Font("Serif", Font.BOLD, 36);
	}

	public void showGraphics(Double[][] graphicsData) {
		this.graphicsData = graphicsData;
		repaint();
	}

	public void setShowMarkers(boolean showMarkers) {
		this.showMarkers = showMarkers;
		repaint();
	}

	public void setShowAxis(boolean showAxis) {
		this.showAxis = showAxis;
		repaint();
	}

	public void setShowZeroMarkers(boolean enabled) {
		// TODO Auto-generated method stub
		showZeroMarkers = enabled;
		repaint();
	}

	protected Point2D.Double xyToPoint(double x, double y) {
		double deltaX = x - minX;
		double deltaY = maxY - y;
		return new Point2D.Double(deltaX * scale, deltaY * scale);
	}

	protected Point2D.Double shiftPoint(Point2D.Double src, double deltaX,
			double deltaY) {
		Point2D.Double dest = new Point2D.Double();
		dest.setLocation(src.getX() + deltaX, src.getY() + deltaY);
		return dest;
	}

	protected void paintGraphics(Graphics2D canvas) {
		canvas.setStroke(StrokeGraphics);
		canvas.setColor(Color.green);
		GeneralPath graphics = new GeneralPath();
		for (int i = 0; i < graphicsData.length; i++) {
			Point2D.Double point = xyToPoint(graphicsData[i][0],
					graphicsData[i][1]);
			if (i > 0)
				graphics.lineTo(point.getX(), point.getY());
			else
				graphics.moveTo(point.getX(), point.getY());
		}

		canvas.draw(graphics);
	}

	protected void paintAxis(Graphics2D canvas) {
		canvas.setStroke(StrokeAxis);
		canvas.setColor(Color.darkGray);
		canvas.setPaint(Color.DARK_GRAY);
		canvas.setFont(axisFont);
		FontRenderContext context = canvas.getFontRenderContext();

		if (minX <= 0.0 && maxX >= 0.0) {
			canvas.draw(new Line2D.Double(xyToPoint(0, maxY),
					xyToPoint(0, minY)));
			GeneralPath arrow = new GeneralPath();
			Point2D.Double lineEnd = xyToPoint(0, maxY);
			arrow.moveTo(lineEnd.getX(), lineEnd.getY());
			arrow.lineTo(arrow.getCurrentPoint().getX() + 5, arrow
					.getCurrentPoint().getY() + 20);
			arrow.lineTo(arrow.getCurrentPoint().getX() - 10, arrow
					.getCurrentPoint().getY());
			arrow.closePath();
			canvas.draw(arrow);
			canvas.fill(arrow);
			Rectangle2D bounds = axisFont.getStringBounds("y", context);
			Point2D.Double labelPos = xyToPoint(0, maxY);
			canvas.drawString("y", (float) labelPos.getX() + 10,
					(float) (labelPos.getY() - bounds.getY()));

			// labelPos = xyToPoint(0, 0);
			// canvas.drawString("0, 0", (float) labelPos.getX(),
			// (float) labelPos.getY());
		}

		if (minY <= 0.0 && maxY >= 0.0) {
			canvas.draw(new Line2D.Double(xyToPoint(minX, 0.0), xyToPoint(maxX,
					0.0)));
			GeneralPath arrow = new GeneralPath();
			Point2D.Double lineEnd = xyToPoint(maxX, 0.0);
			arrow.moveTo(lineEnd.getX(), lineEnd.getY());
			arrow.lineTo(arrow.getCurrentPoint().getX() - 20, arrow
					.getCurrentPoint().getY() - 5);
			arrow.lineTo(arrow.getCurrentPoint().getX(), arrow
					.getCurrentPoint().getY() + 10);
			arrow.closePath();
			canvas.draw(arrow);
			canvas.fill(arrow);
			Rectangle2D bounds = axisFont.getStringBounds("x", context);
			Point2D.Double labelPos = xyToPoint(maxX, 0);
			canvas.drawString("x",
					(float) (labelPos.getX() - bounds.getWidth() - 10),
					(float) (labelPos.getY() + bounds.getY()));
		}
	}

	protected void paintZeroMarkers(Graphics2D canvas) {
		boolean sign = graphicsData[0][1] > 0 ? true : false;

		canvas.setColor(Color.MAGENTA);
		for (int i = 0; i < graphicsData.length; i++) {
			if (AnalyseZero(sign, i)) {
				sign = !sign;
				Point2D.Double center = xyToPoint(graphicsData[i][0],
						graphicsData[i][1]);
				if (Math.abs(graphicsData[i][1]) < 1e-8) {
					Ellipse2D.Double el = new Ellipse2D.Double(
							center.getX() - 5.5, center.getY() - 5.5, 11, 11);
					canvas.fill(el);
					canvas.draw(el);
				} else {
					Point2D.Double centerPrev = xyToPoint(
							graphicsData[i - 1][0], graphicsData[i - 1][1]);
					Ellipse2D.Double el = new Ellipse2D.Double(
							(center.getX() + centerPrev.getX()) / 2 - 5.5,
							(center.getY() + centerPrev.getY()) / 2 - 5.5, 11,
							11);
					canvas.fill(el);
					canvas.draw(el);
				}
			}
		}
	}

	protected void paintMarkers(Graphics2D canvas) {
		canvas.setStroke(StrokeMarker);
		canvas.setColor(Color.BLUE);
		for (int i = 0; i < graphicsData.length; i++) {
			GeneralPath marker = new GeneralPath();
			Point2D.Double center = xyToPoint(graphicsData[i][0],
					graphicsData[i][1]);

			if (AnalyseParity(new Double(graphicsData[i][1]))) {
				canvas.setColor(Color.RED);
			}
			marker.moveTo(center.getX(), center.getY());

			marker.moveTo(center.getX() - 5.5, center.getY() - 2.5);
			marker.lineTo(center.getX() - 5.5, center.getY() + 2.5);

			marker.moveTo(center.getX() + 5.5, center.getY() - 2.5);
			marker.lineTo(center.getX() + 5.5, center.getY() + 2.5);

			marker.moveTo(center.getX() - 2.5, center.getY() + 5.5);
			marker.lineTo(center.getX() + 2.5, center.getY() + 5.5);

			marker.moveTo(center.getX() - 2.5, center.getY() - 5.5);
			marker.lineTo(center.getX() + 2.5, center.getY() - 5.5);

			marker.moveTo(center.getX() - 5.5, center.getY());
			marker.lineTo(center.getX() + 5.5, center.getY());

			marker.moveTo(center.getX(), center.getY() - 5.5);
			marker.lineTo(center.getX(), center.getY() + 5.5);

			canvas.draw(marker);
			canvas.setColor(Color.BLUE);
		}
	}

	private boolean AnalyseParity(Double value) {

		DecimalFormat formatter;
		formatter = (DecimalFormat) NumberFormat.getInstance();

		formatter.setMaximumFractionDigits(3);
		formatter.setGroupingUsed(false);

		DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();

		formatter.getDecimalFormatSymbols();
		dottedDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dottedDouble);

		String Number = formatter.format(value);

		Number += '.';

		if (Integer
				.parseInt(String.valueOf(Number.charAt(Number.indexOf('.') - 1))) % 2 == 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean AnalyseZero(boolean sign, int i) {
		if ((graphicsData[i][1] > 0) == sign) {
			return false;
		} else {
			return true;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (graphicsData == null || graphicsData.length == 0)
			return;
		minX = graphicsData[0][0];
		maxX = graphicsData[graphicsData.length - 1][0];
		minY = graphicsData[0][1];
		maxY = minY;

		for (int i = 0; i < graphicsData.length; i++) {
			if (graphicsData[i][1] < minY)
				minY = graphicsData[i][1];
			if (graphicsData[i][1] > maxY)
				maxY = graphicsData[i][1];
		}

		double scaleX = getSize().getWidth() / (maxX - minX);
		double scaleY = getSize().getHeight() / (maxY - minY);

		scale = Math.min(scaleX, scaleY);

		if (scale == scaleX) {
			double yIncrement = (getSize().getHeight() / scale - (maxY - minY)) / 2;
			maxY += yIncrement;
			minY -= yIncrement;
		}

		if (scale == scaleY) {
			double xIncrement = (getSize().getWidth() / scale - (maxX - minX)) / 2;
			maxX += xIncrement;
			minX -= xIncrement;
		}

		Graphics2D canvas = (Graphics2D) g;
		Stroke oldStroke = canvas.getStroke();
		Color oldColor = canvas.getColor();
		Font oldFont = canvas.getFont();
		Paint oldPaint = canvas.getPaint();

		if (showAxis) {
			paintAxis(canvas);
		}
		paintGraphics(canvas);
		if (showMarkers) {
			paintMarkers(canvas);
		}
		if (showZeroMarkers) {
			paintZeroMarkers(canvas);
		}

		canvas.setColor(oldColor);
		canvas.setFont(oldFont);
		canvas.setStroke(oldStroke);
		canvas.setPaint(oldPaint);
	}
}

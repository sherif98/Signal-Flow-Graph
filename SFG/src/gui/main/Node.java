package gui.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Node {
	private int number;
	private double x, y;

	public Node(int number, double x, double y) {
		this.number = number;
		this.x = x;
		this.y = y;
	}

	public void draw(GraphicsContext g, boolean selected, Color color) {
		Paint prev = g.getFill();
		if (!selected) {
			g.strokeOval(x, y, 30, 30);
		} else {
			g.setFill(color);
			g.fillOval(x, y, 30, 30);
		}
		g.setFill(Color.RED);
		g.fillText("" + number, x + 13, y + 17);
		g.setFill(prev);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getNumber() {
		return number;
	}

	public boolean intersects(double x2, double y2) {
		boolean horizontalCondition = x2 < x + 30 && x2 > x ? true : false;
		boolean verticalCondirion = y2 > y && y2 < y + 17 ? true : false;
		return horizontalCondition && verticalCondirion;
	}
}

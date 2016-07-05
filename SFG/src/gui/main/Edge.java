package gui.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Edge {
	private Node from, to;
	private double value;

	public Edge(Node from, Node to, double value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	public double getValue() {
		return value;
	}

	public void draw(GraphicsContext g) {
		if (Math.abs(from.getNumber() - to.getNumber()) > 1 || from.getX() > to.getX()) {
			drawArc(g);
		} else {
			drawLine(g);
		}
		drawArrow(g);
	}

	private void drawValue(GraphicsContext g, double newX, double newY) {
		g.fillText("" + value, newX, newY);
	}

	private void drawArrow(GraphicsContext g) {
		Arrow arrow = new Arrow(to.getX(), to.getY());
		if (from.getX() < to.getX()) {
			arrow.drawRight(g);
		} else {
			arrow.drawLeft(g);
		}
	}

	private void drawArc(GraphicsContext g) {
		double xMid = (from.getX() + to.getX()) / 2;
		double yMid = (from.getY() + to.getY()) / 2;
		double a = from.getX() - to.getX();
		double b = from.getY() - to.getY();
		double len = Math.sqrt(a * a + b * b);
		double newX = xMid - len / 2;
		double newY = yMid - len / 2;

		double q = to.getX() - xMid;
		double w = to.getY() - yMid;
		double theta = Math.atan(w / q);
		theta *= 180;
		theta /= Math.PI;
		// System.out.println(theta);

		g.strokeArc(newX, newY, len, len, 360 - theta, 180, ArcType.OPEN);
		drawValue(g, newX, newY);
	}

	private void drawLine(GraphicsContext g) {
		g.strokeLine(from.getX() + 13, from.getY() + 5, to.getX() + 5, to.getY() + 5);
		drawValue(g, (from.getX() + to.getX()) / 2, (from.getY() + to.getY()) / 2);
	}
}

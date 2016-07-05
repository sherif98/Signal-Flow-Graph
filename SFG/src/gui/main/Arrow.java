package gui.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;

public class Arrow extends Polygon {
	private double x, y;

	public Arrow(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void drawRight(GraphicsContext g) {
		double upX = x - 10;
		double upY = y - 10;
		g.strokeLine(x, y, upX, upY);
		double downY = y + 10;
		g.strokeLine(x, y, upX, downY);
	}

	public void drawLeft(GraphicsContext g) {
		double upX = x + 10;
		double upY = y - 10;
		g.strokeLine(x, y, upX, upY);
		double downY = y + 10;
		g.strokeLine(x, y, upX, downY);
	}

}

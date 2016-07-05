package gui.main;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class CursorInfo {
	private GraphicsContext g;
	private double x, y;
	private int nextValidNumber;
	private ArrayList<Node> drawnShapes;

	public CursorInfo(GraphicsContext g, double x, double y, int nextValidNumber, ArrayList<Node> drawnShapes) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.nextValidNumber = nextValidNumber;
		this.drawnShapes = drawnShapes;
	}

	public ArrayList<Node> getDrawnShapes() {
		return drawnShapes;
	}

	public GraphicsContext getG() {
		return g;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getNextValidNumber() {
		return nextValidNumber;
	}

}

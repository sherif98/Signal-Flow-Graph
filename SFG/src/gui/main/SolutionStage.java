package gui.main;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.ForwardPath;
import main.Loop;

public class SolutionStage {
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext g;
	private int nextAvailable = 90;

	public SolutionStage(ArrayList<ForwardPath> forwardPaths, ArrayList<Loop> loops, double gain) {
		stage = new Stage();
		canvas = new Canvas(1000, 1000);
		g = canvas.getGraphicsContext2D();
		g.fillText("Forward Paths", 30, nextAvailable);
		nextAvailable += 30;
		drawForwardPaths(forwardPaths);
		nextAvailable += 30;
		g.fillText("Loops", 30, nextAvailable);
		nextAvailable += 30;
		drawLoops(loops);
		nextAvailable += 30;
		g.fillText("Over All Gain is ", 30, nextAvailable);
		nextAvailable += 30;
		g.fillText("" + gain, 30, nextAvailable);
		FlowPane root = new FlowPane(10, 10);
		root.getChildren().add(canvas);
		stage.setScene(new Scene(root, 1000, 1000));
		stage.show();
	}

	private void drawLoops(ArrayList<Loop> loops) {
		int x = 30, y = nextAvailable;
		int horizontalOffset = 50, verticalOffset = 80;
		for (Loop loop : loops) {
			for (int number : loop.nodes) {
				Node node = new Node(number, x, y);
				x += horizontalOffset;
				node.draw(g, false, Color.BLACK);
			}
			g.fillText("Gain is " + loop.gain, x, y + 15);
			y += verticalOffset;
			x = 30;
		}
		nextAvailable = y;
	}

	private void drawForwardPaths(ArrayList<ForwardPath> forwardPaths) {
		int x = 30, y = nextAvailable;
		int horizontalOffset = 50, verticalOffset = 80;
		for (ForwardPath path : forwardPaths) {
			for (int number : path.nodes) {
				Node node = new Node(number, x, y);
				x += horizontalOffset;
				node.draw(g, false, Color.BLACK);
			}
			g.fillText("Gain is" + path.gain, x, y + 15);
			y += verticalOffset;
			x = 30;
		}
		nextAvailable = y;
	}
}

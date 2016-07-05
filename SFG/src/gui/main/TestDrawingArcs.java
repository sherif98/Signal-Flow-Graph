package gui.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestDrawingArcs extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Node one = new Node(0, 100, 300);
		Node two = new Node(1, 400, 100);
		Canvas canvas = new Canvas(500, 500);
		GraphicsContext g = canvas.getGraphicsContext2D();
		one.draw(g, false, Color.WHITE);
		two.draw(g, false, Color.WHITE);
		
		Edge e = new Edge(one, two, 5);
		e.draw(g);
		
		
		FlowPane root = new FlowPane(500, 500);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}

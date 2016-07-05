package gui.main;

import java.util.ArrayList;

import gui.controllers.Solver;
import gui.graph.GraphEdge;
import gui.graph.GuiGraph;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Starter extends Application {

	private Canvas canvas;
	private GraphicsContext g;
	private ArrayList<Node> drawnNodes;
	private ArrayList<Edge> drawnEdges;
	private Cursor cursor;
	private Button setStart;
	private Button setEnd;
	private Button selectionMode;
	private Button drawingMode;
	private Button addEdge;
	private Button solve;
	private Button selectSource;
	private Button selectSink;
	private Label selectedNodeNumber;
	private Label selectedStartingNode;
	private Label selectedEndNode;
	private Node startNode, endNode, selectedNode, sourceNode, sinkNode;
	private TextField valueOfEdge;

	@Override
	public void init() throws Exception {
		canvas = new Canvas(800, 800);
		g = canvas.getGraphicsContext2D();
		g.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
		drawnNodes = new ArrayList<>();
		drawnEdges = new ArrayList<>();
		cursor = new Cursor();
		setStart = new Button("set as start");
		setEnd = new Button("set as end");
		selectionMode = new Button("Selection Mode");
		drawingMode = new Button("Drawing Mode");
		addEdge = new Button("draw Edge");
		solve = new Button("Get Solution");
		selectSource = new Button("select source");
		selectSink = new Button("select sink");
		selectedNodeNumber = new Label("selected is ");
		selectedStartingNode = new Label("start is ");
		selectedEndNode = new Label("end is ");
		valueOfEdge = new TextField("1.0");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane rootNode = new BorderPane();

		// setting up the right
		BorderPane rightNode = new BorderPane();
		VBox box = new VBox(20);
		box.getChildren().addAll(selectedNodeNumber, selectedStartingNode, selectedEndNode, setStart, setEnd,
				valueOfEdge, addEdge, solve, selectSource, selectSink);
		rightNode.setCenter(box);
		rootNode.setRight(rightNode);
		// right done

		// setting up the top
		BorderPane topNode = new BorderPane();
		FlowPane topFlow = new FlowPane(10, 10);
		topFlow.getChildren().addAll(drawingMode, selectionMode);
		topNode.setTop(topFlow);
		rootNode.setTop(topNode);
		// top done

		// setting up listeners

		setUpCanvasListeners();
		setUpCursorModeLiteners();
		setUpSelectionSettersButtonsListeners();
		setUpDrawEdgeButtonListener();
		setUpSolvingButtonListener();
		setUpSourceSinkButtons();
		// listeners done

		rootNode.setCenter(canvas);

		primaryStage.setScene(new Scene(rootNode, 1000, 1000));
		primaryStage.show();

	}

	private void setUpSourceSinkButtons() {
		selectSource.setOnAction((e) -> sourceNode = selectedNode);
		selectSink.setOnAction((e) -> sinkNode = selectedNode);
	}

	private void setUpSolvingButtonListener() {
		solve.setOnAction((e) -> {
			ArrayList<GraphEdge> edges = new ArrayList<>();
			for (Edge ee : drawnEdges) {
				edges.add(new GraphEdge(ee.getFrom().getNumber(), ee.getTo().getNumber(), ee.getValue()));
			}
			GuiGraph g = new GuiGraph(edges, drawnNodes.size(), sourceNode.getNumber(), sinkNode.getNumber());
			Solver solve = new Solver(g);
			new SolutionStage(solve.getForwardPaths(), solve.getLoops(), solve.getGain());
		});

	}

	private void setUpDrawEdgeButtonListener() {
		addEdge.setOnAction((e) -> {
			Edge edge = new Edge(startNode, endNode, Double.parseDouble(valueOfEdge.getText()));
			edge.draw(g);
			drawnEdges.add(edge);
		});
	}

	private void setUpSelectionSettersButtonsListeners() {
		setStart.setOnAction((e) -> {
			if (selectedNode == null) {
				return;
			}
			startNode = selectedNode;
			setStartNodeLabelText(startNode.getNumber());
		});

		setEnd.setOnAction((e) -> {
			if (selectedNode == null) {
				return;
			}
			endNode = selectedNode;
			setEndNodeLabelText(endNode.getNumber());
		});

	}

	private void setEndNodeLabelText(int number) {
		selectedEndNode.setText("end is " + number);
	}

	private void setStartNodeLabelText(int number) {
		selectedStartingNode.setText("start is " + number);
	}

	private void setUpCursorModeLiteners() {
		selectionMode.setOnAction((e) -> cursor.setState(new SelectNodesState()));
		drawingMode.setOnAction((e) -> cursor.setState(new DrawingNodesState()));
	}

	private void setUpCanvasListeners() {
		canvas.setOnMouseMoved((e) -> {
			checkIntersectionsWithNodes(e.getX(), e.getY());
		});
		canvas.setOnMouseClicked((e) -> {
			cursor.click(new CursorInfo(g, e.getX(), e.getY(), drawnNodes.size(), drawnNodes));
			selectedNode = cursor.getSelected();
			setSelectedNodeLabelText();
		});

	}

	private void setSelectedNodeLabelText() {
		if (selectedNode == null) {
			return;
		}
		selectedNodeNumber.setText("selected is " + selectedNode.getNumber());
	}

	private void checkIntersectionsWithNodes(double x, double y) {
		Node tmp = null;
		for (Node node : drawnNodes) {
			if (node.intersects(x, y)) {
				tmp = node;
				break;
			}
		}
		if (tmp == null) {
			return;
		}
		reDrawNodes(tmp, Color.YELLOW);
		reDrawEdges();
	}

	private void reDrawEdges() {
		for (Edge e : drawnEdges) {
			e.draw(g);
		}
	}

	private void reDrawNodes(Node tmp, Color color) {
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		g.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Node node : drawnNodes) {
			if (node == tmp) {
				node.draw(g, true, color);
			} else {
				node.draw(g, false, Color.BLACK);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

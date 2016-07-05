package gui.graph;

public class GraphEdge {
	private int from, to;
	private double value;

	public GraphEdge(int from, int to, double value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public double getValue() {
		return value;
	}

}

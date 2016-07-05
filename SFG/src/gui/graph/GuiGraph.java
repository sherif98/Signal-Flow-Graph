package gui.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class GuiGraph {
	private ArrayList<GraphEdge> edges;
	public int numberOfNodes;
	private int source, sink;

	public GuiGraph(ArrayList<GraphEdge> edges, int numberOfNodes, int source, int sink) {
		this.source = source;
		this.sink = sink;
		this.edges = edges;
		this.numberOfNodes = numberOfNodes;
	}

	public int getSource() {
		return source;
	}

	public int getSink() {
		return sink;
	}

	public Iterable<Integer> getVertices() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = getSource(); i <= getSink(); i++) {
			list.add(i);
		}
		return list;
	}

	public Iterable<GraphEdge> getEdges() {
		return edges;
	}

}

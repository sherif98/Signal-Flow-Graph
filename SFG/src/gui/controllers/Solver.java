package gui.controllers;

import java.util.ArrayList;

import gui.graph.GuiGraph;
import main.ForwardPath;
import main.Graph;
import main.Loop;
import main.Mason;

public class Solver {
	GuiGraph graph;
	Graph g;

	// create instances you need
	public Solver(GuiGraph graph) {
		this.graph = graph;
		g = new Graph();
		g.buildGraph(graph);
	}

	public ArrayList<ForwardPath> getForwardPaths() {// change return type
		return g.getForwardPaths(graph.getSource(), graph.getSink());
	}

	public ArrayList<Loop> getLoops() {// change return type
		return g.getLoops();
	}

	public double getGain() {
		Mason mason = new Mason();
		return mason.getTransferFunction(g, graph.getSource(), graph.getSink());
	}

}

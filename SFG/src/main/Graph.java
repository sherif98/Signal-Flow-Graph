package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import gui.graph.GraphEdge;
import gui.graph.GuiGraph;

/**
 * 
 * @author Tarek Description: Representing the graph
 *
 */

// because of the array of array-lists
@SuppressWarnings("all")
public class Graph {
	// instance variables

	static final int invalid = Integer.MIN_VALUE;
	public int numOfNodes;
	// should be array-list of array-list but it would be annoying
	public ArrayList<Edge<Integer, Double>>[] adj;
	private boolean[] visited;
	public double[][] graph;

	/** constructs the graph using GuiGraph object */
	public void buildGraph(GuiGraph g) {
		numOfNodes = g.numberOfNodes;
		initialize();
		for (GraphEdge edge : g.getEdges()) {
			int x = edge.getFrom();
			int y = edge.getTo();
			double w = edge.getValue();
			if (graph[x][y] == invalid) {
				graph[x][y] = w;
			} else {
				graph[x][y] += w;
			}
		}
		for (int i = 0; i < numOfNodes; ++i) {
			for (int j = 0; j < numOfNodes; ++j) {
				if (graph[i][j] == invalid || graph[i][j] == 0) //to avoid division by zero
					continue;
				adj[i].add(new Edge<>(j, graph[i][j]));
			}
		}
	}

	/** reads and builds the graph from a file */
	public void buildGraph(File file) {
		try (Scanner in = new Scanner(file)) {
			String[] first = in.nextLine().split(" ");
			int n = Integer.parseInt(first[0]);
			int m = Integer.parseInt(first[1]);
			numOfNodes = n;
			initialize();
			for (int i = 0; i < m; ++i) {
				String[] edge = in.nextLine().split(" ");
				int x = Integer.parseInt(edge[0]);
				int y = Integer.parseInt(edge[1]);
				int w = Integer.parseInt(edge[2]);
				// could have cascaded edges
				if (graph[x][y] == invalid) {
					graph[x][y] = w;
				} else {
					graph[x][y] += w;
				}
			}
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (graph[i][j] == invalid || graph[i][j] == 0) // to avoid
																	// division
																	// by zero
						continue;
					adj[i].add(new Edge<>(j, graph[i][j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** constructing the graph given its adjacency matrix */
	public void buildGraph(double[][] graph) {
		this.numOfNodes = graph.length;
		initialize();
		this.graph = graph;
		for (int i = 0; i < graph.length; ++i) {
			for (int j = 0; j < graph[i].length; ++j) {
				if (graph[i][j] == invalid || graph[i][j] == 0)
					continue;
				adj[i].add(new Edge<>(j, graph[i][j]));
			}
		}
	}

	/** @return all the forward paths between two nodes in the graph */
	public ArrayList<ForwardPath> getForwardPaths(int start, int end) {
		Arrays.fill(visited, false);
		ArrayList<ForwardPath> ans = new ArrayList<>();
		ForwardPath temp = new ForwardPath();
		dfs(start, end, temp, ans, true);
		return ans;
	}

	// helper method to find all the forward paths between two nodes
	// and all the loops in the graph
	private void dfs(int node, int destination, ForwardPath path, ArrayList<ForwardPath> ans, boolean flag) {
		if (node == destination && flag) {
			// shit I know
			ForwardPath other = (ForwardPath) path.clone();
			other.nodes.add(node);
			ans.add(other);
			return;
		} else if (node == destination && !flag) {
			flag = true;
		}
		if (node != destination) {
			visited[node] = true;
		}
		path.nodes.add(node);
		for (Edge<Integer, Double> e : adj[node]) {
			if (!visited[e.first]) {
				path.gain *= e.second;
				dfs(e.first, destination, path, ans, flag);
				// removing the effect of adding this edge after returning
				// to try something else
				path.gain /= e.second;
			}
		}
		visited[node] = false;
		path.nodes.remove(new Integer(node));
	}

	/** @return all the loops in the graph */
	public ArrayList<Loop> getLoops() {
		ArrayList<ForwardPath> ans = new ArrayList<>();
		ForwardPath temp;
		for (int i = 0; i < numOfNodes; ++i) {
			Arrays.fill(visited, false);
			temp = new ForwardPath();
			dfs(i, i, temp, ans, false);
		}
		ArrayList<Loop> answer = new ArrayList<>();
		for (ForwardPath x : ans) {
			Loop l = new Loop();
			l.setNodes(x.nodes);
			l.gain = x.gain;
			if (!answer.contains(l))
				answer.add(l);
		}
		return answer;
	}

	// for debugging
	public void printlAdj() {
		for (int i = 0; i < numOfNodes; ++i) {
			System.out.print(i + ": ");
			for (int j = 0; j < adj[i].size(); ++j) {
				System.out.print(adj[i].get(j) + " ");
			}
			System.out.println();
		}
	}

	// this is a replacement of a constructor
	// couldn't do this in the constructor because it requires the number of
	// nodes to be known
	private void initialize() {
		adj = new ArrayList[numOfNodes];
		visited = new boolean[numOfNodes];
		graph = new double[numOfNodes][numOfNodes];
		for (int i = 0; i < numOfNodes; ++i) {
			adj[i] = new ArrayList<>();
			for (int j = 0; j < numOfNodes; ++j) {
				graph[i][j] = invalid;
			}
		}
	}

	// representation of weighted edge
	// like pair in C++
	static class Edge<T, U> {
		public T first;
		public U second;

		public Edge(T first, U second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString() {
			return "[" + first + ", " + second + "]";
		}
	}
}

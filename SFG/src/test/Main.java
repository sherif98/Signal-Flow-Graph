package test;

import java.io.File;
import java.util.ArrayList;

import main.ForwardPath;
import main.Graph;
import main.Loop;
import main.Mason;

public class Main {
	public static void main(String[] args) {
		Graph g = new Graph();
		File file = new File("input2.txt");
		
		g.buildGraph(file);
		ArrayList<Loop> ans = g.getLoops();
		System.out.println("loops");
		for (Loop x : ans) {
			System.out.println(x);
			System.out.println(Long.toBinaryString(x.getMask()));
		}
		ArrayList<ForwardPath> p = g.getForwardPaths(0, 5);
		System.out.println("---------------------------------------------");
		System.out.println("forwardPaths");
		for (ForwardPath x : p) {
			System.out.println(x);
			System.out.println(Long.toBinaryString(x.getMask()));
		}
		System.out.println("-------------------------------------------");
		Mason solver = new Mason();
		System.out.println(solver.getTransferFunction(g, 0, 5));
	}
}

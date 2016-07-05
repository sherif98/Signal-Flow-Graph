package main;

import java.util.ArrayList;

/**
 * 
 * @author Tarek Description: representing forward Paths in the graph
 *
 */
public class ForwardPath implements Cloneable {
	// instance variables
	public double gain = 1;
	public ArrayList<Integer> nodes = new ArrayList<>();

	@Override
	public String toString() {
		return nodes.toString() + ": " + gain;
	}

	public void setNodes(ArrayList<Integer> givenNodes) {
		nodes = new ArrayList<>();
		for (Integer i : givenNodes) {
			nodes.add(i);
		}
	}

	@Override
	public Object clone() {
		ForwardPath other = new ForwardPath();
		other.gain = this.gain;
		for (Integer x : this.nodes) {
			other.nodes.add(x);
		}
		return other;
	}

	public long getMask() {
		long mask = 0;
		for (Integer i : nodes) {
			mask |= (1 << i);
		}
		return mask;
	}
}

package main;

import java.util.ArrayList;

/**
 * 
 * @author Tarek Description: this class will represent the loop in the graph
 */
public class Loop {
	// instance variables
	// mask for the loop to make things easy
	public double gain = 1;
	public ArrayList<Integer> nodes = new ArrayList<>();

	/** @return true if the two loops touches each other false otherwise */
	public boolean touch(Loop loop) {
		return (getMask() & loop.getMask()) != 0;
	}
	
	public boolean touch(ForwardPath path) {
		return (getMask() & path.getMask()) != 0;
	}
	
	public void setNodes(ArrayList<Integer> givenNodes) {
		nodes = new ArrayList<>();
		for (Integer i : givenNodes) {
			nodes.add(i);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null || !(other instanceof Loop))
			return false;
		Loop x = (Loop) other;
		if ((x.gain != gain) || (x.nodes.size() != nodes.size()))
			return false;
		for (Integer i : x.nodes) {
			if (!nodes.contains(i))
				return false;
		}
		return true;
	}
	
	public long getMask() {
		long mask = 0;
		for (Integer i : nodes) {
			mask |= (1 << i);
		}
		return mask;
	}
	@Override
	public String toString() {
		return nodes.toString() + ": " + gain;
	}
}

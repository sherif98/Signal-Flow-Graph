package main;

import java.util.ArrayList;

/**
 * 
 * @author Tarek Description: calculating the t.f between two given nodes in the
 *         graph
 *
 */
public class Mason {

	// TODO calculate the gain between nodes different from R and C

	/** @return the over all t.f between two nodes in a graph */
	public double getTransferFunction(Graph g, int source, int sink) {
		ArrayList<Loop> loops = g.getLoops();
		ArrayList<ForwardPath> paths = g.getForwardPaths(source, sink);
		double delta = 0;
		double ans = 1;
		delta = getDelta(loops);
		//mason's summation
		for (ForwardPath p : paths) {
			// find all the nodes non-touching this path
			ArrayList<Loop> token = getNonTouchingfPath(loops, p);
			// find the gain of these loops and multiplied to the gain of the
			// path then multiplied it with the answer
			ans += (p.gain * getDelta(token));
		}
		ans /= (delta);
		return ans;
	}

	// helper method to find the delta between two nodes
	private double getDelta(ArrayList<Loop> loops) {
		double delta = 1;
		// generating every possible subset of the loops
		for (long loopmask = 0; loopmask < (1 << loops.size()); ++loopmask) {
			ArrayList<Loop> token = getToken(loops, loopmask);
			// make sure no touching happens between the loops
			if (!isTouched(token)) {
				double overGain = getOverGain(token);
				// if there number is even we add their gain else we subtract it
				if (token.size() % 2 == 0) {
					delta += overGain;
				} else {
					delta -= overGain;
				}
			}
		}
		return delta;
	}

	// check if any touching happens between any pair of nodes
	// TODO check that it is true if a, b non-touching and b, c non-touching and
	// a, c non-touching
	// then a, b, c non-touching
	private boolean isTouched(ArrayList<Loop> loops) {
		for (int i = 0; i < loops.size(); ++i) {
			for (int j = i + 1; j < loops.size(); ++j) {
				if (loops.get(i).touch(loops.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	// helper method to find the over gain of group of loops
	// the over gain is the product of the gain of the individual loops
	private double getOverGain(ArrayList<Loop> loops) {
		if (loops.size() == 0) {
			return 0;
		}
		double ans = 1;
		for (int i = 0; i < loops.size(); ++i) {
			ans *= loops.get(i).gain;
		}
		return ans;
	}

	// helper method to find the loops in the given mask
	private ArrayList<Loop> getToken(ArrayList<Loop> loops, long mask) {
		ArrayList<Loop> token = new ArrayList<>();
		for (int i = 0; i < loops.size(); ++i) {
			if ((mask & (1 << i)) != 0) {
				token.add(loops.get(i));
			}
		}
		return token;
	}

	// getting all the loops non-touching a specific path
	private ArrayList<Loop> getNonTouchingfPath(ArrayList<Loop> loops, ForwardPath p) {
		ArrayList<Loop> ans = new ArrayList<>();
		for (Loop x : loops) {
			if (!x.touch(p)) {
				ans.add(x);
			}
		}
		return ans;
	}
}

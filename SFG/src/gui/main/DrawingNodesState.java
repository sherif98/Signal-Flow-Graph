package gui.main;

import javafx.scene.paint.Color;

public class DrawingNodesState implements CursorState {

	@Override
	public void click(CursorInfo info) {
		Node n = new Node(info.getNextValidNumber(), info.getX(), info.getY());
		info.getDrawnShapes().add(n);
		n.draw(info.getG(), false, Color.BLACK);
	}


}

package gui.main;

public class SelectNodesState implements CursorState {
	Node tmp;

	@Override
	public void click(CursorInfo info) {
		tmp = null;
		for (Node node : info.getDrawnShapes()) {
			if (node.intersects(info.getX(), info.getY())) {
				tmp = node;
				break;
			}
		}
	}

	@Override
	public Node getSelectedNode() {
		return tmp;
	}

}

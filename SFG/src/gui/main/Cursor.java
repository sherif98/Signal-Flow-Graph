package gui.main;

public class Cursor {
	private CursorState state;

	public Cursor() {
		state = new DrawingNodesState();
	}

	public void click(CursorInfo info) {
		state.click(info);
	}

	public void setState(CursorState newState) {
		state = newState;
	}

	public Node getSelected() {
		// TODO Auto-generated method stub
		return state.getSelectedNode();
	}
}

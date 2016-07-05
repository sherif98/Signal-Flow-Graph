package gui.main;

public interface CursorState {
	public void click(CursorInfo info);

	default public Node getSelectedNode() {
		return null;
	}
}

package roguelike;
import asciiPanel.AsciiPanel;


public class Tiles {
	private char ID;
	public int x;
	public int y;
	
	public Tiles(int x, int y, char id) {
		this.x = x;
		this.y = y;
		this.ID = id;
	}
	
	public void renderTile() {
		terminal.write(this.ID, this.x, this.y);
	}

}

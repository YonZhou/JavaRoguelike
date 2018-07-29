package roguelike;
import asciiPanel.AsciiPanel;


public class Tiles extends Entity{
	private char ID;
	public int x;
	public int y;
	private AsciiPanel terminal;
	
	public Tiles(int x, int y, char id, AsciiPanel terminal) {
		super(x,y, terminal);
		this.x = x;
		this.y = y;
		this.ID = id;
		this.terminal = terminal;
	}
	
	public void renderTile() {
		terminal.write(this.ID, this.x, this.y);
	}
	

}

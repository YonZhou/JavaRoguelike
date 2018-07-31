package roguelike;
import java.awt.Color;

import asciiPanel.AsciiPanel;


public class Tiles extends Entity{
	private char ID;
	public int x;
	public int y;
	private AsciiPanel terminal;
	private boolean isWalkable;
	private Color color;
	
	public Tiles(int x, int y, char id, AsciiPanel terminal, Color color, boolean isWalkable) {
		super(id, x,y, color, terminal, isWalkable);
		this.x = x;
		this.y = y;
		this.ID = id;
		this.terminal = terminal;
		this.color = color;
		this.isWalkable = isWalkable;
	}
	
//	public Tiles(int x2, int y2, char id2, AsciiPanel terminal2, Color color2) {
//		super(id2, x2,y2, color2, terminal2);
//	}

	public void renderTile() {
		super.drawEntity();
		//terminal.write(this.ID, this.x, this.y, this.color);
	}
	

}

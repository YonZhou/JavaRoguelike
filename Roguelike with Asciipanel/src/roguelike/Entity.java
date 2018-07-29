package roguelike;

import asciiPanel.AsciiPanel;

public class Entity {
	public int x;
	public int y;
	private AsciiPanel panel;
	
	public Entity(int x, int y, AsciiPanel p) {
		this.x = x;
		this.y = y;
		this.panel = p;
	}
	

}

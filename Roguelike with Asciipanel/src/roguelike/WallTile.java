package roguelike;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class WallTile extends Tiles{
	private boolean iswalkable;

	public WallTile(int x, int y, AsciiPanel terminal) {
		super(x, y, '#', terminal, Color.WHITE, false);

	}


}

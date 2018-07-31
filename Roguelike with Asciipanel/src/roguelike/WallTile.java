package roguelike;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class WallTile extends Tiles{
	private boolean iswalkable;

	public WallTile(int x, int y) {
		super(x, y, '#', Color.WHITE, false);

	}


}

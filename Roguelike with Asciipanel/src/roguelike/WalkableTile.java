package roguelike;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class WalkableTile extends Tiles{

	public WalkableTile(int x, int y, AsciiPanel terminal) {
		super(x, y, '.', terminal, Color.DARK_GRAY, true);
		// TODO Auto-generated constructor stub
	}

}

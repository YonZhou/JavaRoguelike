package Tiles;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class WalkableTile extends Tiles{

	public WalkableTile(int x, int y) {
		super(x, y, '.', Color.DARK_GRAY, true);
		// TODO Auto-generated constructor stub
	}

	public WalkableTile(int x, int y, char c, Color co) {
		super(x, y, c, co, true);
		// TODO Auto-generated constructor stub
	}
	

}

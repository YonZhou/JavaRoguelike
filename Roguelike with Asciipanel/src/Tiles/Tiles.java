package Tiles;
import java.awt.Color;

import asciiPanel.AsciiPanel;
import roguelike.Entity;


public class Tiles extends Entity{
	private char ID;
	public int x;
	public int y;
	private boolean isWalkable;
	private Color color;
	
	public Tiles(int x, int y, char id, Color color, boolean isWalkable) {
		super(id, x, y, color, isWalkable);
		this.x = x;
		this.y = y;
		this.ID = id;
		this.color = color;
		this.isWalkable = isWalkable;
	}
	
//	public Tiles(int x2, int y2, char id2, AsciiPanel terminal2, Color color2) {
//		super(id2, x2,y2, color2, terminal2);
//	}


	

}

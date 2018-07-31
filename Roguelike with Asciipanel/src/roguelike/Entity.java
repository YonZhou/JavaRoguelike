package roguelike;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Entity {
	public int x;
	public int y;
	private AsciiPanel panel;
	private Color c;
	private char ch;
	private boolean isWalkable;
	
	public Entity(char ch, int x, int y, Color c, AsciiPanel p) {
		this.ch = ch;
		this.x = x;
		this.y = y;
		this.panel = p;
		this.c = c;
		this.isWalkable = true;
	}
	
	public Entity(char ch, int x, int y, Color c, AsciiPanel p, boolean isW) {
		this.ch = ch;
		this.x = x;
		this.y = y;
		this.panel = p;
		this.c = c;
		this.isWalkable = isW;
	}
	
	public void drawEntity() {
		panel.write(this.ch, this.x, this.y, this.c);
	}
	
	public boolean isWalkable() {
		return this.isWalkable;
	}
	
	public int getx() {
		return this.x;
	}
	
	public int gety() {
		return this.y;
	}
	
	public AsciiPanel getPanel() {
		return panel;
	}
	
	public char getID() {
		return this.ch;
	}
	
	public Color getColor() {
		return this.c;
	}

}

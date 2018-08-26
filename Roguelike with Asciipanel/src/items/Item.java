package items;

import java.awt.Color;

import roguelike.Entity;

public class Item extends Entity{
	protected String name;
	public int level;
	public int ID;
	
	public Item(String name, char ch, int x, int y, Color c) {
		super(ch, x, y, c);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getItemID() {
		return this.ID;
	}
	

}

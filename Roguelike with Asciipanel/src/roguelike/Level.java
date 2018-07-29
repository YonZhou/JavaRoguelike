package roguelike;

import java.util.ArrayList;
import java.util.HashMap;

import asciiPanel.AsciiPanel;


public class Level {
	public ArrayList<Tiles> tileList;
	public ArrayList<Enemy> enemyList;
	//public ArrayList<Level> levels; add this in another class
	public Tiles[][] tileMap;
	private String name;
	
	
	public Level(String name, Tiles[][] tilemap, ArrayList<Tiles> tilelist) {
		this.name = name;
		this.tileMap = tilemap;
		this.tileList = tilelist;
		this.enemyList = new ArrayList<Enemy>();
	}
	
	
	
	public void drawLevel(AsciiPanel panel) {
		
		for(Tiles tile : this.tileList) {
			tile.renderTile();
		}
	}

	
}

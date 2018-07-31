package roguelike;

import java.util.ArrayList;
import java.util.HashMap;

import asciiPanel.AsciiPanel;


public class Level {
	public ArrayList<Tiles> tileList;
	public ArrayList<Enemy> enemyList;
	//public ArrayList<Level> levels; add this in another class
	public Entity[][] Map;
	public ArrayList<WalkableTile> walkabletileList;
	private String name;
	private int difficulty;
	public int width;
	public int height;
	
	
	public Level(String name, Entity[][] map, ArrayList<Tiles> tilelist, ArrayList<WalkableTile> walkabletileList, int difficulty, int w, int h) {
		this.name = name;
		this.Map = map;
		this.tileList = tilelist;
		this.walkabletileList = walkabletileList;
		this.enemyList = new ArrayList<Enemy>();
		this.difficulty = difficulty;
		this.width = w;
		this.height = h;
	}
	
	
	
//	public void drawLevel(AsciiPanel panel) {
//		for(Tiles tile : this.tileList) {
//			tile.renderTile();
//		}
//	}
	
	public boolean checkWalkable(int xC, int yC) {
		if(this.Map[xC][yC] == null) {
			return true; //change to true if you want to be able to move on empty space
		}
		else if(this.Map[xC][yC].isWalkable()) {
			return true;
		}
		return false;
	}

	
}

package roguelike;

import java.util.ArrayList;

import asciiPanel.AsciiPanel;


public class Level {
	public ArrayList<Tiles> tileList;
	public ArrayList<Enemy> enemyList;
	public ArrayList<Level> levels;
	private String name;
	
	
	public Level(String name) {
		this.name = name;
		this.tileList = new ArrayList<Tiles>;
		this.enemyList = new ArrayList<Enemy>;
		levels.add(this);
	}
	
	
	
	public void generateWorld(AsciiPanel panel) {
		panel.clear();
		for(Tiles tile : this.tileList) {
			tile.renderTile(panel);
		}
	}

	
}

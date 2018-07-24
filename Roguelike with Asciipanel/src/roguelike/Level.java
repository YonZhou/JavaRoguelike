package roguelike;

import java.util.ArrayList;
import asciiPanel.AsciiPanel;


public class Level {
	public ArrayList<Tiles> unWalkable;
	public ArrayList<Level> levels;
	private String name;
	
	
	public Level(String name) {
		this.name = name;
		this.unWalkable = new ArrayList<Tiles>;
		levels.add(this);
	}
	
	
	
	public void generateWorld(AsciiPanel panel) {
		panel.clear();
		for(Tiles tile : this.unWalkable) {
			tile.Render();
		}
	}

	
}

package roguelike;

import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class LevelGenerator {
	private int height;
	private int width;
	private AsciiPanel panel;

	
	public LevelGenerator(int width, int height, AsciiPanel panel) {
		this.width = width;
		this.height = height;
		this.panel = panel;
	}
	
	public Level generateLevel() {
		int i = 0;
		int j = 0;
		
		Tiles[][] tilemap = new Tiles[width][height];
		ArrayList<Tiles> tileslist = new ArrayList<Tiles>();
		
		while(i < width) {
			tilemap[i][j] = new Tiles(i, j, '#', panel);
			tileslist.add(tilemap[i][j]);
			i++;
		}


		
		return new Level("test", tilemap, tileslist);
	}

}

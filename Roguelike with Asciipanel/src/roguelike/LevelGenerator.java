package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import asciiPanel.AsciiPanel;

public class LevelGenerator {
	private int height;
	private int width;
	private AsciiPanel panel;
	public Entity[][] map;
	private ArrayList<Rectangle> rectangleList;
	public ArrayList<Tiles> tileslist;
	public ArrayList<WalkableTile> walkabletileslist;

	
	public LevelGenerator(int width, int height, AsciiPanel panel) {
		this.width = width;
		this.height = height;
		this.panel = panel;
		this.rectangleList = new ArrayList<Rectangle>();
		this.tileslist = new ArrayList<Tiles>();
		this.map = new Entity[width][height];
		this.walkabletileslist = new ArrayList<WalkableTile>();
		
	}
	
	public Level generateLevel(int dif) {
		int i = 0;
		int j = 0;

		
		generateWalls();


		
		return new Level("test", map, tileslist, walkabletileslist, dif, width, height);
	}
	
	public Rectangle generateRectangle(Entity[][] fullmap, ArrayList<Tiles> tilelist, int topLeftX, int topLeftY, int Width, int Height) {
		int i = topLeftX;
		int j = topLeftY;
		//create the top
		while(i < Width) {
			fullmap[i][j] = new WallTile(i, j, panel);
			tilelist.add((Tiles) fullmap[i][j]);
			i++;
		}
		
		//create the bottom
		j = topLeftY + Height - 1;
		i = topLeftX;
		
		while(i < Width) {
			fullmap[i][j] = new WallTile(i, j, panel);
			tilelist.add((Tiles) fullmap[i][j]);
			i++;
		}
		
		//create the left
		i = topLeftX;
		j = topLeftY;
		
		while(j < topLeftY + Height) {
			fullmap[i][j] = new WallTile(i, j, panel);
			tilelist.add((Tiles) fullmap[i][j]);
			j++;
		}
		
		//create the right
		i = topLeftX + Width - 1;
		j = topLeftY;
		
		while(j < topLeftY + height) {
			fullmap[i][j] = new WallTile(i, j, panel);
			tilelist.add((Tiles) fullmap[i][j]);
			j++;
		}
		
		Rectangle r = new Rectangle(topLeftX, topLeftY, Width, Height);
		rectangleList.add(r);
		return r;
	
	}
	
	public void generateWalls() {
		Rectangle main = generateRectangle(map, tileslist, 0, 0, width, height);
		fillRectangle(main);
	}
	
	public void fillRectangle(Rectangle r) {
		for(int w = r.topLeftX + 1; w < r.width - 1; w++) {
			for(int h = r.topLeftY + 1; h < r.height - 1; h++) {
				map[w][h] = new WalkableTile(w, h, panel);
				tileslist.add((Tiles) map[w][h]);
				walkabletileslist.add((WalkableTile) map[w][h]);
			}
		}
	}
}

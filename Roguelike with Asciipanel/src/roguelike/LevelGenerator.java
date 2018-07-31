package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import asciiPanel.AsciiPanel;

public class LevelGenerator {
	private int height;
	private int width;
	private AsciiPanel panel;
	public Entity[][] map;
	private ArrayList<Rectangle> rectangleList;
	public ArrayList<Tiles> tileslist;
	public ArrayList<WalkableTile> walkabletileslist;

	
	public LevelGenerator(AsciiPanel panel) {
		this.panel = panel;
		this.rectangleList = new ArrayList<Rectangle>();
		this.tileslist = new ArrayList<Tiles>();
		this.walkabletileslist = new ArrayList<WalkableTile>();
		
	}
	
	public Level generateLevel(int dif, int w, int h) {
		this.width = w;
		this.height = h;
		this.map = new Entity[width][height];

		
		generateWalls();


		
		return new Level("test", map, tileslist, walkabletileslist, dif, width, height);
	}
	
	
	public Level generateRWalkLevel(int dif, int w, int h) {
		
		//below 2 lines useless
		this.width = w;
		this.height = h;
		
		
		Entity[][] map = new Entity[width][height];
		
		generateRWalk(map, w, h);
		
		ArrayList<Tiles> tilelist = new ArrayList<Tiles>();
		ArrayList<WalkableTile> walkabletilelist = new ArrayList<WalkableTile>();
		
		parseTileLists(map, tilelist, walkabletilelist);
		
		return new Level("test", map, tilelist, walkabletilelist, dif, width, height);
	}
	
	public void parseTileLists(Entity[][] map, ArrayList<Tiles> tilelist, ArrayList<WalkableTile> walkabletilelist) {
		for(int i = 0; i< map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				tilelist.add((Tiles) map[i][j]);
				if(map[i][j] instanceof WalkableTile)
					walkabletilelist.add((WalkableTile) map[i][j]);
			}
		}
	}
	
	public void generateRWalk(Entity[][] map, int w, int h) {
		Random r = new Random();
		int max = (int) (w*h*0.75);
		int min = (int) (w*h*0.5);
		
		int numCells = r.nextInt(max - min + 1) + min;
		
		fillWithWalls(map, w, h);
		
		
		int refX = r.nextInt(w);
		int refY = r.nextInt(h);
		
		map[refX][refY] = new WalkableTile(refX, refY);
		int i = 1;
		
		while(i < numCells) {
			
			int randomDir = r.nextInt(4) + 1;
			
			int tempX = refX;
			int tempY = refY;
			
			if(randomDir == 1) {
				refY -= 1;
			} else if(randomDir == 2) {
				refX += 1;
			} else if(randomDir == 3) {
				refY += 1;
			} else {
				refX -= 1;
			}
			
			if(checkForBounds(refX, refY, w, h)) {
				refX = tempX;
				refY = tempY;
			};
			
			if(map[refX][refY] instanceof WallTile) {
				map[refX][refY] = new WalkableTile(refX, refY);
				i++;
			}
			
		}
		
		
		
	}
	
	
	public boolean checkForBounds(int refX, int refY, int w, int h) {
		if(refX < 1 || refY < 1 || refX >= w - 1 || refY >= h - 1) {
			return true;
		}
		return false;
	}
	
	
	public void fillWithWalls(Entity[][] map, int w, int h) {
		for(int i=0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				map[i][j] = new WallTile(i, j);
			}
		}
	}
	
	
	public Rectangle generateRectangle(Entity[][] fullmap, ArrayList<Tiles> tilelist, int topLeftX, int topLeftY, int Width, int Height) {
		int i = topLeftX;
		int j = topLeftY;
		//create the top
		while(i < Width) {
			fullmap[i][j] = new WallTile(i, j);
			tilelist.add((Tiles) fullmap[i][j]);
			i++;
		}
		
		//create the bottom
		j = topLeftY + Height - 1;
		i = topLeftX;
		
		while(i < Width) {
			fullmap[i][j] = new WallTile(i, j);
			tilelist.add((Tiles) fullmap[i][j]);
			i++;
		}
		
		//create the left
		i = topLeftX;
		j = topLeftY;
		
		while(j < topLeftY + Height) {
			fullmap[i][j] = new WallTile(i, j);
			tilelist.add((Tiles) fullmap[i][j]);
			j++;
		}
		
		//create the right
		i = topLeftX + Width - 1;
		j = topLeftY;
		
		while(j < topLeftY + height) {
			fullmap[i][j] = new WallTile(i, j);
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
				map[w][h] = new WalkableTile(w, h);
				tileslist.add((Tiles) map[w][h]);
				walkabletileslist.add((WalkableTile) map[w][h]);
			}
		}
	}
}

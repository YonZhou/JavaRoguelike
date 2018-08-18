package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Tiles.ExitTile;
import Tiles.Tiles;
import Tiles.WalkableTile;
import Tiles.WallTile;
import asciiPanel.AsciiPanel;
import items.HealthPotion;
import items.Item;
import items.Sword;

//generate map with tiles -> place creature -> place player

//map is the main map which holds all the tiles. need new 2d for creatures(new layer)

public class LevelGenerator {
	private int height;
	private int width;
	public Entity[][] map;
	public ArrayList<Tiles> tileslist;

	
	public LevelGenerator() {
		//nothing here ???
	}
	
//	public Level generateLevel(int dif, int w, int h) {
//		this.width = w;
//		this.height = h;
//		this.map = new Entity[width][height];
//
//		
//		generateWalls();
//
//
//		
//		return new Level("test", map, tileslist, freetilelist, dif, width, height);
//	}
	public Entity[][] generateCharMap(Entity[][] map) {
		Entity[][] charmap = new Entity[map.length][map[0].length];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				charmap[i][j] = map[i][j];
			}
		}
		return charmap;
	}
	
	
	public Level generateRWalkLevel(int dif, int w, int h) {
		
		//below 2 lines useless
		this.width = w;
		this.height = h;
		
		
		Entity[][] map = new Entity[width][height];
		Creature[][] EnemyMap = new Creature[width][height];
		
		generateRWalk(map, w, h);
		
		ArrayList<Tiles> tilelist = new ArrayList<Tiles>();
		ArrayList<Entity> freetilelist = new ArrayList<Entity>();
		ArrayList<Creature> enemylist = new ArrayList<Creature>();
		
		parseTileLists(map, tilelist, freetilelist);
		
		generateExitTile(map, tilelist, freetilelist);
		
		Entity[][] cMap = generateCharMap(map);
		
		Level generatedLevel = new Level("test", map, tilelist, freetilelist, cMap, dif, width, height);
		generatedLevel.enemyMap = EnemyMap;
		generatedLevel.enemyList = enemylist;
		generatedLevel.itemList = new ArrayList<Item>();
		generatedLevel.itemMap = new Item[width][height];
		
		generateCreatures(generatedLevel);
		generateItems(generatedLevel);
		
		
		return generatedLevel;
	}
	
	
	public void generateCreatures(Level level) {
		int free = level.FreeTileList.size();
		
		Random r = new Random();
		
		int max = (int) (Math.sqrt(free)*0.5);
		int min = (int) (Math.sqrt(free)*0.25);
		
		
		int numToGenerate = r.nextInt(max - min + 1) + min;
	
		//use for testing 
//		int numToGenerate = 5; 
		
		for(int i=0; i < numToGenerate; i++) {
			Zombie z = new Zombie("Zombie", 100, 1, 1);
			z.setLevel(level);
			z.addAtEmptyLocation(); //already deletes overwritten tile from list
			level.enemyMap[z.getx()][z.gety()] = z; //when creature moves, need to update map but not update list
			level.enemyList.add(z);
		}
		
	}
	
	//add all entries of map to tilelist and walkabletilelist
	public void parseTileLists(Entity[][] map, ArrayList<Tiles> tilelist, ArrayList<Entity> freetilelist) {
		for(int i = 0; i< map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				tilelist.add((Tiles) map[i][j]);
				if(map[i][j] instanceof WalkableTile)
					freetilelist.add((WalkableTile) map[i][j]);
			}
		}
	}
	
	public void generateRWalk(Entity[][] map, int w, int h) {
		Random r = new Random();
		int max = (int) (w*h*0.5); //maximum empty blocks
		int min = (int) (w*h*0.5);
		
		int numCells = r.nextInt(max - min + 1) + min;
		
		fillWithWalls(map, w, h);
		
		//TODO: make refX and refY need to be away from the walls and random
		int refX = w / 2;
		int refY = h / 2;
		
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
	
	public void generateEMPTY() {
		Rectangle main = generateRectangle(map, tileslist, 0, 0, width, height);
		fillRectangle(main);
	}
	
	//put this function after the parse, as it needs to find an empty walkabletile on the TILELIST.
	public void generateExitTile(Entity[][] map, ArrayList<Tiles> tilelist, ArrayList<Entity> freetilelist) {
		int max = freetilelist.size();
		
		Random r = new Random();
		
		int randomIndex = r.nextInt(max); //index of random freetile to write exit to
		
		WalkableTile wTile = (WalkableTile) freetilelist.get(randomIndex);
		
		int x = wTile.getx();
		int y = wTile.gety();
		
		ExitTile exit = new ExitTile(x, y);
		
		map[x][y] = exit;
		
		freetilelist.remove(randomIndex);
		tilelist.set(randomIndex, exit);
	}
	
	public void fillRectangle(Rectangle r) {
		for(int w = r.topLeftX + 1; w < r.width - 1; w++) {
			for(int h = r.topLeftY + 1; h < r.height - 1; h++) {
				map[w][h] = new WalkableTile(w, h);
				tileslist.add((Tiles) map[w][h]);
//				walkabletileslist.add((WalkableTile) map[w][h]);
			}
		}
	}
	
	public void generateItems(Level level) {
		Random r = new Random();
		
		int numToGenerate = r.nextInt((int) level.FreeTileList.size() / 200 - (int) level.FreeTileList.size() / 300  + 1) + (int) level.FreeTileList.size() / 300;
		
		
		for(int i=0; i< numToGenerate; i++) {
			int max = level.FreeTileList.size();
			
			
			int randomIndex = r.nextInt(max); //index of random freetile to write exit to
			
			WalkableTile wTile = (WalkableTile) level.FreeTileList.get(randomIndex);
			
			int x = wTile.getx();
			int y = wTile.gety();
			
			if(r.nextInt(5) > 0) {
			
				int swordLevel = r.nextInt(5) + 1;
				
				Sword sword = new Sword(x, y, swordLevel);
				
				level.itemList.add(sword);
				level.itemMap[x][y] = sword;
				
			} else {
				int potionLevel = r.nextInt(5) + 1;
				
				HealthPotion potion = new HealthPotion(x, y, potionLevel);
				
				level.itemList.add(potion);
				level.itemMap[x][y] = potion;
			}
			
			level.FreeTileList.remove(randomIndex);
			
		}
	}
	
	public void smooth() {
		
	}
	
}

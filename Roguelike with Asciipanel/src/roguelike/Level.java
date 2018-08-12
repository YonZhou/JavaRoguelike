package roguelike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Tiles.ExitTile;
import Tiles.Tiles;
import asciiPanel.AsciiPanel;
import items.Item;


public class Level {
	public ArrayList<Tiles> tileList;
	public ArrayList<Creature> enemyList;
	public Creature[][] enemyMap;
	public ArrayList<Item> itemList;
	public Item[][] itemMap;
	//public ArrayList<Level> levels; add this in another class
	public Entity[][] Map;
	public ArrayList<Entity> FreeTileList;
	private String name;
	private int difficulty;
	public int width;
	public int height;
	public Player player;
	public PathFindingAI ai;
	
	public Entity[][] charMap;
	
	public Integer[][] aiMap;
	
	
	public Level(String name, Entity[][] map, ArrayList<Tiles> tilelist, ArrayList<Entity> freetileList, Entity[][] charmap, int difficulty, int w, int h) {
		this.name = name;
		this.Map = map;
		this.tileList = tilelist;
		this.FreeTileList = freetileList;
		this.charMap = charmap;
		this.difficulty = difficulty;
		this.width = w;
		this.height = h;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
//	public void drawLevel(AsciiPanel panel) {
//		for(Tiles tile : this.tileList) {
//			tile.renderTile();
//		}
//	}
	
	
	//THIS FUNCTINO DOES NOT RELY ON INSTANCE OF WALKABLETILE
	public boolean checkWalkable(int xC, int yC) {
		if(this.Map[xC][yC] == null) {
			return true; //change to true if you want to be able to move on empty space
		}
		else if(this.Map[xC][yC].isWalkable()) {
			return true;
		}
		return false;
	}
	
	public boolean checkExit(int xC, int yC) {
		if(this.Map[xC][yC] instanceof ExitTile) {
			return true;
		}
		return false;
	}
	
	public void updateEnemyMap() {
		for(Creature c : enemyList) {
			enemyMap[c.getx()][c.gety()] = c;
		}
	}
	
	//WARNING!!!! THIS METHOD WILL CLEAR BASED ON COORDINATES IN LIST, NOT ENTIRE MAP
	public void clearEnemyMap() {
		for(Creature c : enemyList) {
			enemyMap[c.getx()][c.gety()] = null;
		}
	}
	
	public void clearEnemyMapPosition(int x, int y) {
		enemyMap[x][y] = null;
	}
	
	//this function also updates the enemymap
	public void moveAllCreatures() {
		
		player.ai.clearToAvoid();
		
		//first set the entire ai to 
//		for(int i=0; i<player.aggroWidth; i++) {
//			for(int j=0; j<player.aggroWidth; i++) {
//				if(enemyMap[i + player.ai.topLeftX][j + player.ai.topLeftY] != null) {
//					player.ai.toAvoid[i][j] = true;
//				}
//			}
//		}
		
		for(Creature c : enemyList) {
			if(c.inRange()) {
				player.ai.toAvoid[c.getx() - player.ai.topLeftX][c.gety() - player.ai.topLeftY] = true;
			}
		}
		
		for(Creature c : enemyList) {
			clearEnemyMapPosition(c.getx(), c.gety());
			

			
			if(c.inRange()) {
				Random r = new Random();
				int moveif = r.nextInt(c.aggroFactor);
				if(moveif > 0) {
					player.ai.toAvoid[c.getx() - player.ai.topLeftX][c.gety() - player.ai.topLeftY] = false;
					player.ai.updateMap();
					
					//move the creature after generating new map with ONLY it being "empty"
					c.moveOnMap(); //coordinates are updated
					
					player.ai.toAvoid[c.getx() - player.ai.topLeftX][c.gety() - player.ai.topLeftY] = true;
				} else {
					player.ai.toAvoid[c.getx() - player.ai.topLeftX][c.gety() - player.ai.topLeftY] = false;
					c.moveRandom();
					player.ai.toAvoid[c.getx() - player.ai.topLeftX][c.gety() - player.ai.topLeftY] = true;

				}
				//player.ai.updateMap();
			} else {
				c.moveRandom();
			}

			enemyMap[c.getx()][c.gety()] = c;
		}
	}
	
	public void deleteItem(int x, int y) {
		Item itemtodelete = itemMap[x][y];
		
		itemList.remove(itemtodelete);
		itemMap[x][y] = null;
	}
	
	
	public void addAiMap(Integer[][] aimap) {
		this.aiMap = aimap;
	}

	
}

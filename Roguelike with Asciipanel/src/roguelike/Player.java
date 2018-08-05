package roguelike;

import java.awt.Color;
import java.util.Random;


public class Player extends Creature{

	private Creature engagedCreature;
	public int aggroWidth;
	public int aggroHeight;
	public PathFindingAI ai;
	public boolean isAlive = true;

	public Player(int health, int level, int x, int y) {
		super("Player", health, level, '@', x, y, Color.WHITE);
		this.aggroWidth = 99;
		this.aggroHeight = 99;
		this.ai = new PathFindingAI(this);
	}
	
	public void setWorld(World w) {
		this.world = w;
	}
	
	@Override
	public void addAtEmptyLocation() {
		int max = this.l.FreeTileList.size();
		
		Random r = new Random();
		
		int randomIndex = r.nextInt(max);
		this.x = l.FreeTileList.get(randomIndex).getx();
		this.y = l.FreeTileList.get(randomIndex).gety();
		
		//check if on exit tile - deprecated due to new list just "writable"
		
//		while(l.FreeTileList.get(randomIndex) instanceof ExitTile) {
//			this.x = l.FreeTileList.get(randomIndex).getx();
//			this.y = l.FreeTileList.get(randomIndex).gety();
//			randomIndex = r.nextInt(max);
//		}
	}
	
	public void setLevel(Level l) {
		this.l = l;
		this.camera.setLevel(l);
		addAtEmptyLocation();
	}
	@Override
	public void moveCreature(int dx, int dy) {
		
		if(checkForExit(x + dx, y + dy)) {
			Level nextL = world.nextLevel(); 
			setLevel(nextL);
			nextL.setPlayer(this);
			this.camera.renderCamera();
		}
		
		if(checkForCollision(x+dx, y+dy)) {
			if(checkForFight()) {
				System.out.println("contact");
				
				l.clearEnemyMap();
				
				attack(engagedCreature);
				
				l.updateEnemyMap();
			}
		} else {
			this.x += dx;
			this.y += dy;
		}
		
		
		
		ai.updateMap();
		
		l.moveAllCreatures();
		
		checkDeath();
		
	}
	
	public void checkDeath() {
		if(this.health <= 0) {
			this.isAlive = false;
			System.out.println(this.isAlive);
			die();
		}
	}
	
	@Override
	public boolean checkForCollision(int xC, int yC) {
		if(!(l.checkWalkable(xC, yC)) || l.enemyMap[xC][yC] != null)
			return true;
		return false;
	}
	
	public boolean checkForExit(int xC, int yC) {
		if(l.checkExit(xC, yC)) {
			return true;
		}
		return false;
	}
	
	public boolean checkForFight() {
		if(l.enemyMap[this.getx()][this.gety()] != null) {
			this.engagedCreature = l.enemyMap[this.getx()][this.gety()];
			return true;
		}
		return false;
	}
	
	public void attack(Creature other) {
		other.health -= 50;
		if(other.health <= 0) {
			other.die();
		}
	}
	
	@Override
	public void die() {
		resetStats();
		this.world.reset();
	}
	
	public void resetStats() {
		this.health = 100;
		this.isAlive = true;
	}
	

}

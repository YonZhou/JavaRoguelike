package roguelike;
import java.awt.Color;
import java.util.Random;

import asciiPanel.AsciiPanel;

public class Creature extends Entity{
	public int health;
	public int level;
	private String name;
	public char icon;
	private Color c;
	public Level l;
	public Camera camera;
	public World world;
	// add map
	
	public Creature(String string, int health, int level, char icon, int x, int y, Color c) {
		super(icon, x, y, c);
		this.name = string;
		this.health = health;
		this.level = level;
		this.icon = icon;
		this.c = c;
	}
	
	
	
//	public Camera createCamera(AsciiPanel p) {
//		camera = new Camera(this, this.x, this.y, this.l, p);
//		return camera;
//	}
	
	public void setCamera(Camera c) {
		this.camera = c;
	}
	
	public void setLevel(Level l) {
		this.l = l;
	}

	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void moveCreature(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		
		
		if(checkForCollision()) {
			if((this.getx() == l.player.getx( ) && this.gety() == l.player.gety())) {
				attackPlayer();
			}
			this.x -= dx;
			this.y -= dy;
		}
	}

//	public void drawCreature() {
//		super.drawEntity();
//		//panel.write(this.icon, this.x, this.y);
//	}
	
//	public void clearCreature() {
//		getPanel().write(' ', this.x, this.y);
//	}
	
//	public void moveAndDrawCreature(int dx, int dy) {
//		this.clearCreature();
//		this.moveCreature(dx, dy);
//		this.drawCreature();
//	}
	
	//public void update() {
	//	this.drawCreature();
	//}

	
	//function below is deprecated due to the coordinates being relative to the world instead of the panel, either update or destroy
//	public void checkForBounds() {
//		if(this.x > getPanel().getWidthInCharacters() - 1) {
//			this.x = getPanel().getWidthInCharacters() - 1;
//		} else if(this.x < 0) {
//			this.x = 0;
//		} else if(this.y < 0) {
//			this.y = 0;
//		} else if(this.y > getPanel().getHeightInCharacters() - 1) {
//			this.y = getPanel().getHeightInCharacters() - 1;
//		}
//	}
	
	public void attackPlayer() {
		this.l.player.health -= 10;
	}
	

	public boolean checkForCollision() {
		if(!(l.checkWalkable(this.getx(), this.gety())) || l.enemyMap[this.getx()][this.gety()] != null || (this.getx() == l.player.getx( ) && this.gety() == l.player.gety())) {
//			if(l.enemyMap[this.getx()][this.gety()] != null) {
//				
//			}
				
			return true;
		}
		return false;
	}
	
	//function already removes the free tile from the list after added
	public void addAtEmptyLocation() {
		Random r = new Random();
		int max = this.l.FreeTileList.size();
		
		int randomIndex = r.nextInt(max);
		this.x = l.FreeTileList.get(randomIndex).getx();
		this.y = l.FreeTileList.get(randomIndex).gety();
		
		l.FreeTileList.remove(randomIndex);
	}
	
	//add death function, where remove from enemy list, and then have the update creaturemap function first clear map based on list, then rewrite
	public void die() {
		this.l.enemyList.remove(this);
	}
	
	public void moveRandom() {
		Random r = new Random();
		int randomDir = r.nextInt(4) + 1;
		
		if(randomDir == 1) {
			moveCreature(0,-1);
		} else if(randomDir == 2) {
			moveCreature(1,0);
		} else if(randomDir == 3) {
			moveCreature(0,1);
		} else {
			moveCreature(-1, 0);
		}
	}
	
	public void moveCreature2(int x, int y) {
		
	}
	
	public void updateMove() {
		
		if(inRange()) {
			Random r = new Random();
			int i = r.nextInt(6);
			if(i > 0)
				moveOnMap();
			else
				moveRandom();
			
		} else {
			moveRandom();
		}
	}
	
	public boolean inRange() {
		if(this.getx() > l.player.ai.topLeftX && this.getx() < l.player.ai.topLeftX + l.player.aggroWidth - 1 && this.gety() > l.player.ai.topLeftY && this.gety() < l.player.ai.topLeftY + l.player.aggroHeight - 1) {
			return true;
		}
		return false;
	}
	
	public void moveOnMap() {
		if(l.player.ai.map[this.getx() - l.player.ai.topLeftX][this.gety() - l.player.ai.topLeftY] == 1) {
			moveCreature(0, -1);
		} else if(l.player.ai.map[this.getx() - l.player.ai.topLeftX][this.gety() - l.player.ai.topLeftY] == 2) {
			moveCreature(1, 0);
		} else if(l.player.ai.map[this.getx() - l.player.ai.topLeftX][this.gety() - l.player.ai.topLeftY] == 3) {
			moveCreature(0, 1);
		} else {
			moveCreature(-1, 0);
		}
	}

	

}


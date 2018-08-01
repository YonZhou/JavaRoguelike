package roguelike;

import java.awt.Color;
import java.util.Random;


public class Player extends Creature{
	public World w;

	public Player(int health, int level, int x, int y) {
		super("Player", health, level, '@', x, y, Color.WHITE);

	}
	
	public void setWorld(World w) {
		this.w = w;
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
	
	@Override
	public void moveCreature(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		
		
		if(checkForCollision()) {
			this.x -= dx;
			this.y -= dy;
		}
		
		if(checkForExit()) {
			setLevel(w.nextLevel());
			this.camera.renderCamera();
			addAtEmptyLocation();
		}
		
		
	}
	
	@Override
	public boolean checkForCollision() {
		if(l.checkWalkable(this.getx(), this.gety()))
			return false;
		return true;
	}
	
	public boolean checkForExit() {
		if(l.checkExit(this.getx(), this.gety())) {
			return true;
		}
		return false;
	}

}

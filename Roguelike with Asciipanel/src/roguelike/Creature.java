package roguelike;
import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Creature extends Entity{
	public int health;
	public int level;
	private String name;
	public char icon;
	private AsciiPanel panel;
	private Color c;
	public Level l;
	public Camera camera;
	// add map
	
	public Creature(String string, int health, int level, char icon, int x, int y, AsciiPanel panel, Level l, Color c) {
		super(icon, x, y, c);
		this.name = string;
		this.health = health;
		this.level = level;
		this.icon = icon;
		this.panel = panel;
		this.l = l;
		this.c = c;
	}
	
	public Camera createCamera() {
		camera = new Camera(this, this.x, this.y, this.l, this.panel);
		return camera;
	}
	
	public void setCamera() {
		this.camera = createCamera();
	}
	
	public void setLevel(Level l) {
		this.l = l;
		this.camera.setLevel(l);
	}

	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void moveCreature(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		
		
		if(checkForCollision()) {
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
	

	public boolean checkForCollision() {
		if(l.checkWalkable(this.getx(), this.gety()))
			return false;
		return true;
	}


	

}


package roguelike;
import asciiPanel.AsciiPanel;

public class Creature extends Entity{
	public int health;
	public int level;
	private String name;
	public char icon;
	private AsciiPanel panel;
	public Level l;
	// add map
	
	public Creature(String string, int health, int level, char icon, int x, int y, AsciiPanel panel, Level l) {
		super(x, y, panel);
		this.name = string;
		this.health = health;
		this.level = level;
		this.icon = icon;
		this.panel = panel;
		this.l = l;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void moveCreature(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		
		this.checkAll();
		
		if(checkForCollision()) {
			this.x -= dx;
			this.y -= dy;
		}
	}

	public void drawCreature() {
		panel.write(this.icon, this.x, this.y);
	}
	
	public void clearCreature() {
		panel.write(' ', this.x, this.y);
	}
	
	public void moveAndDrawCreature(int dx, int dy) {
		this.clearCreature();
		this.moveCreature(dx, dy);
		this.drawCreature();
	}
	
	//public void update() {
	//	this.drawCreature();
	//}
	
	public void checkForBounds() {
		if(this.x > panel.getWidthInCharacters() - 1) {
			this.x = panel.getWidthInCharacters() - 1;
		} else if(this.x < 0) {
			this.x = 0;
		} else if(this.y < 0) {
			this.y = 0;
		} else if(this.y > panel.getHeightInCharacters() - 1) {
			this.y = panel.getHeightInCharacters() - 1;
		}
	}
	
	public void checkAll() {
		checkForBounds();
	}
	
	public boolean checkForCollision() {
		if(l.tileMap[this.x][this.y] == null)
			return false;
		return true;
	}
	

}


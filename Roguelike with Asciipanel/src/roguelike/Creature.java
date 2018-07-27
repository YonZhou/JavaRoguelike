package roguelike;
import asciiPanel.AsciiPanel;

public class Creature extends Entity{
	public int health;
	public int level;
	private String name;
	public char icon;
	private AsciiPanel panel;
	
	public Creature(String string, int health, int level, char icon, int x, int y, AsciiPanel panel) {
		super(x, y);
		this.name = string;
		this.health = health;
		this.level = level;
		this.icon = icon;
		this.panel = panel;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void moveCreature(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public void drawCreature() {
		panel.write(this.icon, this.x, this.y);
	}
	
	public void clearCreature() {
		panel.clear();
	}
	
	public void update() {
		this.drawCreature();
	}

}


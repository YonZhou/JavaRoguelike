package roguelike;
import asciiPanel.AsciiPanel;

public class Creature {
	public int x;
	public int y;
	public int health;
	public int level;
	private String name;
	public char icon;
	
	public Creature(String string, int health, int level, char icon, int x, int y) {
		this.name = string;
		this.health = health;
		this.level = level;
		this.icon = icon;
		this.x = x;
		this.y = y;
	}
	
	public Creature(String string) {
		this.name = string;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}


	public void drawCreature(AsciiPanel terminal) {
		terminal.write(this.icon, this.x, this.y);
	}

}


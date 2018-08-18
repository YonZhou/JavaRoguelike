package items;

import java.awt.Color;

public class Gun extends Weapon{
	
	private int range;
	public int ammoUsage;

	public Gun(String name, int x, int y, int level) {
		super(name, '/', x, y, Color.YELLOW);
		super.level = level;
		super.setAttack(level*level);
		this.range = level*2;
	}
	
	public int getRange() {
		return this.range;
	}
	
	

}

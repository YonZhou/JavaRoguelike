package items;

import java.awt.Color;

public class Gun extends Weapon{
	
	private int range;
	public int ammoUsage;
	public int ammoType;

	public Gun(String name, int x, int y, int level, int ammoType) {
		super(name, '/', x, y, Color.YELLOW);
		super.level = level;
		super.setAttack(10*level);
		this.range = level*level + 50;
		this.ammoType = ammoType;
	}
	
	public Gun(String name, int x, int y, int level) {
		super(name, '/', x, y, Color.YELLOW);
		super.level = level;
		super.setAttack(level*level);
		this.range = level*2;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public void setAmmoType(int i) {
		this.ammoType = i;
	}
	
	
	

}

package items;

import java.awt.Color;

public class Sword extends Weapon{
	
	
	public Sword(int x, int y, int level) {
		super("Sword", '/', x, y, Color.YELLOW);
		super.level = level;
		super.setAttack(level*level);
	}
	
	public Sword() {
		super("Sword", '/', 0, 0, Color.YELLOW);
		super.level = 10;
		super.setAttack(super.level*super.level);
	}
	

}

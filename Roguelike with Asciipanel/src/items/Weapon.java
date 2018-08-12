package items;

import java.awt.Color;

public class Weapon extends Item{
	private int attack;

	public Weapon(String name, char ch, int x, int y, Color c) {
		super(name, ch, x, y, c);
	}
	
	public int getAttack() {
		return this.attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}

}

package items;

import java.awt.Color;

public class HealthPotion extends Potion{
	public int healingFactor;

	public HealthPotion(int x, int y, int level) {
		super("Health Potion", x, y, Color.YELLOW, level);
		
		this.healingFactor = this.level * 3;
	}
	
	public int getHealingFactor() {
		return this.healingFactor;
	}

}

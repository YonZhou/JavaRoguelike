package items;

import java.awt.Color;

public class Potion extends Item{

	public Potion(String name, int x, int y, Color c, int level) {
		super(name, '8', x, y, c);
		this.level = level;
	}

}

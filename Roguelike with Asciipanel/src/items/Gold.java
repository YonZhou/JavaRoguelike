package items;

import java.awt.Color;

public class Gold extends Stackable{

	public Gold(int x, int y, int maxC, int C) {
		super("Gold", '.', x, y, Color.YELLOW);
		this.Capacity = C;
		this.MaxCapacity = maxC;
		super.setItemID(0);
	}

}

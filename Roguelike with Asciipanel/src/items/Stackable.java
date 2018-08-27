package items;

import java.awt.Color;

public abstract class Stackable extends Item{
	public int MaxCapacity;
	public int Capacity;
	
	
	public Stackable(String name, char ch, int x, int y, Color c) {
		super(name, ch, x, y, c);
		// TODO Auto-generated constructor stub
	}
	
	public void decrementCapacity(int i) {
		this.Capacity -= i;
		if(this.Capacity <= 0)
			this.Capacity = 0;
	}
	
	
	public void incrementCapacity(int i) {
		this.Capacity += i;
		if(this.Capacity > this.MaxCapacity)
			this.Capacity = this.MaxCapacity;
	}
	
	public boolean isEmpty() {
		if(this.Capacity <= 0)
			return true;
		return false;
	}
	
	public int getCapacity() {
		return this.Capacity;
	}
//	public void incrementCapacity(int i);
//	public void decrementCapacity(int i);
//	public boolean isEmpty();
//	public int getCapacity();
	
	
}

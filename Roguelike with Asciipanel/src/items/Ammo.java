package items;

import java.awt.Color;

public class Ammo extends Stackable{
//	public int MaxCapacity;
//	public int Capacity;
//	
	public Ammo(String name, int x, int y, int capacity, int ID) {
		super(name, '|', x, y, Color.GREEN);
		this.Capacity = capacity;
		this.MaxCapacity = ID * 100;
		this.ID= ID;
	}
	
//	public void decrementCapacity(int i) {
//		this.Capacity -= i;
//		if(this.Capacity <= 0)
//			this.Capacity = 0;
//	}
//	
//	
//	public void incrementCapacity(int i) {
//		this.Capacity += i;
//		if(this.Capacity > this.MaxCapacity)
//			this.Capacity = this.MaxCapacity;
//	}
//	
//	public boolean isEmpty() {
//		if(this.Capacity <= 0)
//			return true;
//		return false;
//	}
//	
//	public int getCapacity() {
//		return this.Capacity;
//	}



}

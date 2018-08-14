package roguelike;

import java.util.ArrayList;

import items.Item;
import items.Weapon;

public class Inventory {
	public Player player;
	public ArrayList<Item> itemList;
	public int maxInvSize;
	
	
	public Inventory() {
		this.itemList = new ArrayList<Item>();
		this.maxInvSize = 3;
	}
	
	public void clear() {
		this.itemList.clear();
	}
	

}

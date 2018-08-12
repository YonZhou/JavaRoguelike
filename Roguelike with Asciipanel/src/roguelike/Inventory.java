package roguelike;

import java.util.ArrayList;

import items.Item;
import items.Weapon;

public class Inventory {
	public Player player;
	public ArrayList<Item> itemList;
	
	
	public Inventory() {
		this.itemList = new ArrayList<Item>();
	}
	
	public void clear() {
		this.itemList.clear();
	}
	

}

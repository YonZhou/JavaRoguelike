package roguelike;

import java.util.ArrayList;

import items.Item;
import items.Stackable;
import items.Weapon;

public class Inventory {
	public Player player;
	public ArrayList<Item> itemList;
	public int maxInvSize;
	
	
	public Inventory(int i) {
		this.itemList = new ArrayList<Item>();
		this.maxInvSize = i;
	}
	
	public void clear() {
		this.itemList.clear();
	}
	
//	public boolean containsClass(Class c) {
//		for(int i=0; i<itemList.size(); i++) {
//			if(itemList.get(i) instanceof c.class) {
//				
//			}
//		}
//	}
	
	public int containsID(int i) {
		
		for(int j=0; j < itemList.size(); j++) {
			if(itemList.get(j) instanceof Stackable && i == itemList.get(j).getItemID()) {
				return j;
			}
		}
		
		return -1;
	}
	

}

package Tiles;

import java.util.ArrayList;

import items.Item;

public class ItemTile {
	private ArrayList<Item> Items;
	public int x;
	public int y;
	
	public ItemTile(int x, int y) {
		this.Items = new ArrayList<Item>();
		this.x = x;
		this.y = y;
	}
	
	public Item get(int i) {
		return Items.get(i);
	}
	
	public void add(Item i) {
		this.Items.add(i);
	}
	
	public Item retrieveItem() {
		Item i = get(0);
		Items.remove(0);
		return i;
	}
	
	public boolean isEmpty() {
		if(Items.size() <= 0)
			return true;
		else
			return false;
	}
	
	public void removeItem(int i) {
		Items.remove(i);
	}
	
	public Item retrieveItem(int i) {
		Item item = get(i);
		Items.remove(i);
		
		return item;
	}

}

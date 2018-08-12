package menus;

import java.util.ArrayList;

import items.*;
import roguelike.World;

public class InventoryScreen extends MenuScreen{
	public int width;
	public int height;
	public ArrayList<Item> itemList;
	public World w;
	
	public InventoryScreen(World w) {
		this.w = w;
		this.itemList = w.p.inv.itemList;
	}
	
	
	public void updateInventoryScreen() {
		
	}

	@Override
	public void displayScreen() {
		
		
	}

	@Override
	public int checkOption() {
		// TODO Auto-generated method stub
		return 0;
	}

}

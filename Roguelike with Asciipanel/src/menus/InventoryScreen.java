package menus;

import java.awt.Color;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;
import items.*;
import roguelike.Creature;
import roguelike.PanelText;
import roguelike.Player;
import roguelike.World;

public class InventoryScreen extends MenuScreen{
	public int topLeftX;
	public int topLeftY;
	public int width;
	public int height;
	public ArrayList<Item> itemList;
	public World w;
	private AsciiPanel panel;
	public Player p;
	
	public InventoryScreen(World w) {
		this.w = w;
		this.p = w.p;
		this.itemList = p.inv.itemList;
		this.panel = w.app.getTerminal();	
		
		this.textList = new ArrayList<PanelText>();
		
		this.topLeftX = (int) 3 * w.app.TERMINAL_WIDTH / 4;
		this.topLeftY = (int) w.gui.topLeftY;
		
		this.width = (int) w.app.TERMINAL_WIDTH / 4;
		this.height = p.camera.height - 1;
		
	}
	
	
	public void updateInventoryScreen() {
		
		for(int i=0; i< textList.size(); i++) {
			panel.write(textList.get(i).text, topLeftX + 1, topLeftY + 1 + i, textList.get(i).getColor());
		}
		
	}

	@Override
	public void displayScreen() {
		clearSpace();
		displayBorders();
		updateInventoryScreen();
		w.app.repaint();
	}
	
	public void clearSpace() {
		panel.clear(' ', topLeftX + 1, topLeftY + 1, width - 2, height - 2);
	}
	
	public void displayBorders() {
		for(int i=topLeftY; i < topLeftY + height; i++) {
			panel.write('|', topLeftX, i);
		}
		
		for(int i=topLeftY; i< topLeftY + height; i++) {
			panel.write('|', topLeftX + width - 1, i);
		}
		
		for(int i=topLeftX; i< topLeftX + width; i++) {
			panel.write('-', i, topLeftY);
		}
		
		for(int i=topLeftX; i< topLeftX + width; i++) {
			panel.write('-', i, topLeftY + height - 1);
		}
	}
	
	//useless function??? need to convert the items in item list to text, so many just update it everytime inventory opened or managed?
	
	public void updateTextList() {
		for(int i=0; i< itemList.size(); i++) {
			Item item = itemList.get(i);
			if(item instanceof Weapon)
				super.textList.add(new PanelText(item.getName() + " + " + item.level, Color.RED));
			if(item instanceof HealthPotion)
				super.textList.add(new PanelText(item.getName() + " + " + item.level, Color.GREEN));
			if(item instanceof Stackable) {
				super.textList.add(new PanelText(item.getName() + " x" + ((Stackable)item).getCapacity(), Color.YELLOW));

			}
		}
	}
	
	public void clearTextList() {
		textList.clear();
	}

	@Override
	public int checkOption() {
		if(itemList.get(textIndex) != null) {
			Item item = itemList.get(textIndex);
			
			if(item instanceof Weapon) {
				if(p.inShootingMode()) {
					w.gui.addToActionPanel(new PanelText("Cannot switch weapons while in shooting mode!"));
				} else {
					p.equippedWeapon = (Weapon) item;
					w.gui.addToActionPanel(new PanelText("You equipped the "+ textList.get(textIndex).text, Color.PINK));
				}
			} else if (item instanceof HealthPotion) {
				p.use(item);
			}
			
		}
		
		w.gui.refresh();
		displayScreen();
		return 0;
	}
	
	@Override
	public void clearColors() {
		for(int i=0; i<textList.size(); i++) {
			if(itemList.get(i) instanceof Weapon)
				textList.get(i).setColor(Color.RED);
			else if(itemList.get(i) instanceof HealthPotion)
				textList.get(i).setColor(Color.GREEN);
			else if(itemList.get(i) instanceof Stackable)
				textList.get(i).setColor(Color.YELLOW);
		}
	}
	
	
	public void dropItem() {
		
		if(textList.size() > 0) {
			
			Item i = itemList.get(textIndex);
			
			if(i == p.equippedWeapon) {
				if(p.inShootingMode()) {
					w.gui.addToActionPanel(new PanelText("Can't unequip gun while in shooting mode!"));
				} else {
					p.equippedWeapon = new Weapon("Fists", '3', 0, 0, Color.BLACK);
					w.gui.addToActionPanel(new PanelText("You dropped the " + textList.get(textIndex).text, Color.PINK));
					
					
					textList.remove(textIndex);
					itemList.remove(textIndex);
					p.dropItemToMap(i);
				}
			} else {
				w.gui.addToActionPanel(new PanelText("You dropped the " + textList.get(textIndex).text, Color.PINK));
				textList.remove(textIndex);
				itemList.remove(textIndex);
				p.dropItemToMap(i);
			}
			
			
			
			
			
			if(textIndex >= itemList.size()) {
				textIndex = itemList.size() - 1;
			}
			
			//need to check again after removing the item
			if(textList.size() > 0)
				textList.get(textIndex).setColor(Color.WHITE);
			
		}
		w.gui.refresh();
		displayScreen();
	
	}
	

	
	public void useItem() {
		w.gui.addToActionPanel(new PanelText("You used the " + textList.get(textIndex).text, Color.PINK));
		
		textList.remove(textIndex);
		itemList.remove(textIndex);
		
		if(textIndex >= itemList.size()) {
			textIndex = itemList.size() - 1;
		}
		
		if(textList.size() > 0)
			textList.get(textIndex).setColor(Color.WHITE);
		displayScreen();
		
	}
	public void openInvScreen() {
		clearTextList();
		updateTextList();
		textIndex = 0;
		if(textList.size() > 0 && textList.get(0) != null)
			textList.get(0).setColor(Color.WHITE);
		displayScreen();
	}

}

package menus;

import java.awt.Color;
import java.util.ArrayList;

import roguelike.PanelText;

public abstract class MenuScreen {
	public ArrayList<PanelText> textList;
	public int textIndex = 0;
	
	public abstract void displayScreen();
	public abstract int checkOption();
	
	public void clearColors() {
		for(int i=0; i<textList.size(); i++) {
			textList.get(textIndex).setColor(Color.GRAY);
		}
	}
	
	public void moveDown() {
		if(textList.size() > 0) {
			clearColors();
			textIndex++;
			textIndex = textIndex % (textList.size());
			textList.get(textIndex).setColor(Color.WHITE);
			displayScreen();
		}
	}
	
	public void moveUp() {
		if(textList.size() > 0) {
			clearColors();
			textIndex--;
			if(textIndex < 0) {
				textIndex = textList.size() - 1;
			}
			textList.get(textIndex).setColor(Color.WHITE);
			displayScreen();
		}
	}

}

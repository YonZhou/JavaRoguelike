package roguelike;

import java.util.ArrayList;
import java.util.LinkedList;

import asciiPanel.AsciiPanel;

public class MainGUI extends AsciiPanel{
	public final int ACTIONPANEL_MAX_SIZE = 5;
	
	private int topLeftX = 0;
	private int topLeftY = 0;
	private AsciiPanel panel;
	private Player player;
	private int width;
	private int height;
	public LinkedList<PanelText> actionPanel;
	
	public MainGUI(AsciiPanel a, Player p, int width, int height) {
		this.panel = a;
		this.player = p;
		this.width = width;
		this.height = height;
		this.actionPanel = new LinkedList<PanelText>();
	}
	
	public void drawLine() {
		for(int i=0; i< this.width; i++) {
			panel.write('-', i, topLeftY + height - 1);
		}
	}
	
	public void displayPlayerHealth() {
		panel.write("Player Health: " + Integer.toString(player.health), topLeftX, topLeftY);
	}
	
	public void displayLevelCount() {
		panel.write("Current depth: " + player.world.levelList.size(), topLeftX, topLeftY + 1);
	}
	
	public void addToActionPanel(PanelText txt) {
		this.actionPanel.addLast(txt);
		if(actionPanel.size() > ACTIONPANEL_MAX_SIZE)
			this.actionPanel.removeFirst();
	}
	
	public void displayActionPanel() {
		int actionCount = 1;
		int x = 0;
		int y = topLeftY + 2;
		
		for(int i=0; i< actionPanel.size(); i++) {
			if(actionPanel.get(i) == null) {
				actionCount++;
			} else {
				panel.write(actionPanel.get(i).text, x, y+actionCount, actionPanel.get(i).c);
				actionCount++;
			}
		}
		
	}
	
	
//	public void clearActionPanel() {
//		panel.clear(' ');
//	}
	
	
	public void clearActionPanelList() {
		actionPanel.clear();
	}
	
	
	
	public void refresh() {
		panel.clear(' ', 0, 0, width, height);
		displayPlayerHealth();
		displayLevelCount();
		displayActionPanel();
		drawLine();
	}

}

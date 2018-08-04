package roguelike;

import asciiPanel.AsciiPanel;

public class MainGUI extends AsciiPanel{
	public int topLeftX;
	public int topLeftY;
	private AsciiPanel panel;
	private Player player;
	private int width;
	private int height;
	
	public MainGUI(AsciiPanel a, Player p, int width, int height) {
		this.panel = a;
		this.player = p;
		this.width = width;
		this.height = height;
	}
	
	public void drawLine() {
		for(int i=0; i< this.width; i++) {
			panel.write('-', i, topLeftY + height);
		}
	}
	
	public void displayPlayerHealth() {
		panel.clear(' ', 0, 0, width, 1);
		panel.write("Player Health: " + Integer.toString(player.health), topLeftX, topLeftY);
	}
	
	public void displayLevelCount() {
		panel.write("Current depth: " + player.world.levelList.size(), topLeftX, topLeftY + 1);
	}
	
	public void refresh() {
		drawLine();
		displayPlayerHealth();
		displayLevelCount();
	}

}

package roguelike;

import java.awt.Color;

public class StartMenu {
	private ApplicationMain app;
	
	public StartMenu(ApplicationMain app) {
		this.app = app;
	}
	
	public void displayMenu() {
		app.getTerminal().clear();
		
		PanelText Play = new PanelText("Play", Color.GRAY);
	}
	
	public void startNewGame() {
		app.newGame();
		
	}

}

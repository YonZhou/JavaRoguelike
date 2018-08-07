package roguelike;

import java.awt.Color;
import java.util.ArrayList;

public class StartMenu extends MenuScreen{
	private ApplicationMain app;
	
	public StartMenu(ApplicationMain app) {
		this.app = app;
		this.textList = new ArrayList<PanelText>();
		PanelText Play = new PanelText("Play", Color.WHITE);
		textList.add(Play);
		PanelText Quit = new PanelText("Quit", Color.GRAY);
		textList.add(Quit);
	}
	
	public void displayScreen() {
		
		app.getTerminal().clear();
		
		app.getTerminal().write(textList.get(0).text, 40, 25, textList.get(0).getColor());
		app.getTerminal().write(textList.get(1).text, 40, 26, textList.get(1).getColor());
		
		app.repaint();
		
	}
	
	public void startNewGame() {
		app.newGame();
	}

	@Override
	public int checkOption() {
		if(textIndex == 0) {
			startNewGame();
		} else if(textIndex == 1) {
			app.world.close();
		}
		return textIndex;
	}



}

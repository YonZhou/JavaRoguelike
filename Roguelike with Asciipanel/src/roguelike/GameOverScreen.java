package roguelike;

import java.awt.Color;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;
//REMEMBER TO REPAINT

public class GameOverScreen{
	private AsciiPanel a;
	private World world;
	public PanelText NewGame;
	public PanelText Quit;
	public int w;
	public int h;
	public ArrayList<PanelText> textList;
	public int textIndex;
	
	public GameOverScreen(World worl) {
		this.world = worl;
		this.a = world.p.camera.p;
		this.NewGame = new PanelText("New Game", Color.WHITE);
		this.Quit = new PanelText("Exit", Color.GRAY);
		this.textList = new ArrayList<PanelText>();
		textList.add(NewGame);
		textList.add(Quit);
		this.textIndex = 0;
	}
	
	public void gameOverNewGame() {
		world.p.resetStats();
		world.reset();
		//world.app.g.refresh(); //WHY DO INEED TO ADD THIS LINE? fixed, watch out a=panel, app=appmain
		world.p.camera.renderCamera();
		world.gui.refresh();
		world.app.repaint();

	}

	public void moveUp() {
		clearColors();
		textIndex--;
		if(textIndex < 0) {
			textIndex = textList.size() - 1;
		}
		textList.get(textIndex).setColor(Color.WHITE);
		displayScreen();
	}
	
	
	//FIX THIS
	public void moveDown() {
		clearColors();
		textIndex++;
		textIndex = textIndex % (textList.size());
		textList.get(textIndex).setColor(Color.WHITE);
		System.out.println(textList.get(textIndex).getColor());
		displayScreen();
	}
	
	public void clearColors() {
		for(int i=0; i<textList.size(); i++) {
			textList.get(textIndex).setColor(Color.GRAY);
		}
	}
	public void displayScreen() {
		//world.p.camera.p.clear();
		a.clear(); //change to world.clear somehow (nevermind, set the panel to world camera anyway)

		a.write("You Died. ", 30, 25);
		a.write("Your score was " + world.levelList.size(), 30, 26);
		//a.write("Press Enter to start a new game", 30, 27);
		a.write(textList.get(0).text, 30, 28, textList.get(0).getColor());
		a.write(textList.get(1).text, 30, 29, textList.get(1).getColor());
		a.repaint();
	}
	
//	public void updateScreen() {
//		a.write(textList.get(0).text, 30, 28, textList.get(0).getColor());
//		a.write(textList.get(1).text, 30, 29, textList.get(1).getColor());
//		a.repaint();
//	}
	
	public int checkOption() {
		if(textIndex == 0) {
			gameOverNewGame();
		} else if(textIndex == 1) {
			world.close();
		}
		return textIndex;
	}

}

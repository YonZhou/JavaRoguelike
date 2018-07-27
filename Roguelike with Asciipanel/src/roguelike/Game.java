package roguelike;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

import javax.swing.JFrame;

public class Game extends JFrame{
	private AsciiPanel panel;
    private CreatureGenerator generate;
    private KeyHandler mainKeyListener;
    private Creature Player;
	
	public Game(AsciiPanel p) {
		this.panel = p;
		
		generate = new CreatureGenerator(panel);
		Player = generate.newPlayer();
	    mainKeyListener = new KeyHandler(Player);
	    this.addKeyListener(mainKeyListener);
	}
	
	public void mainGameLoop() {
		 Player.drawCreature();
	}
	

}

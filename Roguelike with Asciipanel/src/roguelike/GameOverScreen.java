package roguelike;

import asciiPanel.AsciiPanel;

public class GameOverScreen {
	private AsciiPanel a;
	private World world;
	public int w;
	public int h;
	
	public GameOverScreen(AsciiPanel a) {
		this.a = a;
	}
	
	public void gameOver() {
		world.reset();
		world.p.resetStats();
	}

}

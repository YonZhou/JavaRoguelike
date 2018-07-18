package roguelike;
import asciiPanel.AsciiPanel;

public class CreatureGenerator {
	
	public Creature newPlayer() {
		Creature newPlayerInstance = new Creature("Player", 100, 1, '@', 10, 10);
		return newPlayerInstance;
	}

	public void drawPlayer(AsciiPanel terminal) {
		Creature Player = newPlayer();
		Player.drawCreature(terminal);
	}
}

package roguelike;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class CreatureGenerator {
	
	public ArrayList<Creature> Creatures;
	
	public CreatureGenerator() {
		this.Creatures = new ArrayList<Creature>;
	}
	
	public Creature newPlayer() {
		Creature newPlayerInstance = new Creature("Player", 100, 1, '@', 10, 10);
		return newPlayerInstance;
	}

	public void addCreatureToCreatures(Creature creature) {
		this.Creatures.add(creature);
	}
	
	
	
	public void drawPlayer(AsciiPanel terminal) {
		Creature Player = newPlayer();
		Player.drawCreature(terminal);
	}
}

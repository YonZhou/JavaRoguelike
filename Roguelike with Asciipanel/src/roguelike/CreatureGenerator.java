package roguelike;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class CreatureGenerator {
	
	public ArrayList<Creature> Creatures;
	private AsciiPanel panel;
	
	public CreatureGenerator(AsciiPanel panel) {
		this.Creatures = new ArrayList<Creature>();
		this.panel = panel;
	}
	
	public Creature newPlayer() {
		Creature newPlayerInstance = new Creature("Player", 100, 1, '@', 10, 10, panel);
		return newPlayerInstance;

	}
	
	public Creature newMonster() {
		Creature monster = new Creature("Monster", 111, 1, '$', 11, 11, panel);
		return monster;
	}
	public void addCreatureToCreatures(Creature creature) {
		this.Creatures.add(creature);
	}
	
	public void drawCreature(Creature c) {
		c.drawCreature();
	}
}

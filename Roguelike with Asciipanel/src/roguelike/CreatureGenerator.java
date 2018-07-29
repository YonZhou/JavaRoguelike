package roguelike;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class CreatureGenerator {
	
	public ArrayList<Creature> Creatures;
	private AsciiPanel panel;
	private Level level;
	
	public CreatureGenerator(AsciiPanel panel, Level l) {
		this.Creatures = new ArrayList<Creature>();
		this.panel = panel;
		this.level = l;
	}
	
	public Creature newPlayer() {
		Creature newPlayerInstance = new Creature("Player", 100, 1, '@', 10, 10, panel, level);
		return newPlayerInstance;

	}
	
	public Creature newMonster() {
		Creature monster = new Creature("Monster", 111, 1, '$', 11, 11, panel, level);
		return monster;
	}
	public void addCreatureToCreatures(Creature creature) {
		this.Creatures.add(creature);
	}
}

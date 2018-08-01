package roguelike;
import java.awt.Color;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class CreatureGenerator {
	
	public ArrayList<Creature> Creatures;
	public Creature[][] creatureMap;
	public LevelGenerator lg;
	
	public CreatureGenerator(LevelGenerator lg) {
		this.Creatures = new ArrayList<Creature>();
		this.lg = lg;
	}
	
//	public Creature newPlayer() {
//		Creature newPlayerInstance = new Creature("Player", 100, 1, '@', 10, 10, panel, level, Color.WHITE);
//		return newPlayerInstance;
//
//	}
	
	//consider deleting this
	
	public Creature newMonster() {
		Creature monster = new Creature("Monster", 111, 1, '$', 11, 11, Color.RED);
		return monster;
	}
	public void addCreatureToCreatures(Creature creature) {
		this.Creatures.add(creature);
	}
}

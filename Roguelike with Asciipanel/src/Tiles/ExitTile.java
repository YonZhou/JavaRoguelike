package Tiles;

import java.awt.Color;
import java.util.Random;

import roguelike.Level;
import roguelike.Player;

public class ExitTile extends WalkableTile{

	public ExitTile(int x, int y) {
		super(x, y, '%', Color.GREEN);
		// TODO Auto-generated constructor stub
	}
	
	public void movePlayerNextLevel(Player p, Level l) {
		p.setLevel(l);
	}
	
	//this function is written in the level generator
//	public void addAtEmptyLocation(Level l) {
//		int max = l.walkabletileList.size();
//			
//		Random r = new Random();
//			
//		int randomIndex = r.nextInt(max);
//			
//		this.x = l.walkabletileList.get(randomIndex).getx();
//		this.y = l.walkabletileList.get(randomIndex).gety();
//	}
}

package roguelike;

import java.util.ArrayList;
import java.util.Random;

public class World {
	
	public ArrayList<Level> levelList;
	public LevelGenerator levelGen;
	public int maxWidth;
	public int maxHeight;
	public int minWidth;
	public int minHeight;
	public Level currentlevel;
	public int currentLevelCount;
	public Player p;
	public GameOverScreen ggScreen;
	
	public World(Player p) {
		this.levelList = new ArrayList<Level>();
		this.levelGen = new LevelGenerator();
		this.currentLevelCount = 0;
		this.ggScreen = new GameOverScreen(p.camera.p);
	}
	
	public void setBoundDimensions(int wM, int hM, int wm, int hm) {
		this.maxWidth = wM;
		this.maxHeight = hM;
		this.minWidth = wm;
		this.minHeight = hm;
	}
	
	public Level createRWalkLevel() {
		Random r = new Random();
		int width = r.nextInt(maxWidth - minWidth + 1) + minWidth;
		int height = r.nextInt(maxHeight - minHeight + 1) + minHeight;
		Level newWalkLevel = this.levelGen.generateRWalkLevel(1, width, height);
		this.levelList.add(newWalkLevel);
		return newWalkLevel;
	}
	
	public void setCurrentLevel(Level l) {
		this.currentlevel = l;
	}
	
	public Level nextLevel() {
		createRWalkLevel();
		currentLevelCount++;
		return levelList.get(currentLevelCount);
	}
	
	public void reset() {
		this.levelList.clear();
		
		currentLevelCount = -1;
		
		Level nextL = nextLevel(); 
		p.setLevel(nextL);
		nextL.setPlayer((Player) p);
		p.camera.renderCamera();

	}
	

}

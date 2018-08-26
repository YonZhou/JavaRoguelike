package roguelike;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Tiles.WallTile;
import menus.GameOverScreen;
import menus.InventoryScreen;
import menus.MenuScreen;
import menus.StartMenu;

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
	public InventoryScreen invScreen;
	public StartMenu startMenu;
	public ApplicationMain app;
	public MainGUI gui;

	
	public World(Player p, ApplicationMain app) {
		this.app = app;
		this.startMenu = new StartMenu(app);
		this.levelList = new ArrayList<Level>();
		this.levelGen = new LevelGenerator();
		this.currentLevelCount = -1;
		this.p = p;
		
		
		this.gui = new MainGUI(p.camera.p, p, app.TERMINAL_WIDTH, p.camera.topLeftY);
		this.ggScreen = new GameOverScreen(this);
		this.invScreen = new InventoryScreen(this);

	}
	
//	public void setApp(ApplicationMain app) {
//		this.app = app;
//	}
	
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
		newWalkLevel.setPlayer(p); //player will set the level in itself in function exit tile
		
		
		//makeLine(10,10,0,0);
		
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
		gui.clearActionPanelList();
		
		currentLevelCount = -1;
		
		Level nextL = nextLevel(); 
		p.setLevel(nextL);
		p.camera.renderCamera(p);

	}
	
	public void close() {
		app.setVisible(false);
		app.dispose();
		System.exit(0);
	}

	public void moveUp(MenuScreen menuscreen) {
		menuscreen.moveUp();
	}

	public void moveDown(MenuScreen menuscreen) {
		menuscreen.moveDown();
	}
	
	public ArrayList<Point> makeLine(int x1, int y1, int x2, int y2) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int signx = 1;
		int signy = 1;
		
		if(x2 < x1) {
			signx = -1;
		}
		
		
		if(y2 < y1) {
			signy = -1;
		}
		
		int error = dx - dy;
//		int dx2 = 2*dx;
//		int dy2 = 2*dy;
		
		while(true) {
			pointList.add(new Point(x1, y1));
			System.out.printf("added point %d, %d\n", x1, y1);
			
			if(x1 == x2 && y1 == y2) {
				break;
			}
			
			int e2 = 2*error;
			
            if (e2 > -dy) 
            {
                error = error - dy;
                x1 = x1 + signx;
            }
 
            if (e2 < dx) 
            {
                error = error + dx;
                y1 = y1 + signy;
            }
			
		}
		
		return pointList;
	}
	
	public boolean checkCanSee(int x1, int y1, int x2, int y2) {
		ArrayList<Point> pointList = makeLine(x1, y1, x2, y2);
		
		pointList.remove(pointList.size() - 1);
		for(Point i : pointList) {
			if(currentlevel.Map[i.x][i.y] instanceof WallTile || currentlevel.enemyMap[i.x][i.y] != null)
				return true;
		}
		
		
		return false;
		
	}
	
	public void createAmmoTypes() {
		
	}
	
//	public void playerDeath() {
//		boolean newGame = false;
//		
//		while(!newGame) {
//			ggScreen.displayScreen();
//			
//		}
//	}
	

}

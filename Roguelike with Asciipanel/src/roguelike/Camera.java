package roguelike;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;

public class Camera{
	private int x;
	private int y;
	private Level l;
	public AsciiPanel p;
	public int width;
	public int height;
	public int topLeftX;
	public int topLeftY;
	public Creature player;

	public Camera(Creature Player, int x, int y, Level l, AsciiPanel p) {
		this.player = Player;
		this.x = x;
		this.y = y;
		this.l = l;
		this.p = p;
	}
	
	public void setDimensions(int w, int h, int xoff, int yoff) {
		this.width = w;
		this.height = h;
		this.topLeftX = xoff;
		this.topLeftY = yoff;
	}
	
	public void setupCamera(Creature Player) {
		this.x = Player.getx();
		this.y = Player.gety();
		this.l = Player.l;
	}
	
	public void setCoordinates(Creature c) {
		this.x = c.getx();
		this.y = c.gety();
	}
	
	public void renderCameraOnPlayer() {
		renderCamera(player);
	}
	
	
	public void renderCamera(Creature c) {
		setCoordinates(c);
		
		//coordinates of top-left ON MAP, pure, width and height are size of camera
		//used to get reference coordinates for top left block to write
		int startx = this.x - (this.width / 2);
		int starty = this.y - (this.height / 2);
		
		
		//loop through the TERMINAL coordinates, map coordinates must add startx and starty
		//i and j must start at 0; they represent the loop and represent SCREEN coords
		//to convert i and j to coordinates on the map, add the x/y coordinate of top-left and then subtract the "starting" offset
		for(int i = topLeftX; i < topLeftX + this.width; i++) {
			for(int j = topLeftY; j < topLeftY + this.height; j++) {
				//NEED TO CREATE CHECK THAT CAN WRITE ON ASCIIPANEL(or maybe not?)
				
				int mapCheckX = i + startx - topLeftX;
				int mapCheckY = j + starty - topLeftY;
				
				//check for out of bounds on map
				if(mapCheckX< 0 || mapCheckX > l.width - 1|| mapCheckY < 0 || mapCheckY> l.height - 1) {
					p.write(' ', i, j);
				} else if(l.charMap[mapCheckX][mapCheckY] == null) {
					p.write(' ', i, j);
				} else {
					p.write(l.charMap[mapCheckX][mapCheckY].getID(), i, j, l.charMap[mapCheckX][mapCheckY].getColor());
					
					
					
					if(l.itemMap[mapCheckX][mapCheckY] != null) {
						p.write(l.itemMap[mapCheckX][mapCheckY].getID(),i ,j ,l.itemMap[mapCheckX][mapCheckY].getColor());
					}
					
					//check for enemies on the map and add them at subsequent locations(put in another function?)
					if(l.enemyMap[mapCheckX][mapCheckY] != null) {
						p.write(l.enemyMap[mapCheckX][mapCheckY].getID(),i ,j ,l.enemyMap[mapCheckX][mapCheckY].getColor());
					}
					
					
					
				}
			}
		}
		
		//bottom deprecated due to can just figure out the coords of player
		//p.write(player.getID(), topLeftX + (width / 2),topLeftY + (height / 2));
		
		if(player.getx() - startx + topLeftX >= 0 && player.gety() - starty + topLeftY >= 0 && player.getx() - startx < this.width && player.gety() - starty< this.height)
			p.write(player.getID(), topLeftX + player.getx() - startx, topLeftY + player.gety() - starty);
		player.world.app.repaint();
	}
	
	public void drawLineToEntity(Entity e) {
		ArrayList<Point> pointList = player.world.makeLine(player.getx(), player.gety(), e.getx(), e.gety());
		int startx = this.x - (this.width / 2);
		int starty = this.y - (this.height / 2);
		
		
		for(Point point : pointList) {
			if(point.x - startx + topLeftX >= 0 && point.y - starty + topLeftY >= 0 && point.x - startx < this.width && point.y - starty< this.height) {
				p.write(',', point.x - startx + topLeftX, point.y - starty + topLeftY, Color.GREEN);
			
//				if(player.world.currentlevel.enemyMap[point.x][point.y] != null) {
//					p.write(',', point.x - startx + topLeftX, point.y - starty + topLeftY, Color.GREEN);
//				}
				
				if(player.getx() - startx + topLeftX >= 0 && player.gety() - starty + topLeftY >= 0 && player.getx() - startx < this.width && player.gety() - starty< this.height)
					p.write(player.getID(), topLeftX + player.getx() - startx, topLeftY + player.gety() - starty);
				
				p.write(e.getID(), e.getx() - startx + topLeftX, e.gety() - starty + topLeftY, Color.RED, Color.WHITE);
			}
		}
		
		
	}
	
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setLevel(Level l) {
		this.l = l;
	}
	

}

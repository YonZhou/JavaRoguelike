package roguelike;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Camera{
	private int x;
	private int y;
	private Level l;
	private AsciiPanel p;
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
		this.topLeftX = 0;
		this.topLeftY = 3;
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
	
	public void setCoordinates() {
		this.x = player.getx();
		this.y = player.gety();
	}
	
	
	public void renderCamera() {
		setCoordinates();
		
		//coordinates of top-left ON MAP, pure, width and height are size of camera
		//used to get reference coordinates for top left block to write
		int startx = this.x - (this.width / 2);
		int starty = this.y - (this.height / 2);
		
		
		//loop through the TERMINAL coordinates, map coordinates must add startx and starty
		//i and j must start at 0; they represent the loop and represent SCREEN coords
		//to convert i and j to coordinates on the map, add the x/y coordinate of top-left and then subtract the "starting" offset
		for(int i = topLeftX; i < this.width; i++) {
			for(int j = topLeftY; j < this.height; j++) {
				//NEED TO CREATE CHECK THAT CAN WRITE ON ASCIIPANEL(or maybe not?)
				
				//check for out of bounds on map
				if(i + startx - topLeftX< 0 || i + startx - topLeftX > l.width - 1|| j + starty - topLeftY < 0 || j + starty - topLeftY> l.height - 1) {
					p.write(' ', i, j);
				} else if(l.Map[startx + i - topLeftX][starty + j - topLeftY] == null) {
					p.write(' ', i, j);
				} else {
					p.write(l.Map[startx + i - topLeftX][starty + j - topLeftY].getID(), i, j, l.Map[startx + i - topLeftX][starty + j - topLeftY].getColor());
				}
			}
		}
		
		p.write(player.getID(), topLeftX + (width / 2),topLeftY + (height / 2));
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

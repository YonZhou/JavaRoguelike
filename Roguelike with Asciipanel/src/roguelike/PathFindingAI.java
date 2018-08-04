package roguelike;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class PathFindingAI {
	
	public Integer[][] map;
	private Player p;
	public int startX;
	public int startY;
	public int topLeftX;
	public int topLeftY;
	public ArrayList<Point> edgeList;
	public final ArrayList<Point> directions;
	
	//convert creature x y to map x y: subtract topleft coordinates
	
	public PathFindingAI(Player p) {
		this.p = p;
		this.edgeList = new ArrayList<Point>();
		directions = new ArrayList<Point>();
		directions.add(new Point(0,-1));
		directions.add(new Point(1,0));
		directions.add(new Point(0,1));
		directions.add(new Point(-1,0));
		this.map = new Integer[p.aggroWidth][p.aggroHeight]; //pathfinding  map is size of entire map 
	}
	
	
	//note that terminal will display old directions when checking because u didnt clear
	public void updateMap() {
		clearMap();
		
		this.topLeftX = p.getx() - p.aggroWidth / 2;
		this.topLeftY = p.gety() - p.aggroHeight / 2;
		
		Point playerStart = new Point(p.getx() - topLeftX, p.gety() - topLeftY);
		
		edgeList.add(playerStart);
		
//		for(int i=0; i< p.aggroWidth; i++) {
//			for(int j=0; j<p.aggroHeight; j++) {
				
		while(!(edgeList.isEmpty())) {
			Point Current = edgeList.get(0);
			
			//loop through all directions
			for(Point dir : directions) {
				Integer directionCheckX = Current.x + dir.x;
				Integer directionCheckY = Current.y + dir.y;
				//add check for out of bounds in the current aggro range
				//
				if(checkInMapBounds(topLeftX + directionCheckX, topLeftY + directionCheckY) && p.l.Map[topLeftX + directionCheckX][topLeftY + directionCheckY].isWalkable() && checkInBounds(directionCheckX, directionCheckY)) {
					System.out.println(map[directionCheckX][directionCheckY]);
					if(map[directionCheckX][directionCheckY] == null) {
						Point point = new Point(directionCheckX, directionCheckY);
						edgeList.add(point);
						Integer newDir = convertDir(dir);
						map[directionCheckX][directionCheckY] = newDir;
						
						p.camera.p.write(Integer.toString(newDir), directionCheckX, directionCheckY);
					}
				}
			}
			edgeList.remove(Current);
			System.out.println(edgeList.size());
		}	
	}
	
	public boolean checkInMapBounds(Integer x, Integer y) {
		if(x < 0 || y < 0 || x >= p.l.Map.length || y >= p.l.Map[0].length) {
			return false;
		}
		return true;
	}
	
	public boolean checkInBounds(Integer x, Integer y) {
		if(x >= p.aggroWidth || x < 0 || y >= p.aggroHeight || y < 0) {
			return false;
		}
		return true;
	}
	
//	public void writeToMap(Integer direction, Integer directionCheckX, Integer directionCheckY) {
//		if(direction == 1) { //means you moved 1 up to get to the new block
//			map[directionCheckX][directionCheckY] = 3;
//		} else if(direction == 2) {
//			map[directionCheckX][directionCheckY] = 4;
//		} else if(direction == 3) {
//			map[directionCheckX][directionCheckY] = 1;
//		} else if(direction == 4){
//			map[directionCheckX][directionCheckY] = 2;
//		}
//	}
	
	public Integer convertDir(Point dir) {
		Integer d = 0;
		
		if(dir.x == 0 && dir.y == 1) {
			d = 1;
		} else if(dir.x == 1 && dir.y == 0) {
			d = 4;
		} else if(dir.x == 0 && dir.y == -1) {
			d = 3;
		} else if(dir.x == -1 && dir.y == 0){
			d = 2;
		}
		return d;
	}
	
	public void clearMap() {
		for(int i=0; i< p.aggroWidth; i++) {
			for(int j=0; j<p.aggroHeight; j++) {
				map[i][j] = null;
			}
		}
	}
	
	

}

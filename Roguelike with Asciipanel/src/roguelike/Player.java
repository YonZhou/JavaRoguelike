package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import items.Ammo;
import items.Gold;
import items.Gun;
import items.HealthPotion;
import items.Item;
import items.Stackable;
import items.Weapon;


public class Player extends Creature{

	private Creature engagedCreature;
	private Creature focusedCreatureInRange;
	public static final int BASE_HEALTH = 10000;
	public int aggroWidth;
	public int aggroHeight;
	public PathFindingAI ai;
	public boolean isAlive = true;
	public Inventory inv;
	public Weapon equippedWeapon;
	public ArrayList<Entity> enemiesInRange;
	public int index;
	

	public Player(int health, int level, int x, int y) {
		super("Player", health, level, '@', x, y, Color.WHITE);
		this.aggroWidth = 99;
		this.aggroHeight = 99;
		this.ai = new PathFindingAI(this);
		this.enemiesInRange = new ArrayList<Entity>();
		
		//this.equippedWeapon = new Weapon("Fists", '3', 0, 0, Color.BLACK);
		
		this.inv = new Inventory(5);
		
		this.inv.itemList.add(equippedWeapon);
		
		
	}
	
	public void resetInventory() {
		this.inv.clear();
		this.inv.itemList.add(new Ammo("Standard rounds", 1, 1, 10, 1));
		
		this.inv.itemList.add(new Gold(1, 1, 999999, 0));
		
		this.equippedWeapon = new Gun("Starter Pistol", 1, 1, 1, 1);
		this.inv.itemList.add(equippedWeapon);
	}
	public void setWorld(World w) {
		this.world = w;
	}
	
	@Override
	public void addAtEmptyLocation() {
		int max = this.l.FreeTileList.size();
		
		Random r = new Random();
		
		int randomIndex = r.nextInt(max);
		this.x = l.FreeTileList.get(randomIndex).getx();
		this.y = l.FreeTileList.get(randomIndex).gety();
		
		//check if on exit tile - deprecated due to new list just "writable"
		
//		while(l.FreeTileList.get(randomIndex) instanceof ExitTile) {
//			this.x = l.FreeTileList.get(randomIndex).getx();
//			this.y = l.FreeTileList.get(randomIndex).gety();
//			randomIndex = r.nextInt(max);
//		}
	}
	
	public void setLevel(Level l) {
		this.l = l;
		this.camera.setLevel(l);
		addAtEmptyLocation();
	}
	
	@Override
	public void moveCreature(int dx, int dy) {
		
		//TODO change it so if enemy is in the way need to get through it first?
		if(checkForExit(x + dx, y + dy)) {
			Level nextL = world.nextLevel(); 
			setLevel(nextL);
//			nextL.setPlayer(this);
			this.camera.renderCamera(this);
		}
		
		if(checkForCollision(x+dx, y+dy)) {
			if(checkForFight(x+dx, y+dy)) {
				System.out.println("contact");
				
				l.clearEnemyMap();
				
				attack(engagedCreature);
				
				l.updateEnemyMap();
			}
		} else {
			this.x += dx;
			this.y += dy;
		}
		
		
		
		ai.updateMap();
		
		l.moveAllCreatures();
		
		//this function should be somewhere else?
		//checkDeath();
		
		world.gui.refresh();
	}
	
	public void checkDeath() {
		if(this.health <= 0) {
			this.isAlive = false;
			System.out.println(this.isAlive);
			die();
		}
	}
	
	@Override
	public boolean checkForCollision(int xC, int yC) {
		if(!(l.checkWalkable(xC, yC)) || l.enemyMap[xC][yC] != null)
			return true;
		return false;
	}
	
	public boolean checkForExit(int xC, int yC) {
		if(l.checkExit(xC, yC)) {
			return true;
		}
		return false;
	}
	
	public boolean checkForFight(int xC, int yC) {
		if(l.enemyMap[xC][yC] != null) {
			this.engagedCreature = l.enemyMap[xC][yC];
			return true;
		}
		return false;
	}
	
	public void attack(Creature other) {
		Random r = new Random();
		
		int max = 50 + equippedWeapon.getAttack();
		int min = 50 + equippedWeapon.getAttack();
		
		int damageDealt = r.nextInt(max - min + 1) + min;
		
		this.world.gui.addToActionPanel(new PanelText("You hit "+ engagedCreature.name + " for " + damageDealt));
		
		other.takeDamage(damageDealt);
		other.checkPlayerKill();
		
		//this function already called in checkPlayerKill
//		if(other.health <= 0) {
//			other.die();
//			this.world.gui.addToActionPanel(new PanelText("You kill the " + engagedCreature.name, Color.RED));
//		}
	}
	
	public boolean checkForItem(int xC, int yC) {
		if(l.itemMap[xC][yC] != null) {
			return true;
		}
		return false;
	}
	
	public void pickUpItem() {
		if(checkForItem(this.x, this.y)) {
			if(inv.itemList.size() < inv.maxInvSize) {
				Item itempickedup = l.itemMap[this.x][this.y];
				
				
				//need to fix this
				if(itempickedup instanceof Stackable) {
					System.out.println("The id is: " + itempickedup.getItemID());
					
					
					if(inv.containsID(itempickedup.getItemID()) > -1) {
						int i = inv.containsID(itempickedup.getItemID());
						((Stackable) inv.itemList.get(i)).incrementCapacity(((Stackable) itempickedup).getCapacity());
					} else {
						this.inv.itemList.add(itempickedup);
					}
			
					world.gui.addToActionPanel(new PanelText("You picked up the " + itempickedup.getName() + " x" + ((Ammo) itempickedup).Capacity , Color.YELLOW));

				} else {
					this.inv.itemList.add(itempickedup);
					world.gui.addToActionPanel(new PanelText("You picked up the " + itempickedup.getName() + " + " + itempickedup.level , Color.YELLOW));
				}		
				l.deleteItem(this.x, this.y);
				
				l.moveAllCreatures();
				world.gui.refresh();
			} else {
				world.gui.addToActionPanel(new PanelText("Full inventory space!", Color.RED));
				world.gui.refresh();
			}
			
		} else {
			world.gui.addToActionPanel(new PanelText("No Item!" , Color.ORANGE));
			world.gui.refresh();
		}
	}
	
	@Override
	public void die() {
		//resetStats();
		//this.world.reset();
		
		//the bottom 2 get over-ridden by the repaint in the player move keyhandler that comes right after move
		this.world.ggScreen.displayScreen();
		this.world.app.repaint();
		this.world.app.mainKeyListener.switchGameOver();
	}
	
	public void resetStats() {
		this.health = BASE_HEALTH;
		this.maxHealth = BASE_HEALTH;

		world.invScreen.clearTextList();
		
		resetInventory();
		
		this.isAlive = true;
	}
	
	public void use(Item item) {
		if(item instanceof HealthPotion) {
			healHealth(((HealthPotion) item).getHealingFactor());
			this.world.invScreen.useItem();
		}
		
	}
	
	public void updateEnemiesInRange() {
		enemiesInRange.clear();
		
		if(equippedWeapon instanceof Gun) {
		
			int range = ((Gun) (equippedWeapon)).getRange();
			
			int topLeftX = this.x - range;
			int topLeftY = this.y - range;
			
			
			//should check if something is blocking them
			
			for(int i= topLeftX; i< topLeftX + range*2; i++) {
				for(int j = topLeftY; j< topLeftY + range*2; j++) {
					if(i > 0 && i < l.Map.length && j > 0 && j < l.Map[0].length && l.enemyMap[i][j] != null ) {
						if(!world.checkCanSee(this.x, this.y, i,j))
							enemiesInRange.add(l.enemyMap[i][j]);
					}
				}
			}

		}
	}
	
	public void switchFocusLeft() {
		if(enemiesInRange.size() > 0) {
			this.index--;
			if(this.index < 0) {
				this.index = this.enemiesInRange.size() - 1;
			}
			camera.renderCamera((Creature) enemiesInRange.get(index));
			camera.drawLineToEntity(enemiesInRange.get(index));
		}
		world.gui.refresh();
	}
	
	public void switchFocusRight() {
		if(enemiesInRange.size() > 0) {
			this.index++;
			if(this.index >= this.enemiesInRange.size()) {
				this.index = 0;
			}
			
			camera.renderCamera((Creature) enemiesInRange.get(index));
			camera.drawLineToEntity(enemiesInRange.get(index));
		} else {
			exitShooting();
			world.app.mainKeyListener.clearShootKeys();
			world.app.mainKeyListener.rebindPlayerMovement();

		}
		world.gui.refresh();
	}
	
	public void setupShoot() {
		updateEnemiesInRange();
		this.index = 0;
		
		if(enemiesInRange.size() > 0) {
			Entity focusedEnemy = enemiesInRange.get(index);
			camera.renderCamera((Creature) focusedEnemy);
			camera.drawLineToEntity(focusedEnemy);
		}
		
		//NEED TOA DD THIS LINE EVERYTIME SO THE GUI GOES ON TOP. ANY WAY AROUND THIS???
		world.gui.refresh();
	}
	
//	public void shoot(Creature c) {
//		Gun gun = (Gun) this.equippedWeapon;
//		
//		if(this.inv.containsID(gun.ammoType) > 0) {
//			int i;
//			i = this.inv.containsID(gun.ammoType);
//			if(!((Ammo)this.inv.itemList.get(i)).isEmpty())
//				((Ammo) this.inv.itemList.get(i)).decrementCapacity(1);
//			c.takeDamage(gun.getAttack());
//			world.gui.addToActionPanel(new PanelText("You shoot the " + c.name +" for " + gun.getAttack()));
//			if(c.checkPlayerKill())
//				enemiesInRange.remove(index);
//		} else {
//			world.gui.addToActionPanel(new PanelText("No ammo"));
//		}
//		
//		//Creature focusedEnemy = (Creature) enemiesInRange.get(index);
//
//	}
	
	public void shoot() {
		
		l.moveAllCreatures();
		
		updateEnemiesInRange();
		
		Gun gun = (Gun) this.equippedWeapon;
		
		
		if(!inShootingMode()) {
			exitShooting();
			return;
		}
		
		if(this.index >= this.enemiesInRange.size()) {
			this.index = 0;
		}
		
		Creature focusedEnemy = (Creature) enemiesInRange.get(index);
		
		if(focusedCreatureInRange != null && enemiesInRange.contains(focusedCreatureInRange)) {
			focusedEnemy = focusedCreatureInRange;
		}
		
		
		
		this.camera.renderCamera(focusedEnemy);
		this.camera.drawLineToEntity(focusedEnemy);
		
		if(this.inv.containsID(gun.ammoType) > 0) {

			
			int i = this.inv.containsID(gun.ammoType);
			
			if(!((Ammo)this.inv.itemList.get(i)).isEmpty()) {
				((Ammo) this.inv.itemList.get(i)).decrementCapacity(1);
				focusedEnemy.takeDamage(gun.getAttack());
				
				world.gui.addToActionPanel(new PanelText("You shoot the " + focusedEnemy.name +" for " + gun.getAttack()));
			} else {
				world.gui.addToActionPanel(new PanelText("Out of Ammo!", Color.RED));
			}
			if(focusedEnemy.checkPlayerKill()) {
				this.enemiesInRange.remove(index);
				updateEnemiesInRange();
				switchFocusRight();
			}
		} else {
			world.gui.addToActionPanel(new PanelText("No ammo!"));
		}
		
		//ai.updateMap();
		
		this.focusedCreatureInRange = focusedEnemy;
		
		
		world.gui.refresh();
		world.app.repaint();
	}
	
	
	public void exitShooting() {
		this.enemiesInRange.clear();
		focusedCreatureInRange = null;
		this.index = 0;
		camera.renderCamera(this);
	}
	
	public boolean inShootingMode() {
		if(this.enemiesInRange.size() > 0) {
			return true;
		}
		return false;
	}
	
	

}

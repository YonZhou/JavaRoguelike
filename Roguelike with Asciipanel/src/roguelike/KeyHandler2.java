package roguelike;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import items.Gun;
import menus.MenuScreen;

public class KeyHandler2 {
	private Player p;
	private ApplicationMain a;
	
	public KeyHandler2(Player p, ApplicationMain a) {
		this.p = p;
		this.a = a;
		setup();
	}
	
	public void setup() {
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("S"), "downKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("W"), "upKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("D"), "rightKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("A"), "leftKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "EnterKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "SpaceKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("I"), "IKey");
		a.getTerminal().getInputMap().put(KeyStroke.getKeyStroke("E"), "EKey");
		
		
		//everything above this line should stay in setup
		
		//rebindPlayerMovement();
		rebindStartMenuMovement();
		
		
		//2 calls below dont do anything???
		//p.camera.renderCamera();
		//a.repaint();
        System.out.println("Player x " + p.x);
        System.out.println("Player y " + p.y);
	}
	
	public void switchGameOver() {
		clearPlayerKeys();
		a.getTerminal().getActionMap().put("downKey", new ScreenSelectDown(p.world.ggScreen));
		a.getTerminal().getActionMap().put("upKey", new ScreenSelectUp(p.world.ggScreen));
//		a.getTerminal().getActionMap().put("rightKey", null);
//		a.getTerminal().getActionMap().put("leftKey", null);
		a.getTerminal().getActionMap().put("EnterKey", new EndGameSelect());
	}
	
	public void clearMovementKeys() {
		a.getTerminal().getActionMap().put("downKey", null);
		a.getTerminal().getActionMap().put("upKey", null);
		a.getTerminal().getActionMap().put("rightKey", null);
		a.getTerminal().getActionMap().put("leftKey", null);
	}
	
	public void clearPlayerKeys() {
		clearMovementKeys();
		a.getTerminal().getActionMap().put("IKey", null);
		a.getTerminal().getActionMap().put("SpaceKey", null);
		a.getTerminal().getActionMap().put("EKey", null);
	}
	
	public void rebindStartMenuMovement() {
		clearPlayerKeys();
		a.getTerminal().getActionMap().put("downKey", new ScreenSelectDown(p.world.startMenu));
		a.getTerminal().getActionMap().put("upKey", new ScreenSelectUp(p.world.startMenu));
		a.getTerminal().getActionMap().put("EnterKey", new startMenuAction());
	}
	
	
	public void clearEnterKey() {
		a.getTerminal().getActionMap().put("EnterKey", null);
	}
	public void rebindPlayerMovement() {
		a.getTerminal().getActionMap().put("downKey", new PlayerMovementAction(0, 1));
		a.getTerminal().getActionMap().put("upKey", new PlayerMovementAction(0, -1));
		a.getTerminal().getActionMap().put("rightKey", new PlayerMovementAction(1, 0));
		a.getTerminal().getActionMap().put("leftKey", new PlayerMovementAction(-1, 0));
		a.getTerminal().getActionMap().put("SpaceKey", new PlayerPickupAction());
		a.getTerminal().getActionMap().put("IKey", new OpenInventoryAction(0));
		a.getTerminal().getActionMap().put("EKey", new ShootModeAction());
	}
	
	public void switchInventoryKeys() {
		clearPlayerKeys();
		a.getTerminal().getActionMap().put("IKey", new CloseInventoryAction(0));
		a.getTerminal().getActionMap().put("downKey", new ScreenSelectDown(p.world.invScreen));
		a.getTerminal().getActionMap().put("upKey", new ScreenSelectUp(p.world.invScreen));
		a.getTerminal().getActionMap().put("EnterKey", new InventorySelect(0));
		a.getTerminal().getActionMap().put("SpaceKey", new InventorySelect(0));
		a.getTerminal().getActionMap().put("EKey", new dropItemAction(0));
	}
	
	public void switchInventoryKeysShooting() {
		clearPlayerKeys();
		a.getTerminal().getActionMap().put("IKey", new CloseInventoryAction(1));
		a.getTerminal().getActionMap().put("downKey", new ScreenSelectDown(p.world.invScreen));
		a.getTerminal().getActionMap().put("upKey", new ScreenSelectUp(p.world.invScreen));
		a.getTerminal().getActionMap().put("EnterKey", new InventorySelect(1));
		a.getTerminal().getActionMap().put("SpaceKey", new InventorySelect(1));
		a.getTerminal().getActionMap().put("EKey", new dropItemAction(1));
	}
	
	
	public void clearShootKeys() {
		a.getTerminal().getActionMap().put("EnterKey", null);
	}
	
	public void switchShootKeys() {
		clearPlayerKeys();
		a.getTerminal().getActionMap().put("rightKey", new ShootSelect(0));
		a.getTerminal().getActionMap().put("leftKey", new ShootSelect(1));
		a.getTerminal().getActionMap().put("EKey", new CloseShootModeAction());
		a.getTerminal().getActionMap().put("EnterKey", new ShootAction());
		a.getTerminal().getActionMap().put("IKey", new OpenInventoryAction(1));
	}
	
	class OpenInventoryAction extends AbstractAction{
		public int mode;
		
		
		public OpenInventoryAction(int i) {
			this.mode = i;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(mode == 0)
				switchInventoryKeys();
			else if(mode == 1) {
				switchInventoryKeysShooting();
			}
			p.world.invScreen.openInvScreen();
		}
		
	}
	
	
	class CloseInventoryAction extends AbstractAction{
		int mode;
		
		public CloseInventoryAction(int i) {
			this.mode = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//erase the inventory layer, may need to add more to this as more guis emerge
			a.world.gui.refresh();
			
			if(mode == 0) {
				p.camera.renderCamera(p);
				rebindPlayerMovement();
				a.getTerminal().getActionMap().put("EnterKey", null);
			} else if(mode == 1) {
				
				//add check for if gun is unequipped
				p.camera.renderCamera((Creature) p.enemiesInRange.get(p.index));
				p.camera.drawLineToEntity((Creature) p.enemiesInRange.get(p.index));
				
				switchShootKeys();
			}
			
		}
		
	}
	
	class PlayerPickupAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.pickUpItem();
			a.repaint();
		}
		
	}
	
	
	class PlayerMovementAction extends AbstractAction{
		public int x;
		public int y;
		
		public PlayerMovementAction(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			p.moveCreature(x, y);
			
			
			p.camera.renderCamera(p);
			//p.camera.renderCamera(p.l.enemyList.get(0));
			
			//this should come last, or else the death screen will be rendered first and overwritten by p.camera.render
			p.checkDeath();
			
	        System.out.println("Player x " + p.x);
	        System.out.println("Player y " + p.y);
		}

		
	}
	
	class dropItemAction extends AbstractAction{
		public int mode;
		public dropItemAction(int i) {
			this.mode = i;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.world.invScreen.dropItem();
			
		}
		
	}
	
	class startMenuAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int actionChecked = p.world.startMenu.checkOption();
			
			if(actionChecked == 0) {
				rebindPlayerMovement();
				clearEnterKey();
			}
			
		}
		
	}
	
	class EndGameSelect extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			int actionChecked = p.world.ggScreen.checkOption();
			
			if(actionChecked == 0) {
				rebindPlayerMovement();
				clearEnterKey();
			}
			
			//p.world.ggScreen.displayScreen();
			
		}
		
	}
	
	class ScreenSelectUp extends AbstractAction{
		
		private MenuScreen menuscreen;

		public ScreenSelectUp(MenuScreen ms) {
			this.menuscreen = ms;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.world.moveUp(menuscreen);
			//System.out.println("moved up");
		}
		
	}
	
	class ScreenSelectDown extends AbstractAction{
		
		private MenuScreen menuscreen;

		public ScreenSelectDown(MenuScreen ms) {
			this.menuscreen = ms;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			p.world.moveDown(menuscreen);
		}
		
	}
	
	class InventorySelect extends AbstractAction{
		public int mode;
		public InventorySelect(int i) {
			this.mode = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int actionChecked = p.world.invScreen.checkOption();
			
		}
		
	}
	class ShootAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.shoot();
		}
		
	}
	
	class ShootModeAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(p.equippedWeapon instanceof Gun) {
				p.setupShoot();
				if(p.enemiesInRange.size() > 0) {
					switchShootKeys();
				} else {
					p.world.gui.addToActionPanel(new PanelText("No enemies in range"));
					p.world.gui.refresh();
					a.repaint();
				}
				
			} else {
				p.world.gui.addToActionPanel(new PanelText("No gun equipped!", Color.RED));
				p.world.gui.refresh();
				a.repaint();
			}
		}
		
	}
	
	class ShootSelect extends AbstractAction{
		public int direction;
		
		public ShootSelect(int i) {
			direction = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(direction == 0) {
				p.switchFocusLeft();
			} else {
				p.switchFocusRight();
			}
			
		}
		
	}
	
	class CloseShootModeAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.exitShooting();
			
			rebindPlayerMovement();
			a.getTerminal().getActionMap().put("EnterKey", null);
			
		}
	
	}



}

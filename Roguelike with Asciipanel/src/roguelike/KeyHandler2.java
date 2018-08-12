package roguelike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

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
		clearMovementKeys();
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
	
	public void rebindStartMenuMovement() {
		clearMovementKeys();
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
			
			
			p.camera.renderCamera();
			
			//this should come last, or else the death screen will be rendered first and overwritten by p.camera.render
			p.checkDeath();
			
	        System.out.println("Player x " + p.x);
	        System.out.println("Player y " + p.y);
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
			System.out.println("moved up");
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




}

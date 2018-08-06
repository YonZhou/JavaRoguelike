package roguelike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

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
		
		
		a.getTerminal().getActionMap().put("downKey", new PlayerMovementAction(0, 1));
		a.getTerminal().getActionMap().put("upKey", new PlayerMovementAction(0, -1));
		a.getTerminal().getActionMap().put("rightKey", new PlayerMovementAction(1, 0));
		a.getTerminal().getActionMap().put("leftKey", new PlayerMovementAction(-1, 0));
		
		p.camera.renderCamera();
		a.repaint();
        System.out.println("Player x " + p.x);
        System.out.println("Player y " + p.y);
	}
	
	public void switchGameOver() {
		a.getTerminal().getActionMap().put("downKey", new EndGameSelectDown());
		a.getTerminal().getActionMap().put("upKey", new EndGameSelectUp());
		a.getTerminal().getActionMap().put("rightKey", null);
		a.getTerminal().getActionMap().put("leftKey", null);
		a.getTerminal().getActionMap().put("EnterKey", new EndGameSelect());
	}
	
	
	public void clearEndGameAction() {
		a.getTerminal().getActionMap().put("EnterKey", null);
	}
	public void rebindPlayerMovement() {
		a.getTerminal().getActionMap().put("downKey", new PlayerMovementAction(0, 1));
		a.getTerminal().getActionMap().put("upKey", new PlayerMovementAction(0, -1));
		a.getTerminal().getActionMap().put("rightKey", new PlayerMovementAction(1, 0));
		a.getTerminal().getActionMap().put("leftKey", new PlayerMovementAction(-1, 0));
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
			if(p.isAlive) {
				p.moveCreature(x, y);
				p.camera.renderCamera();
				a.repaint();
		        System.out.println("Player x " + p.x);
		        System.out.println("Player y " + p.y);
			}
		}

		
	}
	
	class EndGameSelect extends AbstractAction{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			int actionChecked = p.world.ggScreen.checkOption();
			
			if(actionChecked == 0) {
				rebindPlayerMovement();
				clearEndGameAction();
			}
			
			//p.world.ggScreen.displayScreen();
			
		}
		
	}
	
	class EndGameSelectUp extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p.world.ggScreen.moveUp();
			System.out.println("moved up");
		}
		
	}
	
	class EndGameSelectDown extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			p.world.ggScreen.moveDown();
		}
		
	}




}

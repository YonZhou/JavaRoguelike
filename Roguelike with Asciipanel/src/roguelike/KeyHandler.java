package roguelike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	private Creature p;
	
	public KeyHandler(Creature p) {
		this.p = p;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			p.moveCreature(0,-1);
	        System.out.println(p.y);
	        p.clearCreature();
			p.drawCreature();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}

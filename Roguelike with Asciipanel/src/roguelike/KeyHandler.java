package roguelike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	private Creature p;
	private ApplicationMain a;
	
	public KeyHandler(Creature p, ApplicationMain a) {
		this.p = p;
		this.a = a;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			p.moveCreature(0, 1);
		}
		if(k.getKeyCode() == KeyEvent.VK_UP) {
			p.moveCreature(0, -1);
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			p.moveCreature(1, 0);
		}
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			p.moveCreature(-1, 0);
		}
		
		p.camera.renderCamera();
		a.repaint();
        System.out.println(p.x);
        System.out.println(p.y);
		
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

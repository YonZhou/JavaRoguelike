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
		if(k.getKeyCode() == KeyEvent.VK_S) {
			p.moveCreature(0, 1);
		}
		if(k.getKeyCode() == KeyEvent.VK_W) {
			p.moveCreature(0, -1);
		}
		if(k.getKeyCode() == KeyEvent.VK_D) {
			p.moveCreature(1, 0);
		}
		if(k.getKeyCode() == KeyEvent.VK_A) {
			p.moveCreature(-1, 0);
		}
		if(k.getKeyCode() == KeyEvent.VK_SPACE) {
			Level nextL = p.world.nextLevel(); 
			p.setLevel(nextL);
			nextL.setPlayer((Player) p);
			p.camera.renderCamera();
		}
		
		//p.l.update();
		
		//function is called whenever key is detected(render camera sets coords and rewrites screen)
		p.camera.renderCamera();
		a.repaint();
        System.out.println("Player x " + p.x);
        System.out.println("Player y " + p.y);
		
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

package roguelike;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
//todo: screens, add creaturegenereator to level generator, levels should have creatures[][] and creature list
public class ApplicationMain extends JFrame{
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;
    private KeyHandler mainKeyListener;
    private Player Player;
    
    public static final int TERMINAL_WIDTH = 100;
    public static final int TERMINAL_HEIGHT = 50;
    
    public ApplicationMain() {
	    //super();
	    terminal = new AsciiPanel(TERMINAL_WIDTH,TERMINAL_HEIGHT,AsciiFont.TALRYTH_15_15);
	    
	    World world = new World();
	    
	    // world.setBoundDimensions(1000, 1000, TERMINAL_WIDTH, TERMINAL_HEIGHT);
	    world.setBoundDimensions(100, 100, 10, 10);
	    world.createRWalkLevel();
	    world.setCurrentLevel(world.levelList.get(0));
	    
	    
	    Level thirdLevel = world.currentlevel;
	    Player = new Player(100, 1, 1, 1);
	    Player.setWorld(world);
	    //only needs to be called once
	    Camera camera = new Camera(Player, Player.x, Player.y, Player.l, terminal);
	    Player.setCamera(camera);
	    Player.camera.setDimensions(TERMINAL_WIDTH, TERMINAL_HEIGHT, 0, 0);
	    
	    //camera level is set along with camera
	    Player.setLevel(thirdLevel);
	    Player.addAtEmptyLocation();
	    
	    Player.camera.renderCamera();
	    
	    mainKeyListener = new KeyHandler(Player, this);
	    addKeyListener(mainKeyListener);
	    
	    
	    
	    System.out.println("Completed task 1");
    	add(terminal);
    	pack();
    	repaint();
    }
    
    
    @Override
    public void repaint() {
    	//re-draw everything that will stay on screen(not needed anymore?)
//	    currentLevel.drawLevel(terminal);
	    //Player.drawCreature();
    	super.repaint();
    }
    


	public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Roguelike");
    }

}

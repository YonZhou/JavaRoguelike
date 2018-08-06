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
    public KeyHandler2 mainKeyListener;
    private Player Player;
    private MainGUI g;
    
    public static final int TERMINAL_WIDTH = 100;
    public static final int TERMINAL_HEIGHT = 50;
    
    public ApplicationMain() {
	    //super();
	    setTerminal(new AsciiPanel(TERMINAL_WIDTH,TERMINAL_HEIGHT,AsciiFont.TALRYTH_15_15));
	    
	    
	    // world.setBoundDimensions(1000, 1000, TERMINAL_WIDTH, TERMINAL_HEIGHT);
	    
	    
	    this.Player = new Player(100, 1, 1, 1);
	    
	    //only needs to be called once
	    Camera camera = new Camera(Player, Player.x, Player.y, Player.l, getTerminal());
	    Player.setCamera(camera);
	    World world = new World(Player);
	    Player.setWorld(world);
	    
	    world.setApp(this);
	    world.setBoundDimensions(1000, 1000, 100, 100);
	    world.createRWalkLevel();
	    world.setCurrentLevel(world.levelList.get(0));
	    
	    Level thirdLevel = world.currentlevel;
	    //Player.camera.setDimensions(TERMINAL_WIDTH, TERMINAL_HEIGHT - 10, 0, 10);
	    Player.camera.setDimensions(TERMINAL_WIDTH, TERMINAL_HEIGHT, 0, 10);
	    
	    //camera level is set along with camera
	    Player.setLevel(thirdLevel);
	    thirdLevel.setPlayer(Player);
	    Player.addAtEmptyLocation(); //coordinates are initiated
	    
	    g = new MainGUI(getTerminal(), Player, TERMINAL_WIDTH, Player.camera.topLeftY);
	    
	    Player.camera.renderCamera();
	    
	    //mainKeyListener = new KeyHandler(Player, this);
	    //addKeyListener(mainKeyListener);
	    mainKeyListener = new KeyHandler2(Player, this);
	    
	    
	    System.out.println("Completed task 1");
    	add(getTerminal());
    	pack();
    	repaint();
    }
    
    
    @Override
    public void repaint() {
    	//re-draw everything that will stay on screen(not needed anymore?)
//	    currentLevel.drawLevel(terminal);
	    //Player.drawCreature();
    	if(Player.isAlive)
    		g.refresh();
    	else {
    		Player.world.ggScreen.displayScreen();
    	}
    	super.repaint();
    }
    


	public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Roguelike");
    }


	public AsciiPanel getTerminal() {
		return terminal;
	}


	public void setTerminal(AsciiPanel terminal) {
		this.terminal = terminal;
	}
	

}

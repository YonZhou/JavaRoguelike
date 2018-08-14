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
    public MainGUI g;
    public World world;
    
    public static final int TERMINAL_WIDTH = 100;
    public static final int TERMINAL_HEIGHT = 50;
    
    public ApplicationMain() {
	    //super();
	    setTerminal(new AsciiPanel(TERMINAL_WIDTH,TERMINAL_HEIGHT,AsciiFont.TALRYTH_15_15));
	    
	    
	    // world.setBoundDimensions(1000, 1000, TERMINAL_WIDTH, TERMINAL_HEIGHT);
	    
	    
	    this.Player = new Player(Player.BASE_HEALTH, 1, 1, 1);
	    Player.isAlive = false; //to erase the GUI, but maybe need better method since gameover pops up whoops
	    
	    //only needs to be called once
	    Camera camera = new Camera(Player, Player.x, Player.y, Player.l, getTerminal());
	    Player.setCamera(camera);
	    Player.camera.setDimensions(TERMINAL_WIDTH, TERMINAL_HEIGHT, 0, 10);
	    this.world = new World(Player, this);
	    Player.setWorld(world);
	    
	    world.setBoundDimensions(100, 100, 100, 100);
	    
//	    world.createRWalkLevel(); //after menu screen
//	    world.setCurrentLevel(world.levelList.get(0)); //after menu screen
//	    
//	    Level firstLevel = world.currentlevel; //after menu screen
	    //Player.camera.setDimensions(TERMINAL_WIDTH, TERMINAL_HEIGHT - 10, 0, 10);
	    
	    //camera level is set along with camera
	    
//	    Player.setLevel(firstLevel); //After menu screen
//	    firstLevel.setPlayer(Player); //after menu screen
//	    Player.addAtEmptyLocation(); //after menu screen
	    
	    
	    //newGame(); //TODO
	    
	    world.startMenu.displayScreen();
	    
	    
//	    Player.camera.renderCamera();//after menu screen
	    
	    //mainKeyListener = new KeyHandler(Player, this);
	    //addKeyListener(mainKeyListener);
	    mainKeyListener = new KeyHandler2(world.p, this);
	    
	    
	    System.out.println("Completed task 1");
    	add(terminal);
    	pack();
    	repaint();
    }
    
//    
//    @Override
//    public void repaint() {
//    	//re-draw everything that will stay on screen(not needed anymore?)
////	    currentLevel.drawLevel(terminal);
//	    //Player.drawCreature();
//    	
//    	//player.isalive draw function should be somewhere else, shouldnt need to check everytime
//    	
//    	//TODO: for some reason ggscreen will not update until another movement is called
////    	if(!Player.isAlive) {
////    		Player.world.ggScreen.displayScreen();
////    	}
//    	super.repaint();
//    }
    


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
	
	public void newGame() {
		//fix bug of initially the main gui writing 2 as depth??? TODO
		Player.isAlive = true;
		System.out.println(world.levelList.size());
		world.reset(); //ALREADY MADE THE LEVEL
		//world.createRWalkLevel();
	    world.setCurrentLevel(world.levelList.get(0));
		Player.setLevel(world.levelList.get(0));
		world.levelList.get(0).setPlayer(Player);
		Player.addAtEmptyLocation();
		Player.camera.renderCamera();
		world.gui.refresh();
	}

}

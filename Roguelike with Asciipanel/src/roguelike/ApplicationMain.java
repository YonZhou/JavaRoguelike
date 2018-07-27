package roguelike;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

public class ApplicationMain extends JFrame {
    private static final long serialVersionUID = 1060623638149583738L;

    public AsciiPanel terminal;
    private CreatureGenerator generate;
    private KeyHandler mainKeyListener;
    private Creature Player;
    
    public ApplicationMain() {
	    super();
	    terminal = new AsciiPanel(100,50,AsciiFont.TALRYTH_15_15);
	    generate = new CreatureGenerator(terminal);
	    terminal.write("Roguelike", 1, 1);
	    
	    Player = generate.newPlayer();
	    Player.drawCreature();

	    mainKeyListener = new KeyHandler(Player);
	    this.addKeyListener(mainKeyListener);
	    
	    System.out.println("Completed task 1");
    	add(terminal);
    	pack();
    	repaint();
    }
    


	public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Roguelike");
    }
}

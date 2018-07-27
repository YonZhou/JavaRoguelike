package roguelike;
import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import javax.swing.JFrame;

import org.w3c.dom.Entity;

public class Screen extends JFrame{
	private static final long serialVersionUID = 1L;
	public static AsciiPanel terminal;
    private CreatureGenerator generate;
    
	public Screen() {
	}
	
	public static void drawEntity(Entity entity) {
		terminal.write(entity.x,entity.y);
	}

}

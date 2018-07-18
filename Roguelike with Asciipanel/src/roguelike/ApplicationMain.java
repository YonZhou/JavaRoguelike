package roguelike;

import javax.swing.JFrame;
a
import asciiPanel.AsciiPanel;

import asciiPanel.AsciiFont;

public class ApplicationMain extends JFrame {
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;
    private CreatureGenerator generate;

    public ApplicationMain(){
        super();
        terminal = new AsciiPanel(200,50, AsciiFont.TALRYTH_15_15);
        terminal.write("Roguelike", 1, 1);
        generate = new CreatureGenerator();
        generate.drawPlayer(terminal);
        System.out.println("Completed task 1");
        add(terminal);
        pack();

    }


	public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}

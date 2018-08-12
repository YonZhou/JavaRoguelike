package roguelike;

import java.awt.Color;

public class PanelText {
	public String text;
	public Color c;
	
	public PanelText(String s, Color c) {
		this.text = s;
		this.c = c;
	}
	
	public PanelText(String s) {
		this.text = s;
		this.c = Color.WHITE;
	}
	
	public void setColor(Color color) {
		this.c = color;
	}
	
	public Color getColor() {
		return this.c;
	}

}

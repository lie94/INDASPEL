package hitboxes;

import java.awt.Color;
import java.awt.Graphics;
import navigation.Coord;
import main.DrawText;

public class Exit extends Block{
	private static final int WIDTH = 100, HEIGHT = 100;
	private int exitCode;
	private DrawText text;
	public Exit(Coord upper_left_corner, Coord size, int exit_code) {
		super(upper_left_corner, size);
		exitCode = exit_code; 
		color = Color.CYAN;
	}
	public Exit(Coord upper_left_corner, int exit_code){
		super(upper_left_corner, new Coord(WIDTH,HEIGHT));
		exitCode = exit_code;
		color = Color.CYAN;
	}
	public int getExitCode(){
		return exitCode;
	}
	public Exit setText(String s){
		text = new DrawText(getMiddle(),s);
		return this;
	}
	public Exit setColor(Color c){
		color = c;
		return this;
	}
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), Width(), Height());
		draw_border(g);
		if(text != null){
			//TODO Display text in the middle of the exit
			text.draw(g);
		}
	}
}

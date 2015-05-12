package hitboxes;

import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;

public class Exit extends Block{
	private int exitCode;
	private String text;
	public Exit(Coord upper_left_corner, Coord lower_right_corner, int exit_code) {
		super(upper_left_corner, lower_right_corner);
		exitCode = exit_code; 
		color = Color.CYAN;
	}
	public int getExitCode(){
		return exitCode;
	}
	public Exit setText(String s){
		text = s;
		return this;
	}
	public void draw(Graphics g){
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), Width(), Height());
		if(text != null){
			
		}
	}
}

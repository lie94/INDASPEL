package main;
import hitboxes.Exit;

import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public class DrawText {
	private String text;
	private Coord upper_left_corner;
	private Color color;
	public DrawText(Coord upper_left_corner, String text){
		this.text = text;
		this.upper_left_corner = upper_left_corner;
		color = Color.BLACK;
	}
	public DrawText(Exit e, String text){
		this.text = text;
		this.upper_left_corner = e.getCoord();
		color = Color.BLACK;
	}
	public DrawText setColor(Color color){
		this.color = color;
		return this;
	}
	public void draw(Graphics g){
		g.setColor(color);
		g.drawString(text, upper_left_corner.getX(), upper_left_corner.getY());
	}
}

package main;
import hitboxes.Block;

import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public class DrawText {
	private String text;
	private Coord upper_left_corner;
	private Color color;
	/**
	 * Creates a new text description
	 * @param upper_left_corner
	 * The upper left corner of the text
	 * @param text
	 * The text that will be displayed
	 */
	public DrawText(Coord upper_left_corner, String text){
		this.text = text;
		this.upper_left_corner = upper_left_corner;
		color = Color.BLACK;
	}
	/**
	 * Creates a new DrawText without any given text
	 * @param upper_left_corner
	 */
	public DrawText(Coord upper_left_corner){
		this.upper_left_corner = upper_left_corner;
		text = null;
		color = Color.BLACK;
	}
	/**
	 * Links a DrawText with a block so that the text will
	 * be drawn in the middle of the block
	 * @param e
	 * @param text
	 */
	public DrawText(Block b, String text){
		this.text = text;
		this.upper_left_corner = b.getCoord();
		color = Color.BLACK;
	}
	/**
	 * Changes the color of the text
	 * @param color
	 * The desired text-color
	 * @return
	 * The DrawText object after the color is changed
	 */
	public DrawText setColor(Color color){
		this.color = color;
		return this;
	}
	/**
	 * Changes the text of the DrawText object
	 * @param s
	 * The new text
	 * @return
	 */
	public DrawText setText(String s){
		text = s;
		return this;
	}
	/**
	 * Draws the text
	 * @param g
	 */
	public void draw(Graphics g){
		g.setColor(color);
		g.drawString(text, upper_left_corner.X(), upper_left_corner.Y());
	}
	/**
	 * Draws the given text and disregards stored text
	 * @param g
	 * The graphics object that should be drawn upon
	 * @param s
	 * The text that shoud be drawn
	 */
	public void draw(Graphics g, String s){
		g.setColor(color);
		g.drawString(s, upper_left_corner.X(), upper_left_corner.Y());
	}
}

package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public abstract class Block extends Hitbox{
	public static final int WIDTH = 100, HEIGHT = 100, SPEED = 8;
	protected Color color;
	/**
	 * Creates a block of default size
	 * @param c
	 */
	public Block(Coord upper_left_corner){
		super(upper_left_corner,WIDTH,HEIGHT);
	}
	public Block(Coord upper_right_corner, Coord size){
		super(upper_right_corner, size);
	}
	protected Block(Coord c, int width, int height) {
		super(c, width, height);
	}
	/**
	 * Returns the coordinates of the upper
	 * left coorner of the block
	 */
	public Coord getCoord(){
		return c.clone();
	}
	/**
	 * Draws the block
	 */
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), Width(), Height());
		draw_border(g);
	}
	/**
	 * Draws a border around the block
	 * @param g
	 */
	protected void draw_border(Graphics g){
		int thickness = 2;
		g.setColor(Color.BLACK);
		g.fillRect(c.getX()				, c.getY()				, Width()	, thickness	);		//0
		g.fillRect(c.getX() + Width()	, c.getY()				, -thickness, Height()	);		//1
		g.fillRect(c.getX()				, c.getY() + Height()	, Width()	, -thickness);		//2
		g.fillRect(c.getX()				, c.getY()				, thickness	, Height()	);		//3
	}
}

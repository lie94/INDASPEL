package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public abstract class Block extends Hitbox{
	public static final int WIDTH = 100, HEIGHT = 100, SPEED = 4;
	protected Color color;
	/**
	 * Creates a block of default size
	 * @param c
	 */
	public Block(Coord upper_left_corner){
		super(upper_left_corner,new Coord(WIDTH,HEIGHT));
	}
	/**
	 * Creates a block with the a given size
	 * @param upper_right_corner
	 * Describes where the upper left corner of the block is
	 * @param size
	 * Describes the blocks size
	 */
	public Block(Coord upper_right_corner, Coord size){
		super(upper_right_corner, size);
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
		g.fillRect(c.X(), c.Y(), Width(), Height());
		draw_border(g);
	}
	/**
	 * Draws a border around the block
	 * @param g
	 */
	protected void draw_border(Graphics g){
		int thickness = 5;
		g.setColor(Color.BLACK);
		g.fillRect(c.X()						, c.Y()							, Width()	, thickness	);		//0
		g.fillRect(c.X() + Width() - thickness	, c.Y()							, thickness, Height()	);		//1 FEL
		g.fillRect(c.X()						, c.Y() + Height() - thickness	, Width()	, thickness);		//2	FEL
		g.fillRect(c.X()						, c.Y()							, thickness	, Height()	);		//3
	}
	/**
	 * Default update operation
	 */
	public void update() {};
}

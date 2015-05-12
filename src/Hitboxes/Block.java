package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public abstract class Block extends Hitbox{
	protected Block(Coord c, int width, int height) {
		super(c, width, height);
	}
	public static final int WIDTH = 100, HEIGHT = 100;
	protected Color color;
	public Block(Coord c){
		super(c,WIDTH,HEIGHT);
	}
	public Block(Coord upper_right_corner, Coord lower_right_corner){
		super(upper_right_corner, lower_right_corner);
	}
	public Coord getCoord(){
		return c.clone();
	}
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), Width(), Height());
		draw_border(g);
	}
	protected void draw_border(Graphics g){
		int thickness = 5;
		g.setColor(Color.BLACK);
		g.fillRect(c.getX()				, c.getY()				, Width()	, thickness	);		//0
		g.fillRect(c.getX() + Width()	, c.getY()				, -thickness, Height()	);		//1
		g.fillRect(c.getX()				, c.getY() + Height()	, Width()	, -thickness);		//2
		g.fillRect(c.getX()				, c.getY()				, thickness	, Height()	);		//3
	}
}

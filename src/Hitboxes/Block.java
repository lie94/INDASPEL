package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public abstract class Block extends Hitbox{
	protected Block(Coord c, int width, int height) {
		super(c, width, height);
		// TODO Auto-generated constructor stub
	}
	public static final int WIDTH = 100, HEIGHT = 100;
	protected Color color;
	public Block(Coord c){
		super(c,WIDTH,HEIGHT);
	}
	public Coord getCoord(){
		return c.clone();
	}
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), width, height);
	}	
}

package Hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import nav.Coord;
import nav.Vector;


public abstract class Block extends Hitbox{
	public static final int STD_WIDTH = 100, STD_HEIGHT = 100;
	protected Color color;
	public Block(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	public Block(Coord c, int width, int height){
		super(c,width,height);
	}
	public Block(Coord c){
		super(c,STD_WIDTH,STD_HEIGHT);
	}
	public Block(Coord c, Vector dir, int cycle_size){
		super(c,STD_WIDTH,STD_HEIGHT);
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

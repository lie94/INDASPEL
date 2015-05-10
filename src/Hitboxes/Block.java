package Hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import nav.Coord;
import nav.Vector;


public abstract class Block extends Hitbox{
	public static final int STD_WIDTH = 100, STD_HEIGHT = 100;
	private Color color = Color.MAGENTA;
	private Vector v;
	private int times, max_times;
	public Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.v = new Vector();
		max_times = 0;
	}
	public Block(Coord c, int width, int height){
		super(c,width,height);
		this.v = new Vector();
		max_times = 0;
	}
	public Block(Coord c){
		super(c,STD_WIDTH,STD_HEIGHT);
		this.v = new Vector();
		max_times = 0;
	}
	public Block(Coord c, Vector dir, int cycle_size){
		super(c,STD_WIDTH,STD_HEIGHT);
		v = dir;
		max_times = cycle_size;
	}
	public void setColor(Color c){
		color = c;
	}
	public void setDir(Vector v){
		this.v = v;
	}
	
	public void setCycle(int cycle_size){
		max_times = cycle_size;
	}
	public void setDist(int pixel_dist){
		
	}
	public Coord getCoord(){
		return c.clone();
	}
	@Override
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), width, height);
	}
	@Override
	public void update() {
		if(max_times != 0){
			if(times++ % max_times == 0){
				v.antiDir();;
			}
			c.add(v);
		}
	}
	
}

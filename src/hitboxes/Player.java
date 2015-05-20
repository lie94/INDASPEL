package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;
import navigation.Vector;


public class Player extends Hitbox{
	public static final int SPEED = 8, WIDTH = 45, HEIGHT = 45;
	private boolean dead;
	public Player(Coord upper_left_corner, Coord size){
		super(upper_left_corner, size);
	}
	/**
	 * Create a player object with the default size values
	 * @param c
	 * The coordinates of where the player is.
	 */
	public Player(Coord c){
		super(c,new Coord(WIDTH,HEIGHT));
	}
	/**
	 * Moves the player to the coordinate c
	 * @param c
	 * Where the player should be moved
	 */
	public void setCoord(Coord c){
		this.c = c;
	}
	public void increaseX(){
		c.add(SPEED,0);
	}
	public void decreaseX(){
		c.add(-SPEED,0);
	}
	public void increaseY(){
		c.add(0,SPEED);
	}
	public void decreaseY(){
		c.add(0,-SPEED);
	}
	public void setC(Coord c){
		this.c = c;
	}
	public void setX(int x){
		c.setX(x);
	}
	public void setY(int y){
		c.setY(y);
	}
	/**
	 * Moves the player in a direction with the standard speed
	 * @param i
	 * Indicates where the player should be moved.
	 * 0 means up
	 * 1 means right
	 * 2 means down
	 * 3 means left
	 */
	public void move(int i){
		switch(i){
		case 0:
			decreaseY();
			break;
		case 1:
			increaseX();
			break;
		case 2:
			increaseY();
			break;
		case 3:
			decreaseX();
			break;
		}
	}
	/**
	 * Checks if the player is dead
	 * @return
	 */
	public boolean isDead(){
		return dead;
	}
	/**
	 * Change the state of the player
	 * @param b
	 */
	public void setDead(boolean b){
		dead = b;
	}
	/**
	 * Moves the player according to the array
	 * dirs. Check documentation for dirrArrayToVector
	 * @param dirs
	 */
	public void move(boolean[] dirs){
		Vector dv = Vector.dirArrayToVector(dirs);
		c.add(dv);
	}
	/**
	 * Draws the player
	 */
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(X(), Y(), Width(), Height());
		g.setColor(Color.YELLOW);
		int thickness = 10;
		g.fillRect(X() + thickness, Y() + thickness, Width() - 2 * thickness, Height() - 2 * thickness);
	
	}
	/**
	 * Returns a copy of the player
	 */
	public Player clone(){
		return new Player(c,d);
	}
}

package hitboxes;
import java.awt.Color;
import java.awt.Graphics;

import navigation.Coord;


public class Player extends Hitbox{
	public static final int SPEED = 8, WIDTH = 45, HEIGHT = 45;
	public Player(Coord c, int width, int height){
		super(c,width,height);
	}
	public Player(Coord upper_left_corner, Coord size){
		super(upper_left_corner, size);
	}
	/**
	 * Create a player object with the default size values
	 * @param c
	 * The coordinates of where the player is.
	 */
	public Player(Coord c){
		super(c,WIDTH,HEIGHT);
	}
	/**
	 * Moves the player to the coordinate c
	 * @param c
	 * Where the player should be moved
	 */
	public void setCoord(Coord c){
		this.c = c;
	}
	public void increaseX(int i){
		c.add(i,0);
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
	 * Draws the player
	 */
	public void draw(Graphics g){
		int width = Width(), height = Height();
		g.setColor(Color.BLUE);
		g.fillRect(c.getX(), c.getY(), width, height);
		g.setColor(Color.RED);
		g.fillRect(c.getX() + width / 6, c.getY() + height / 6, (width * 2) / 3, (height * 2) / 3);
		g.setColor(Color.BLUE);
		g.fillRect(c.getX() + width / 3, c.getY() + height / 3, width / 3, height / 3);
	}
	/**
	 * Returns a copy of the player
	 */
	public Player clone(){
		return new Player(c,d);
	}
}

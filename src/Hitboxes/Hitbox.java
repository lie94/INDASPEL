package hitboxes;

import java.awt.Graphics;

import navigation.Coord;


public abstract class Hitbox{
	protected Coord c, d; //Upper right corner / Lower left corner
	Hitbox(int x, int y,  int width, int height){
		c = new Coord(x,y);
		d = new Coord(height,width);
	}
	Hitbox(Coord upper_left_corner, Coord lower_right_corner){
		c = upper_left_corner;
		d = lower_right_corner;
	}
	Hitbox(Coord c, int width, int height){
		this.c = c;
		d = new Coord(width,height);
	}
	protected boolean contains(Coord ... coords){
		for(Coord p : coords){
			if(!this.contains(p)){
				return false;
			}
		}
		return true;
	}
	/**
	 * Checks if the point (x,y) is in the area of a
	 * @param x
	 * @param y
	 * @param a
	 * @return
	 */
	public static boolean contains(Coord c, Hitbox h){
		int x = c.getX();
		int y = c.getY();
		if(h.X() <= x && h.Y() <= y && h.Y() + h.Height() >= y && h.X() + h.Width() >= x)
			return true;
		return false;
	}
	/**
	 * Checks if the coord is in "this" hitbox
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(Coord c){
		return contains(c,this);
	}
	/**
	 * Checks if hitbox a and hitbox b share area
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean shareHitbox(Hitbox h1, Hitbox h2){
		if(	h1.X() < h2.X() + h2.Width() 	&& h1.X() + h1.Width() 		> h2.X() &&
			h1.Y() < h2.Y() + h2.Height() 	&& h1.Y() + h1.Height() 	> h2.Y())
			return true;
		return false;
	}
	/**
	 * Checks if the Hitbox of the object that is calling
	 * this function is shared with the area of Hitbox a
	 * @param a
	 * @return
	 */
	public boolean shareHitbox(Hitbox h){
		return shareHitbox(this,h);
	}
	/**
	 * Checks if <italic>this</italic> hitbox
	 * is totaly inside hitbox h
	 * @param h
	 * @return
	 */
	public boolean isIn(Hitbox h){
		Coord [] corners = getCorners();
		for(Coord c : corners){
			if(!h.contains(c)){
				return false;
			}
		}
		return true;
	}
	public Coord getCoord(){
		return c.clone();
	}
	public int Height(){
		return d.getY();
	}
	public int Width(){
		return d.getX();
	}
	public int X(){
		return c.getX();
	}
	public int Y(){
		return c.getY();
	}
	/**
	 * Finds the middle point of the hitbox
	 * @return
	 */
	public Coord getMiddle(){
		return c.add(d.getX() / 2, d.getY() / 2);
	}
	/**
	 * Returns an array containing the four corners of the player
	 * 0: Upper left
	 * 1: upper right
	 * 2: lower right
	 * 3: lower left
	 * @return
	 */
	public Coord[] getCorners(){
		Coord[] corners = new Coord[4];
		corners[0] = new Coord(c);
		corners[1] = new Coord(new Coord(c.getX() + Width()	, c.getY()					));
		corners[2] = new Coord(new Coord(c.getX() + Width()	, c.getY() + Height()		));
		corners[3] = new Coord(new Coord(c.getX()				, c.getY() + Height()	));
		return corners;
	}
	public String toString(){
		return "(" + c + ", " + d + ")";
	}
	public abstract void draw(Graphics g);
}

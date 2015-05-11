package hitboxes;

import navigation.Coord;


public abstract class Hitbox{
	protected Coord c;
	protected int width;
	protected int height;
	Hitbox(int x, int y,  int width, int height){
		c = new Coord(x,y);
		this.height = height;
		this.width = width;
	}
	Hitbox(Coord c, int width, int height){
		this.c = c;
		this.width = width;
		this.height = height;
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
		if(h.getX() <= x && h.getY() <= y && h.getY() + h.getHeight() >= y && h.getX() + h.getWidth() >= x)
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
		if(	h1.getX() < h2.getX() + h2.getWidth() 	&& h1.getX() + h1.getWidth() 	> h2.getX() &&
			h1.getY() < h2.getY() + h2.getHeight() 	&& h1.getY() + h1.getHeight() 	> h2.getY())
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
	public Coord getCoord(){
		return c.clone();
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getX(){
		return c.getX();
	}
	public int getY(){
		return c.getY();
	}
}

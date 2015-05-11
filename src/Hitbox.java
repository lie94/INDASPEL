import java.awt.Graphics;


public abstract class Hitbox {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean visible;
	Hitbox(int x, int y,  int width, int height){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		visible = false;
	}
	Hitbox(int x, int y, int width,int height, boolean visible){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.visible = visible;
		
	}
	/**
	 * Checks if the point (x,y) is in the area of a
	 * @param x
	 * @param y
	 * @param a
	 * @return
	 */
	public static boolean coordInHitbox(int x, int y, Hitbox h){
		if(h.getX() <= x && h.getY() <= y && h.getY() + h.getHeight() >= y && h.getX() + h.getWidth() >= x)
			return true;
		return false;
	}
	public boolean coordInHitbox(int x, int y){
		return coordInHitbox(x,y,this);
	}
	/**
	 * Checks if hitbox a and hitbox b share area
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean shareHitbox(Hitbox h1, Hitbox h2){
		if(	h1.getX() <= h2.getX() + h2.getWidth() 	&& h1.getX() + h1.getWidth() 	>= h2.getX() &&
			h1.getY() <= h2.getY() + h2.getHeight() && h1.getY() + h1.getHeight() 	>= h2.getY())
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
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public String toString(){
		return "(" + x + ", " + y + ")"; 
	}
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	public boolean isVisible(){
		return visible;
	}
	public void draw(Graphics g){}
	public void update(){}
}

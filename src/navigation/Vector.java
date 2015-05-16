package navigation;

import hitboxes.Player;

public class Vector extends Coord{
	/**
	 * Creates a vector with the given coordinates
	 * @param x
	 * X-position
	 * @param y
	 * Y-position
	 */
	public Vector(int x, int y){
		super(x,y);
	}
	/**
	 * Creates an vector with length 0
	 */
	public Vector(){
		super(0,0);
	}
	/**
	 * Creates a vector with the given coordinates
	 * @param x
	 * X-position
	 * @param y
	 * Y-position
	 */
	public Vector(double x, double y){
		super(x,y);
	}
	/**
	 * Creates a vector from the given coordinates
	 * @param c
	 * The coordinate the vector will be 
	 * "pointing" towards
	 */
	public Vector(Coord c){
		super(c.x,c.y);
	}
	/**
	 * Hit a surface and bounce of it.
	 * dir indicates which direction the surface is
	 * @param dir
	 */
	public void hitSurface(int dir){
		switch(dir){
		case 0:
			this.y = -y;
			break;
		case 1:
			x = -x;
			break;
		case 2:
			y = -y;
			break;
		case 3:
			x = -x;
			break;
		}
	}
	/**
	 * Changes the direction of the vector. 
	 * Same as multiplying with -1
	 */
	public void antiDir(){
		x = -x;
		y = -y;
	}
	/**
	 * Returns the length of the vector
	 * @return
	 */
	public double length(){
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	}
	/**
	 * Makes the vector length 1
	 * @return
	 */
	public Vector norm(){
		double length = length();
		if(length == 0)
			return this;
		x /= length;
		y /= length;
		return this;
	}
	/**
	 * Multiplies the vectors length
	 * by the integer i
	 * @param i
     * The number that multiplices the vectors length
	 * @return
	 * The vector itself
	 */
	public Vector multiply(int i){
		return multiply((double) (i));
	}
	/**
	 * Sets the vectors lenght to the specified number i
	 * @param i
	 * The number that the vectors should bee.
	 * @return
	 * The vector itself after the length has been set
	 */
	public Vector setLength(int i){
		return norm().multiply(i);
	}
	/**
	 * Multiplies the vectors length by the
	 * double d
	 * @param d
	 * A double indicating the desired length of the vector
	 * @return
	 * The object itself
	 */
	public Vector multiply(double d){
		x *= d;
		y *= d;
		return this;
	}
	/**
	 * Returns a clone of the vector
	 */
	public Vector clone(){
		return new Vector(x,y);
	}
	/**
	 * Returns the scalar product of the vector v
	 * and the vector that calls this function
	 * @param v
	 * @return
	 */
	public double scalar(Vector v){
		return x * v.X() + y * v.Y();
	}
	/**
	 * Returns a vector of length 1 with the direction specified
	 * in the boolean array
	 * @param b
	 * An array indicating the direction of the vector.
	 * Index True | Direction
	 * 			0 | Up
	 * 			1 | Right
	 * 			2 | Down
	 * 			3 | Left
	 * @return
	 */
	public static Vector dirArrayToVector(boolean[] b) throws IllegalArgumentException{
		if(b.length != 4){
			throw new IllegalArgumentException("The boolean array must have length 4. b = " + b.length);
		}
		Vector v = new Vector();
		if(b[0]){
			v.add(0, -Player.SPEED);
		}
		if(b[1]){
			v.add(Player.SPEED,0);
		}
		if(b[2]){
			v.add(0, Player.SPEED);
		}
		if(b[3]){
			v.add(-Player.SPEED,0);
		}
		
		return v.setLength(Player.SPEED);
	}
}

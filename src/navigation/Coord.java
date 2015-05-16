package navigation;

public class Coord {
	protected double x, y;
	/**
	 * Creates a coordinate point at the given location
	 * @param x
	 * X-coordinate
	 * @param y
	 * Y-coordinate
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Creates a coordinate at the given location
	 * @param x
	 * X-coordinate
	 * @param y
	 * Y-coordinate
	 */
	public Coord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Creates a default coordinate at the top left corner
	 */
	public Coord(){
		x = 0;
		y = 0;
	}
	/**
	 * Adds a vector to the coordinate
	 * @param v
	 * The vector that will change the value of the coordinate
	 * @return
	 * The coordinate itself after the change
	 */
	public Coord add(Vector v){
		this.x += v.x;
		this.y += v.y;
		
		return this;
	}
	/**
	 * Adds the integers to the x and y values
	 * @param x
	 * The x value that should be added
	 * @param y
	 * The y value that should be added
	 * @return
	 * The coordinate itself after the changes
	 */
	public Coord add(int x, int y){
		this.x += x;
		this.y += y;
		return this;
	}
	/**
	 * Changes the x value
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	/**
	 * Changes the y value
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	/**
	 * Returns the x value as an int
	 * @return
	 */
	public int X(){
		return (int) x;
	}
	/**
	 * Returns the y value as an int
	 * @return
	 */
	public int Y(){
		return (int) y;
	}
	/**
	 * Finds the vector spanning from this coord
	 * to the c coord
	 * @param c
	 * @return
	 */
	public Vector sub(Coord c){
		return new Vector(x - c.X(), y - c.Y());
	}
	/**
	 * Finds the vector spanning from this coord
	 * to the given coordinates
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector sub(int x, int y){
		return new Vector(this.x - x, this.y - y);
	}
	/*public Vector sub(double x, double y){
		return new Vector(this.x - x, this.y - y);
	}*/
	/**
	 * Returns a string representation of the coord
	 */
	public String toString(){
		return "(" + (int) x + ", " + (int) y + ")";
	}
	/**
	 * Clones the coord
	 */
	public Coord clone(){
		return new Coord(x,y);
	}
	/**
	 * Checks if two coords are equal with double
	 * precision
	 * @param c
	 * @return
	 */
	public boolean equals(Coord c){
		if(c.x == x && c.y == y){
			return true;
		}
		return false;
	}
	/**
	 * Checks if two coords are equals when the
	 * coords are rounded down.
	 * @param c
	 * @return
	 */
	public boolean intequals(Coord c){
		if((int) c.x == (int) x && (int) c.y == (int) y){
			return true;
		}
		return false;
	}
}

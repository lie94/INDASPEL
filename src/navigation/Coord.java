package navigation;

public class Coord {
	protected double x, y;
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Coord(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Coord(Coord c){
		x = c.getX();
		y = c.getY();
	}
	public Coord(){
		x = 0;
		y = 0;
	}
	public Coord add(Vector v){
		x += v.x;
		y += v.y;
		return clone();
	}
	public Coord add(int x, int y){
		this.x += x;
		this.y += y;
		return this;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}
	public Vector sub(Coord c){
		return new Vector(x - c.getX(), y - c.getY());
	}
	public Vector sub(int x, int y){
		return new Vector(this.x - x, this.y - y);
	}
	public Vector sub(double x, double y){
		return new Vector(this.x - x, this.y - y);
	}
	public String toString(){
		return "(" + (int) x + ", " + (int) y + ")";
	}
	public Coord clone(){
		return new Coord(x,y);
	}
	public boolean equals(Coord c){
		if(c.x == x && c.y == y){
			return true;
		}
		return false;
	}
}

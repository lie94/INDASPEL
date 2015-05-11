package nav;

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
	protected Coord(Coord c){
		x = c.getX();
		y = c.getY();
	}
	public Coord add(Vector v){
		x += v.x;
		y += v.y;
		return clone();
	}
	public void add(int x, int y){
		this.x += x;
		this.y += y;
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
		return new Vector(getX() - c.getX(), getY() - c.getY());
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

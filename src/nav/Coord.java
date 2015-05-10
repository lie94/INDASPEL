package nav;

public class Coord {
	protected int x, y;
	public Coord(int x, int y) {
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
		return x;
	}
	public int getY(){
		return y;
	}
	public String toString(){
		return "(" + x + ", " + y + ")";
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

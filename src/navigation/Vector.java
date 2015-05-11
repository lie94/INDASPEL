package navigation;

public class Vector extends Coord{
	public Vector(int x, int y){
		super(x,y);
	}
	public Vector(){
		super(0,0);
	}
	public Vector(double x, double y){
		super(x,y);
	}
	public Vector(Coord c){
		super(c);
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
	public void antiDir(){
		x = -x;
		y = -y;
	}
	public double length(){
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	}
	public Vector norm(){
		double length = length();
		x /= length;
		y /= length;
		return this;
	}
	public Vector multiply(int i){
		return multiply((double) (i));
	}
	public Vector multiply(double d){
		x *= d;
		y *= d;
		return this;
	}
	public Vector clone(){
		return new Vector(x,y);
	}
}

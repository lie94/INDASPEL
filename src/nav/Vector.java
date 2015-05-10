package nav;

public class Vector extends Coord{
	public Vector(int x, int y){
		super(x,y);
	}
	public Vector(){
		super(0,0);
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
}

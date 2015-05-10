import nav.Coord;
import nav.Vector;
import Hitboxes.Hitbox;
import Hitboxes.Player;
import junit.framework.TestCase;


public class test extends TestCase{
	public void testShareHitbox(){
		Player a = new Player(new Coord(0,0),120,120);
		Player b = new Player(new Coord(60,60),120,120);
		assertTrue(a.coordInHitbox(0, 0));
		assertFalse(a.coordInHitbox(121, 121));
		assertTrue(a.coordInHitbox(0, 120));
		assertFalse(b.coordInHitbox(0, 0));
		
		assertTrue(a.shareHitbox(b));
		assertTrue(Hitbox.shareHitbox(a, b));
		assertFalse(a.shareHitbox(new Player(new Coord(121,121),120,120)));
	}
	public void testMoveChar(){
		Player a = new Player(new Coord(0,0),120,120);
		a.setX(100);
		assertEquals(100,a.getX());
		a.increaseX(10);
		assertEquals(110,a.getX());
	}
	public void testVector(){
		Vector v = new Vector(0,4);
		assertEquals(4.0,v.length());
		Coord c = new Coord(2,7);
		assertTrue(c.add(v).equals(new Coord(2,11)));
		v = new Vector(3,4);
		assertEquals(5.0,v.length());
		v.antiDir();
		assertEquals(v.toString(),"(-3, -4)");
		assertTrue(c.add(v).equals(new Coord(-1,7)));
	}
}

import hitboxes.Hitbox;
import hitboxes.Player;
import navigation.Coord;
import navigation.Vector;
import junit.framework.TestCase;


public class test extends TestCase{
	public void testHitbox(){
		Player a = new Player(new Coord(0,0),120,120);
		Player b = new Player(new Coord(60,60),120,120);
		assertTrue(a.contains(new Coord(0, 0)));
		assertFalse(a.contains(new Coord(121, 121)));
		assertTrue(a.contains(new Coord(0, 120)));
		assertFalse(b.contains(new Coord(0, 0)));
		
		assertTrue(a.shareHitbox(b));
		assertTrue(Hitbox.shareHitbox(a, b));
		assertFalse(a.shareHitbox(new Player(new Coord(121,121),120,120)));
		
	}
	public void testMoveChar(){
		Player a = new Player(new Coord(0,0),120,120);
		a.setCoord(new Coord(100,0));
		assertEquals(100,a.X());
		a.increaseX(10);
		assertEquals(110,a.X());
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
	public void testCoord(){
		Coord c1 = new Coord(2,4);
		Coord c2 = new Coord(4,2);
		assertTrue(new Coord(2.0,-2.0).equals(c2.sub(c1)));
	}
}

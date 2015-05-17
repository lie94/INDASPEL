package main;
import java.io.IOException;

import hitboxes.Hitbox;
import hitboxes.Player;
import hitboxes.safeblocks.SafeBlock;
import navigation.Coord;
import navigation.Vector;
import junit.framework.TestCase;


public class test extends TestCase{
	/**
	 * Test that basic hitbox mechanics are working as intended
	 */
	public void testHitbox(){
		Player a = new Player(new Coord(0,0),new Coord(120,120));
		Player b = new Player(new Coord(60,60),new Coord(120,120));
		assertTrue(a.contains(new Coord(0, 0)));
		assertFalse(a.contains(new Coord(121, 121)));
		assertTrue(a.contains(new Coord(0, 120)));
		assertFalse(b.contains(new Coord(0, 0)));
		
		assertTrue(a.shareHitbox(b));
		assertTrue(Hitbox.shareHitbox(a, b));
		assertFalse(a.shareHitbox(new Player(new Coord(121,121),new Coord(120,120))));
		
		SafeBlock s1 = new SafeBlock(new Coord(0,5), new Coord(4,9));
		SafeBlock s2 = new SafeBlock(new Coord(6,12), new Coord(6,6));

		assertTrue(s1.getMiddle().intequals(new Coord(2,9)));
		assertTrue(s2.getMiddle().intequals(new Coord(9,15)));
	}
	/**
	 * Tests if character movment is working as intended
	 */
	public void testMoveChar(){
		Player a = new Player(new Coord(0,0),new Coord(120,120));
		a.setCoord(new Coord(100,0));
		assertEquals(100,a.X());
		a.move(1);
		assertEquals(100 + Player.SPEED,a.X());
	}
	/**
	 * Tests if vectors are working as intended
	 */
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
		
		Vector v1 = new Vector(0,0);
		Vector v2 = new Vector(1,0);
		
		assertEquals(0,(int) v1.scalar(v2));
		assertEquals(0,(int) v1.length());
		assertTrue(v1.equals(v2.setLength(0)));
	}
	/**
	 * Tests that coordinates are working as intended
	 */
	public void testCoord(){
		Coord c1 = new Coord(2,4);
		Coord c2 = new Coord(4,2);
		assertTrue(new Coord(2.0,-2.0).equals(c2.sub(c1)));
	}
	/**
	 * Tests the MapParser class
	 * @throws IOException 
	 */
	public void testMapParser() throws IOException{
		MapParser.parseMaps();
	}
}

package killblocks;

import nav.Coord;
import nav.Vector;
import Hitboxes.KillBlock;

/**
 * KillBlockP2P (Point to point)
 * A killblock that moves between the two given coordinates
 * @author Felix
 *
 */
public class KillBlockP2P extends KillBlock{
	final Coord start, end; 
	private Coord pos;
	private Vector v;
	int speed, count = 0, max;
	public KillBlockP2P(Coord beginning, Coord end){
		super(beginning);
		start = beginning;
		this.end = end;
		speed = 1;
		pos = beginning;
		v = end.sub(beginning).norm().multiply(speed);
		max = (int) end.sub(beginning).length() / speed;
	}
	public KillBlockP2P(Coord beginning, Coord end, Coord pos){
		super(pos);
		this.start = beginning;
		this.end = end;
		speed = 1;
		pos = beginning;
		v = end.sub(beginning).norm().multiply(speed);
		max = (int) end.sub(beginning).length() / speed;
	}
	public KillBlockP2P setSpeed(int speed){
		this.speed = speed;
		v = end.sub(start).norm().multiply(speed);
		max = (int) end.sub(start).length() / speed;
		return this;
	}
	@Override
	public void update() {
		if(count < max){
			pos.add(v);
			count++;
		}else{
			count = 0;
			v.antiDir();
		}
	}
	
	
}

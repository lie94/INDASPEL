package hitboxes.killblocks;

import java.awt.Color;

import navigation.Coord;
import navigation.Vector;

public class KillBlockCycle extends KillBlockPath {
	public KillBlockCycle(Coord ... path){
		this.path = path;
		c = path[0];
		count = 0;
		index = 0;
		last_index = 0;
		refresh();
	}
	public KillBlockCycle setSpeed(int speed){
		this.speed = speed;
		refresh();
		return this;
	}
	@Override
	public void update() {
		if(path.length == 1){
			return;
		}
		if(count < max[index]){
			c.add(dirs[index]);
			count++;
		}else{
			count = 0;
			index++;
			if(index == max.length){
				index = 0;
			}
		}
	}
	/**
	 * Moves the killbox if it can be moved
	 */
	private void refresh(){
		if(path.length == 1){
			return;
		}
		dirs = new Vector[path.length];
		max = new int[path.length];
		Vector v;
		if(speed != 0){
			for(int i = 0; i < path.length - 1; i++){
				v = path[i + 1].sub(path[i]);
				dirs[i] = (v.clone().norm().multiply(speed));
				max[i] = ((int) v.length() / speed);
			}
			v = path[0].sub(path[path.length - 1]);
			dirs[dirs.length - 1] = v.clone().norm().multiply(speed);
			max[max.length - 1] = (int) (v.length() / speed);
		}
	}
	/**
	 * Changes the color of the killblock.
	 * Default color is Color.RED
	 */
	public KillBlockCycle setColor(Color c){
		color = c;
		return this;
	}
}
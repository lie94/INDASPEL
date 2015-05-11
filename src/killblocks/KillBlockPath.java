package killblocks;

import java.util.ArrayList;
import java.util.Arrays;

import nav.Coord;
import nav.Vector;
import Hitboxes.KillBlock;

public class KillBlockPath extends KillBlock{
	ArrayList<Coord> path;
	ArrayList<Vector> dirs;
	ArrayList<Integer> max;
	int speed, index, last_index, count;
	public KillBlockPath(Coord ... path) throws IllegalArgumentException{
		super(path[0]);
		if(path.length < 2){
			throw new IllegalArgumentException("There must be at least two points in the path");
		}
		init(path);
	}
	private void init(Coord[] path){
		this.path = new ArrayList<Coord>(Arrays.asList(path));
		speed = 8;
		count = 0;
		index = 0;
		last_index = 0;
		refresh();
	}
	/**
	 * Refreshes the path arrayList
	 */
	private void refresh(){
		dirs = new ArrayList<Vector>();
		max = new ArrayList<Integer>();
		Vector v;
		for(int i = 0; i < path.size() - 1; i++){
			v = path.get(i + 1).sub(path.get(i));
			dirs.add(v.clone().norm().multiply(speed));
			max.add((int) v.length() / speed);
		}
	}
	public KillBlockPath setSpeed(int speed){
		this.speed = speed;
		refresh();
		return this;
	}
	@Override
	public void update() {
		if(count < max.get(index)){
			c.add(dirs.get(index));
			count++;
		}else{
			count = 0;
			changeIndex();
		}
	}
	private void changeIndex(){
		if(max.size() == 1){
			flip();
			return;
		}
		if(index == max.size() - 1){
			if(index == last_index){
				index--;
			}else{
				last_index = index;
				flip();
			}
		}else if(index == 0 ){
			if(index == last_index){
				index++;
			}else{
				last_index = index;
				flip();
			}
		}else if(index < last_index){
			last_index = index;
			index--;
		}else{
			last_index = index;
			index++;
		}
	}
	private void flip(){
		for(Vector v : dirs){
			v.antiDir();
		}
	}
}

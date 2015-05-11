package Hitboxes.killblocks;

import java.awt.Color;

import nav.Coord;
import nav.Vector;

public class KillBlockPath extends KillBlock{
	Coord[] path;
	Vector[] dirs;
	int[] max;
	int speed = 8, index, last_index, count;
	public KillBlockPath(Coord ... path){
		super(path[0]);
		init(path);
	}
	protected KillBlockPath(){
		super(new Coord(0,0));
	}
	private void init(Coord[] path){
		this.path = path;
		color = Color.BLACK;
		count = 0;
		index = 0;
		last_index = 0;
		refresh();
	}
	/**
	 * Refreshes the path arrayList
	 */
	private void refresh(){
		if(path.length == 1){
			return;
		}
		dirs = new Vector[path.length - 1];
		max = new int[path.length - 1];
		Vector v;
		if(speed != 0){
			for(int i = 0; i < path.length - 1; i++){
				v = path[i + 1].sub(path[i]);
				dirs[i] = (v.clone().norm().multiply(speed));
				max[i] = ((int) v.length() / speed);
			}
		}
	}
	public KillBlockPath setSpeed(int speed){
		this.speed = speed;
		refresh();
		return this;
	}
	/**
	 * Set pecent of the road traveled.
	 * @param percent
	 * @return
	 * @throws IllegalArgumentException
	 */
	public KillBlockPath setPercent(int percent) throws IllegalArgumentException{
		if(percent < 0 || percent > 100){
			throw new IllegalArgumentException("percent must have a value between 0-100. \n You gave the value: " + percent);
		}else if(path.length == 1 || percent == 0){
			return this;
		}
		double p = (double) percent / 100.0;
		double length_traveled = p * pathLength();
		double temp = 0; 
		int index = 0;
		boolean smaller = true;
		while(smaller){
			if(temp < length_traveled && index != max.length){
				temp += max[index++] * speed;
			}else{
				smaller = false;
			}
		}
		Coord start = path[index - 1];
		Vector v = path[index].sub(start);
		
		p -= (temp - max[index - 1] * speed) / length_traveled; 
		c = start.add(v.multiply(p));
		this.index = index - 1;
		count = (int) (max[index - 1] * p);
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
			changeIndex();
		}
	}
	public KillBlockPath setColor(Color c){
		color = c;
		return this;
	}
	private void changeIndex(){
		if(max.length == 1){
			flip();
			return;
		}
		if(index == max.length - 1){
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
	/**
	 * Returns the total length of the whole path
	 * @return
	 */
	private double pathLength(){
		double res = 0;
		for(int i : max){
			res += i * speed;
		}
		return res;
	}
}

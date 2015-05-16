package hitboxes.safeblocks;

import java.awt.Color;

import navigation.Coord;
import navigation.Vector;

public class SafeBlockPath extends SafeBlock {
	Coord[] path;
	Vector[] dirs;
	int[] max;
	int speed = 8, index, last_index, count;
	/**
	 * Creates a safeblock that will move along the path
	 * @param path
	 * Describes every coordinate in the desired
	 * paths
	 */
	public SafeBlockPath(Coord ... path){
		super(path[0]);
		if(path.length < 2){
			throw new IllegalArgumentException("Paths have to have atleast two coordinates");
		}
		init(path);
	}
	/**
	 * Creates an empty path
	 */
	protected SafeBlockPath(){
		super(new Coord(0,0));
	}
	/**
	 * Changes the siez of the block
	 * @param size
	 * @return
	 */
	public SafeBlockPath setSize(Coord size){
		d = size;
		return this;
	}
	/**
	 * Sets the speed the block should be moving at.
	 * @param speed
	 * @return
	 */
	public SafeBlockPath setSpeed(int speed){
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
	public SafeBlockPath setPercent(int percent) throws IllegalArgumentException{
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
	/**
	 * Moves the block if necessary
	 */
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
	/**
	 * Changes if the player can pass through the block
	 * @param b
	 * @return
	 */
	public SafeBlockPath setPassable(boolean b){
		passable = b;
		return this;
	}
	public Vector getVector(){
		return dirs[index];
	}
	/**
	 * Changes the color of the block
	 * @param c
	 * @return
	 */
	public SafeBlockPath setColor(Color c){
		color = c;
		return this;
	}
	public int points(){
		return path.length;
	}
	/**
	 * Initiates the block
	 * @param path
	 */
	private void init(Coord[] path){
		this.path = path;
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
	/**
	 * Changes which vector should be used
	 */
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
	/**
	 * Flips all vectors in the dirs-array
	 * Used when the block walks back the same path
	 */
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

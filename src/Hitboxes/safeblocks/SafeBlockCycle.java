package hitboxes.safeblocks;

import navigation.Coord;
import navigation.Vector;

public class SafeBlockCycle extends SafeBlockPath{
	public SafeBlockCycle(Coord ... path){
		this.path = path;
		c = path[0];
		count = 0;
		index = 0;
		last_index = 0;
		refresh();
	}
	public SafeBlockCycle setSpeed(int speed){
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
}

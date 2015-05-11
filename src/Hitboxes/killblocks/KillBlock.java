package hitboxes.killblocks;


import hitboxes.Block;
import navigation.Coord;

public abstract class KillBlock extends Block{

	public KillBlock(Coord c) {
		super(c);
	}
	public abstract void update();
}

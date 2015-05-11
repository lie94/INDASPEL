package Hitboxes.killblocks;


import Hitboxes.Block;
import nav.Coord;

public abstract class KillBlock extends Block{

	public KillBlock(Coord c) {
		super(c);
	}
	public abstract void update();
}

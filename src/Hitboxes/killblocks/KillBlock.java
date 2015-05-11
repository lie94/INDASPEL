package Hitboxes.killblocks;


import nav.Coord;
import Hitboxes.Block;
import Hitboxes.killblocks.KillBlock;

public abstract class KillBlock extends Block{

	public KillBlock(Coord c) {
		super(c);
	}
	public abstract void update();
}

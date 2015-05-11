package Hitboxes.safeblocks;

import nav.Coord;
import Hitboxes.Block;

public abstract class SafeBlock extends Block{

	public SafeBlock(Coord c) {
		super(c);
	}
	public abstract void update();
}

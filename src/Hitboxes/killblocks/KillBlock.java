package hitboxes.killblocks;


import java.awt.Color;

import hitboxes.Block;
import navigation.Coord;

public abstract class KillBlock extends Block{

	public KillBlock(Coord c) {
		super(c);
		color = Color.RED;
	}
	public abstract void update();
}

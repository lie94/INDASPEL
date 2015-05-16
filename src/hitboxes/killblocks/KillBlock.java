package hitboxes.killblocks;


import java.awt.Color;

import hitboxes.Block;
import navigation.Coord;

public class KillBlock extends Block{
	/**
	 * Creates a killblock of default size
	 * @param upper_left_corner
	 * Describes where the killblocks upper left corner is
	 */
	public KillBlock(Coord upper_left_corner) {
		super(upper_left_corner);
		color = Color.RED;
	}
	/**
	 * Creates a killblock with a given size
	 */
	public KillBlock(Coord upper_left_corner, Coord size){
		super(upper_left_corner,size);
		color = Color.RED;
	}
}

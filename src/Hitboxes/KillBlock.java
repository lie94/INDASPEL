package Hitboxes;

import nav.Coord;

public class KillBlock extends Block {
	public KillBlock(Coord c){
		super(c);
	}
	public KillBlock(Coord c, int width, int height){
		super(c,width,height);
	}
	public KillBlock(int x, int y, int width, int height){
		super(x,y,width,height);
	}
}

package Hitboxes;


import java.awt.Color;

import nav.Coord;

public abstract class KillBlock extends Block {
	public KillBlock(Coord c){
		super(c);
		init();
	}
	public KillBlock(Coord c, int width, int height){
		super(c,width,height);
		init();
	}
	public KillBlock(int x, int y, int width, int height){
		super(x,y,width,height);
		init();
	}
	private void init(){
		color = Color.BLACK;
	}
}

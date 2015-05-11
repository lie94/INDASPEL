import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import nav.Coord;
import Hitboxes.Player;
import Hitboxes.killblocks.KillBlock;


public class Map {
	public static final int WIDTH = 1280, HEIGHT = 720;
	public BufferedImage background;
	public ArrayList<KillBlock> killblocks;
	private Coord spawn;
	Map(BufferedImage background, Coord spawn){
		this.background = background;
		this.spawn = spawn;
		killblocks = new ArrayList<KillBlock>();
	}
	Map(){}
	public Coord getSpawn(){
		return spawn.clone();
	}
	public void add(KillBlock kb){
		killblocks.add(kb);
	}
	/*
	public void addKB(int dir, int coordinate) throws IllegalArgumentException{
		switch(dir){
		case 0:
			killblocks.add(new KillBlock(-WIDTH,coordinate,2*WIDTH,2*HEIGHT));
			break;
		case 1:
			killblocks.add(new KillBlock(-WIDTH,-HEIGHT,coordinate,2*HEIGHT));
			break;
		case 2:
			killblocks.add(new KillBlock(-WIDTH,-HEIGHT,2*WIDTH,coordinate));
			break;
		case 3:
			killblocks.add(new KillBlock(coordinate,-HEIGHT,2*WIDTH,2*HEIGHT));
			break;
		default:
			throw new IllegalArgumentException("dir must be one of {0,1,2,3}");
		}
	}*/
	public void removeAll(){
		killblocks = new ArrayList<KillBlock>();
	}
	public void draw(Graphics g){
		g.drawImage(background,0,0,WIDTH,HEIGHT,null);
		for(KillBlock kb : killblocks){
			kb.draw(g);
		}
	}
	public boolean isTouchingKB(Player player){
		for(KillBlock kb : killblocks){
			if(kb.shareHitbox(player)){
				return true;
			}
		}
		return false;
	}
}

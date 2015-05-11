import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.safeblocks.SafeBlock;
import hitboxes.safeblocks.SafeBlockPath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import navigation.Coord;


public class Map {
	public static final int WIDTH = 1280, HEIGHT = 720;
	public BufferedImage background;
	public ArrayList<KillBlock> killblocks;
	public ArrayList<SafeBlock> safeblocks;
	private Coord spawn;
	Map(BufferedImage background, Coord spawn){
		this.background = background;
		this.spawn = spawn;
		init();
	}
	private void init(){
		killblocks = new ArrayList<KillBlock>();
		safeblocks = new ArrayList<SafeBlock>();
		safeblocks.add(new SafeBlockPath(Map.WIDTH + 100, 100,	new Coord(-100, 	Map.HEIGHT)	).setColor(Color.BLACK)); //0
		safeblocks.add(new SafeBlockPath(100, Map.HEIGHT + 100,	new Coord(-100,		-100)		).setColor(Color.BLACK)); //1
		safeblocks.add(new SafeBlockPath(Map.WIDTH + 100, 100,	new Coord(-100,		-100)		).setColor(Color.BLACK)); //2
		safeblocks.add(new SafeBlockPath(100, Map.HEIGHT + 100,	new Coord(Map.WIDTH,-100)		).setColor(Color.BLACK)); //3
	}
	Map(){}
	public Coord getSpawn(){
		return spawn.clone();
	}
	public void add(KillBlock kb){
		killblocks.add(kb);
	}
	public void add(SafeBlock sb){
		safeblocks.add(sb);
	}
	/**
	 * Clears all killblocks and safeblocks on the map
	 */
	public void removeAll(){
		init();
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

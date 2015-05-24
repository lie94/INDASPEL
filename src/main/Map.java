package main;
import hitboxes.Exit;
import hitboxes.killblocks.KillBlock;
import hitboxes.safeblocks.SafeBlock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import navigation.Coord;


public class Map {
	public static final int WIDTH = 1280, HEIGHT = 720;
	public BufferedImage background;
	public Color color;
	public ArrayList<KillBlock> killblocks;
	public ArrayList<SafeBlock> safeblocks;
	public ArrayList<Exit> exits;
	private Coord spawn;
	/**
	 * Initiates a map with a background image
	 * @param background
	 * @param spawn
	 */
	Map(BufferedImage background, Coord spawn){
		this.background = background;
		this.spawn = spawn;
		init();
	}
	/**
	 * Initiates a map with a background color
	 * @param color
	 * @param spawn
	 */
	public Map(Color color, Coord spawn) {
		this.color = color;
		this.spawn = spawn;
		init();
	}
	/**
	 * Returns the spawn on the current map
	 * @return
	 */
	public Coord getSpawn(){
		return spawn.clone();
	}
	/**
	 * Adds the killbox kb to the map
	 * @param kb
	 */
	public void add(KillBlock kb){
		killblocks.add(kb);
	}
	/**
	 * Adds the safeblock sb to the map
	 * @param sb
	 */
	public void add(SafeBlock sb){
		safeblocks.add(sb);
	}
	/**
	 * Adds the exit e to the map
	 * @param e
	 */
	public void add(Exit e){
		exits.add(e);
	}
	/**
	 * Returns the center of the map
	 * @return
	 */
	public static Coord getMiddle(){
		return new Coord(WIDTH / 2, HEIGHT / 2);
	}
	/**
	 * Clears all killblocks and safeblocks on the map
	 */
	public Map removeAll(){
		init();
		return this;
	}
	/**
	 * Draws the map
	 * @param g
	 */
	public void draw(Graphics g){
		if(background == null){
			g.setColor(color);
			g.fillRect(5, 5, Map.WIDTH, Map.HEIGHT);
		}else
			g.drawImage(background,0,0,WIDTH,HEIGHT,null);
	}
	/**
	 * Initiates all ArrayLists in the map and
	 * creates walls that the player can't move through
	 * around the map
	 */
	private void init(){
		killblocks = new ArrayList<KillBlock>();
		safeblocks = new ArrayList<SafeBlock>();
		exits = new ArrayList<Exit>();
		int wall_width = 20;
		Coord horizontal_size = new Coord(Map.WIDTH + 2 * wall_width, wall_width);
		Coord vertical_size = new Coord(wall_width,Map.HEIGHT + 2 * wall_width);
		safeblocks.add(new SafeBlock(new Coord(-wall_width	, -wall_width	),	horizontal_size	).setColor(Color.BLACK)); //0
		safeblocks.add(new SafeBlock(new Coord(Map.WIDTH	, -wall_width	),	vertical_size	).setColor(Color.BLACK)); //1
		safeblocks.add(new SafeBlock(new Coord(-wall_width	, Map.HEIGHT	),	horizontal_size	).setColor(Color.BLACK)); //2
		safeblocks.add(new SafeBlock(new Coord(-wall_width	, -wall_width	),	vertical_size	).setColor(Color.BLACK)); //3
	}
}

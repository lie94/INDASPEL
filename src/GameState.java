import hitboxes.Block;
import hitboxes.Exit;
import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.killblocks.KillBlockCycle;
import hitboxes.killblocks.KillBlockPath;
import hitboxes.safeblocks.SafeBlock;
import hitboxes.safeblocks.SafeBlockCycle;
import hitboxes.safeblocks.SafeBlockPath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import navigation.Coord;


public class GameState {
	private Map currentMap;
	private Player player;
	private Map[] maps;
	private boolean[] directions; 	/* Upp 		index 0
									 * H�ger 	index 1
									 * Ner 		index 2
									 * V�nster 	index 3
									 */
	/**
	 * Initiates the backgrounds and spawns of the maps, but not the blocks in the maps
	 */
	GameState(){
		player = new Player(new Coord(0,0),45,45);
		directions = new boolean[4];
		maps = new Map[2];
		try {
			maps[0] = new Map(Color.WHITE														,Map.getMiddle()	);
			resetToMap(0);
			maps[1] = new Map(ImageIO.read(this.getClass().getResource("/res/images/bg1.jpg"))	,new Coord(0,0)		);
			resetToMap(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Starting map
		currentMap = maps[1];
		killPlayer();
		
		
	}
	/**
	 * Draws everything on that is happening on the current map
	 * @param g
	 */
	public void draw(Graphics g){
		currentMap.draw(g);
		for(Exit e : currentMap.exits){
			e.draw(g);
		}
		player.draw(g);
		for(KillBlock kb : currentMap.killblocks){
			kb.draw(g);
		}
		for(SafeBlock sb : currentMap.safeblocks){
			sb.draw(g);
		}
		
		
	}
	/**
	 * Updates everything that is happening on the current map
	 */
	public boolean update(){
		for(KillBlock kb : currentMap.killblocks){
			kb.update();
			if(kb.shareHitbox(player)){
				killPlayer();
			}
		}
		for(int i = 0; i < 4; i++){
			if(directions[i]){
				player.move(i);
			}
		}
		for(SafeBlock sb : currentMap.safeblocks){
			sb.update();
			if(sb.playerColiding(player))
				killPlayer();
		}
		for(Exit e : currentMap.exits){
			if(player.isIn(e)){
				int code = e.getExitCode();
				if(code == -1){
					return false;
				}else{
					currentMap = maps[code];
					killPlayer();
				}
			}
		}
		return true;
	}
	/**
	 * Recives a keyevent and translates it to how the player should be moved
	 * @param e
	 * @param keyPress
	 */
	public void send(KeyEvent e, boolean keyPress){
		if(keyPress){
			switch(e.getKeyCode()){
			case 38:
				directions[0] = true;
				break;
			case 39:
				directions[1] = true;
				break;
			case 40:
				directions[2] = true;
				break;
			case 37:
				directions[3] = true;
				break;
			default:
			}	
		}else{
			switch(e.getKeyCode()){
			case 38:
				directions[0] = false;
				break;
			case 39:
				directions[1] = false;
				break;
			case 40:
				directions[2] = false;
				break;
			case 37:
				directions[3] = false;
				break;
			default:
			}	
		}
	}
	/**
	 * Resets/Initiates the map with index i
	 * @param i
	 * @return 
	 */
	private void resetToMap(int i){
		Map temp = maps[i].removeAll();
		switch(i){
		case 0:
			temp.add(new Exit(new Coord(7 * Map.WIDTH / 10,180), new Coord(2 * Map.WIDTH / 10, 360), 1));
			temp.add(new Exit(new Coord(1 * Map.WIDTH/10,180), new Coord(2 * Map.WIDTH / 10, 360), -1));
			break;
		case 1:
			 
			// Safeblocks
			int y = 720 / 3;
			int dx = 1280 / 5;
			temp.add(new SafeBlock(new Coord(Map.getMiddle())));
			temp.add(new SafeBlockPath(	new Coord(dx - Block.WIDTH					, y	- Block.WIDTH / 2			)));
			temp.add(new SafeBlockPath(	new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new SafeBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)));
			// Killblocks
			y = 2 * y;
			temp.add(new KillBlockPath(	new Coord(dx - Block.WIDTH					, y	- Block.WIDTH / 2			)));
			temp.add(new KillBlockPath(	new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new KillBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)));
			break;
		}
	}
	/**
	 * Kills the player and resets the current map
	 */
	private void killPlayer(){
		player.setC(currentMap.getSpawn());
		resetToMap(Arrays.asList(maps).indexOf(currentMap));
	}
}

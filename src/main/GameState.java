package main;
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
import java.util.Random;

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
		maps = new Map[3];
		try {
			//MENUTEST
			maps[0] = new Map(Color.WHITE														,Map.getMiddle()	);
			resetToMap(0);
			//MECHANICSTEST
			maps[1] = new Map(ImageIO.read(this.getClass().getResource("/res/images/bg1.jpg"))	,new Coord(0,0)		);
			resetToMap(1);
			//STRESSTEST
			maps[2] = new Map(Color.GRAY		 												,new Coord(0,0)		);
			resetToMap(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Starting map
		currentMap = maps[0];
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
		for(int i = 0; i < 4; i++){
			if(directions[i]){
				player.move(i);
			}
		}
		for(KillBlock kb : currentMap.killblocks){
			kb.update();
			if(kb.shareHitbox(player)){
				killPlayer();
			}
		}
		for(SafeBlock sb : currentMap.safeblocks){
			sb.update();
		}
		boolean move = false, done = false;
		for(SafeBlock sb : currentMap.safeblocks){
			if(sb.playerColiding(player) && (sb instanceof SafeBlockPath || sb instanceof SafeBlockCycle)){
				move = true;
				if(!done)
					done = true;
				else
					killPlayer();
			}else if(sb.shareHitbox(player)){
				if(!done){
					done = true;
				}else if(move)
					killPlayer();
			}
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
			Coord size = new Coord(new Coord(2 * Map.WIDTH / 10, 360));
			temp.add(new Exit(new Coord(7 * Map.WIDTH / 10,180), size, 1).setText("Start Game"));
			temp.add(new Exit(new Coord(1 * Map.WIDTH/10,180), size, -1).setText("Exit Game"));
			break;
		case 1:
			 
			// Safeblocks
			int y = 720 / 3;
			int dx = 1280 / 5;
			
			temp.add(new SafeBlockPath(		new Coord(dx - Block.WIDTH					, y	- Block.WIDTH / 2			)));
			temp.add(new SafeBlockPath(		new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new SafeBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)));
			// Killblocks
			y = 2 * y;
			temp.add(new KillBlock(	new Coord(dx - Block.WIDTH						, y	- Block.WIDTH / 2			)));
			temp.add(new KillBlockPath(	new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new KillBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)));
			temp.add(new SafeBlock(new Coord(Map.getMiddle())));
			//Exits
			temp.add(new Exit(new Coord(Map.WIDTH - Block.WIDTH, Map.HEIGHT / 2 - Block.HEIGHT / 2), 2));
			break;
		case 2:
			int size1 = 10000; //Dubbla denna siffran blir block-antalet
								// 20 000 : 60 fps
								// 25 000 : 50 fps
								// 50 000 : 24 fps
								//100 000 : 12 fps
								//200 000 : 6 fps
			Random rn = new Random();
			//SB
			int minx = Map.WIDTH / 2;
			int maxx = Map.WIDTH;
			int miny = 0;
			int maxy = Map.HEIGHT;
			
			for(int j = 0; j < size1; j++){
				temp.add(new SafeBlockPath(new Coord(minx + rn.nextInt(maxx - minx), miny + rn.nextInt(maxy - miny)),new Coord(minx + rn.nextInt(maxx - minx), miny + rn.nextInt(maxy - miny) )));
			}
			//KB
			minx = 0;
			maxx = Map.WIDTH;
			miny = Map.HEIGHT / 2;
			maxy = Map.HEIGHT;
			for(int j = 0; j < size1; j++){
				temp.add(new KillBlockPath(new Coord(minx + rn.nextInt(maxx - minx), miny + rn.nextInt(maxy - miny)),new Coord(minx + rn.nextInt(maxx - minx), miny + rn.nextInt(maxy - miny) )));
			}
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

package main;

import hitboxes.Exit;
import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.safeblocks.SafeBlock;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import navigation.Coord;


public class GameState implements KeyListener {
	private Map currentMap;
	private int currentMapIndex;
	private Player player;
	private DrawText death;
	private boolean[] directions; 	/* Upp 		index 0
									 * H�ger 	index 1
									 * Ner 		index 2
									 * V�nster 	index 3
									 */
	/**
	 * Initiates the backgrounds and spawns of the maps, but not the blocks in the maps
	 */
	GameState(Run r){
		player = new Player(new Coord(0,0),new Coord(45,45));
		directions = new boolean[4];
		//FPS counter at top right corner
		//text = new DrawText(new Coord(0,10));
		death = new DrawText(Map.getMiddle().add(-Map.WIDTH / 3,0)).setText("You have died. \n Press space to continue").setFont(new Font("TimeRoman",Font.PLAIN,50));
		try {
			currentMap = MapParser.parseMap(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentMapIndex = 0;
		respawn();
		player.setDead(false);
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
		
		for(SafeBlock sb : currentMap.safeblocks){
			sb.draw(g);
		}
		for(KillBlock kb : currentMap.killblocks){
			kb.draw(g);
		}
		//text.draw(g,"" + r.fps);
		if(player.isDead()){
			death.draw(g);
		}
			
	}
	/**
	 * Updates everything that is happening on the current map
	 */
	public boolean update(){
		if(!player.isDead()){	
			player.move(directions);
			for(KillBlock kb : currentMap.killblocks){
				kb.update();
				if(kb.shareHitbox(player)){
					player.setDead(true);
					return true;
				}
			}
			for(SafeBlock sb : currentMap.safeblocks){
				sb.update();
				sb.playerColiding(player);
			}
			for(SafeBlock sb : currentMap.safeblocks){
				if(sb.shareHitbox(player)){
					player.setDead(true);
					return true;
				}
			}
			for(Exit e : currentMap.exits){
				if(player.isIn(e)){
					int code = e.getExitCode();
					if(code == -1){
						return false;
					}else{
						currentMapIndex = code;
						respawn();
						return true;
					}
				}
			}
		}
		return true;
	}
	/**
	 * Resets/Initiates the map with index i
	 * @param i
	 * @return 
	 */
	/*private void resetMap(int i){
		if(i < 1){
			try {
				maps[i] = MapParser.parseMap(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			return;
		}
		//switch(i){
			/*Coord size = new Coord(2 * Map.WIDTH / 10, 360);
			temp.add(new Exit(new Coord(7 * Map.WIDTH / 10,180), size, 1).setText("Start Game"));
			temp.add(new Exit(new Coord(1 * Map.WIDTH/10,180), size, -1).setText("Exit Game"));*/
		/*case 1:
			 
			// Safeblocks
			int y = 720 / 3;
			int dx = 1280 / 5;
			temp.add(new SafeBlock(		new Coord(dx - Block.WIDTH					, y	- Block.WIDTH / 2			)));
			temp.add(new SafeBlockPath(		new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new SafeBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)).setSpeed(2 * Block.SPEED));
			// Killblocks
			y = 2 * y;
			temp.add(new KillBlock(	new Coord(dx - Block.WIDTH						, y	- Block.WIDTH / 2			)));
			temp.add(new KillBlockPath(	new Coord(dx								, y - Block.WIDTH / 2			), 
											new Coord(2 * dx - Block.WIDTH / 2			, y - Block.WIDTH / 2			)));
			temp.add(new KillBlockCycle(	new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y - Block.HEIGHT * (3 / 2)	),
											new Coord(dx * 3 + Block.WIDTH / 2			, y + Block.HEIGHT / 2			),
											new Coord(dx * 3 - (Block.WIDTH * 3) / 2	, y + Block.HEIGHT / 2			)));
			temp.add(new SafeBlock(Map.getMiddle()));
			//Exits
			temp.add(new Exit(new Coord(Map.WIDTH - Block.WIDTH, Map.HEIGHT / 2 - Block.HEIGHT / 2), 2));
			break;
		case 2:
			int size1 = 5000; //Dubbla denna siffran blir block-antalet
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
	}*/
	/**
	 * Kills the player and resets the current map
	 */
	private void respawn(){
		try {
			currentMap = MapParser.parseMap(currentMapIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.setC(currentMap.getSpawn());

		player.setDead(false);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
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
		case 32: //SPACE
			player.setDead(false);
			respawn();																	
			break;
		default:
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
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
	@Override 
	public void keyTyped(KeyEvent arg0) {}
}

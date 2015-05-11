import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.killblocks.KillBlockCycle;
import hitboxes.killblocks.KillBlockPath;
import hitboxes.safeblocks.SafeBlock;
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
	GameState(){
		player = new Player(new Coord(0,0),45,45);
		directions = new boolean[4];
		maps = new Map[1];
		try {
			maps[0] = new Map(ImageIO.read(this.getClass().getResource("/res/images/bg1.jpg")),new Coord(0,0));
			resetToMap(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentMap = maps[0];
		
		
	}
	/**
	 * Draws everything on that is happening on the current map
	 * @param g
	 */
	public void draw(Graphics g){
		currentMap.draw(g);
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
	public void update(){
		if(currentMap.isTouchingKB(player)){
			resetToMap(Arrays.asList(maps).indexOf(currentMap));
		}
		for(KillBlock kb : currentMap.killblocks){
			kb.update();
		}
		for(int i = 0; i < 4; i++){
			if(directions[i]){
				player.move(i);
			}
		}
		for(SafeBlock sb : currentMap.safeblocks){
			sb.update();
			sb.playerColiding(player);
		}
	}
	/**
	 * Recives a keyevent and translates it to how the player should be moved
	 * @param e
	 * @param keyPress
	 */
	public void send(KeyEvent e, boolean keyPress){
		//System.out.println(e.getKeyCode());
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
	 */
	private void resetToMap(int i){
		player.setC(maps[i].getSpawn());
		maps[i].removeAll();
		switch(i){
		case 0:
			// Safeblocks
			maps[0].add(new SafeBlockPath(new Coord(300,300), new Coord(1000,600)));
			// Killblocks
			/*maps[0].add(new KillBlockCycle(new Coord(250,250), new Coord(500,250), new Coord(500,500), new Coord(250,500)).setColor(Color.CYAN).setSpeed(4));
			maps[0].add(new KillBlockPath(new Coord(500,500)));
			maps[0].add(new KillBlockPath(new Coord(320,180), new Coord(960,540), new Coord(960,180),  new Coord(320,540)));
			maps[0].add(new KillBlockPath(new Coord(320,0), new Coord(640,Map.HEIGHT - KillBlock.STD_HEIGHT)).setPercent(50).setColor(Color.BLUE));
			maps[0].add(new KillBlockPath(new Coord(960,0), new Coord(640,Map.HEIGHT - KillBlock.STD_HEIGHT)));
			maps[0].add(new KillBlockPath(new Coord(0,360 + KillBlock.STD_HEIGHT / 2), new Coord(1280 - KillBlock.STD_WIDTH,360 + KillBlock.STD_HEIGHT)).setPercent(90).setColor(Color.MAGENTA));*/
			break;
		}
	}
}

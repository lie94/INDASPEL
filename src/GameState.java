import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import killblocks.KillBlockPath;
import nav.Coord;
import Hitboxes.KillBlock;
import Hitboxes.Player;


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
	public void draw(Graphics g){
		currentMap.draw(g);
		for(KillBlock kb : currentMap.killblocks){
			kb.draw(g);
		}
		player.draw(g);
	}
	public void update(){
		if(currentMap.isTouchingKB(player)){
			resetToMap(Arrays.asList(maps).indexOf(currentMap));
		}
		// TODO UPDATE THE ENVIROMENT
		for(KillBlock kb : currentMap.killblocks){
			kb.update();
		}
		for(int i = 0; i < 4; i++){
			if(directions[i]){
				player.move(i);
			}
		}
		player.update();
		
	}
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
	private void resetToMap(int i){
		player.setC(maps[i].getSpawn());
		maps[i].removeAll();
		switch(i){
		case 0:
			maps[0].add(new KillBlockPath(new Coord(320,180), new Coord(960,540), new Coord(960,180),  new Coord(320,540)));
			maps[0].add(new KillBlockPath(new Coord(320,0), new Coord(640,Map.HEIGHT - KillBlock.STD_HEIGHT)));
			maps[0].add(new KillBlockPath(new Coord(960,0), new Coord(640,Map.HEIGHT - KillBlock.STD_HEIGHT)));
			maps[0].add(new KillBlockPath(new Coord(0,360 + KillBlock.STD_HEIGHT / 2), new Coord(1280 - KillBlock.STD_WIDTH,360 + KillBlock.STD_HEIGHT)));
			break;
		}
	}
}

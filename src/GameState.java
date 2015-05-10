import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import nav.Coord;
import nav.Vector;
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
			KillBlock temp = new KillBlock(new Coord(500,500));
			temp.setCycle(100);
			temp.setDir(new Vector(1,3));
			maps[0].add(temp);
			/*temp = new Block(700,300,100,100);
			temp.setMoving(true);
			maps[0].add(temp);
			temp = new Block(900,700,100,100);
			temp.setMoving(true);
			maps[0].add(temp);
			temp = new Block(300,100,100,100);
			temp.setMoving(true);
			maps[0].add(temp);*/
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
			player.setC(currentMap.getSpawn());
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
}

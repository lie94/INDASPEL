package main;

import hitboxes.Exit;
import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.safeblocks.SafeBlock;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import navigation.Coord;


public class GameState implements KeyListener {
	private Map currentMap;
	private int currentMapIndex;
	private Player player;
	private BufferedImage death;
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
		try {
			death = ImageIO.read(getClass().getResourceAsStream("/res/images/death.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			currentMap = MapParser.parseMap(this,0);
		} catch (IOException e) {
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
			g.drawImage(death, 0, 0, null);
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
		}else{
			for(SafeBlock sb : currentMap.safeblocks){
				sb.playerColiding(player);
			}
		}
		return true;
	}
	/**
	 * Kills the player and resets the current map
	 */
	private void respawn(){
		try {
			currentMap = MapParser.parseMap(this,currentMapIndex);
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
			if(player.isDead()){
				player.setDead(false);
				respawn();
			}															
			break;
		case 82: //r
			currentMapIndex = 0;
			respawn();
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

package Hitboxes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nav.Coord;


public class Player extends Hitbox{
	public static final int SPEED = 8;
	private static BufferedImage[] sprites;
	private static boolean hasInit = false;
	public Player(Coord c, int width, int height){
		super(c,width,height);
		if(!hasInit){
			init();
		}
	}
	private void init(){
		sprites = new BufferedImage[1];
		try {
			sprites[0] = ImageIO.read(this.getClass().getResource("/res/images/sprite.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		hasInit = true;
	}
	public void setX(int x){
		c.setX(x);
	}
	public void setY(int y){
		c.setY(y);
	}
	public void increaseX(){
		c.add(SPEED,0);
	}
	public void increaseX(int i){
		c.add(i,0);
	}
	public void decreaseX(){
		c.add(-SPEED,0);
	}
	public void increaseY(){
		c.add(0,SPEED);
	}
	public void decreaseY(){
		c.add(0,-SPEED);
	}
	public void setC(Coord c){
		this.c = c;
	}
	public void move(int i){
		switch(i){
		case 0:
			decreaseY();
			break;
		case 1:
			increaseX();
			break;
		case 2:
			increaseY();
			break;
		case 3:
			decreaseX();
			break;
		}
	}
	public void draw(Graphics g){
		// TODO M�la olika bilder beroende p� vilken typ av animation ska g�ras
		//g.drawImage(sprites[0],x,y,width,height,null);
		g.setColor(Color.BLUE);
		g.fillRect(c.getX(), c.getY(), width, height);
		g.setColor(Color.RED);
		g.fillRect(c.getX() + width / 6, c.getY() + height / 6, (width * 2) / 3, (height * 2) / 3);
		g.setColor(Color.BLUE);
		g.fillRect(c.getX() + width / 3, c.getY() + height / 3, width / 3, height / 3);
	}
	public void update(){
		
	}
	public Player clone(){
		return new Player(c,width,height);
	}
}

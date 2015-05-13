package hitboxes.safeblocks;

import java.awt.Color;

import hitboxes.Block;
import hitboxes.Player;
import navigation.Coord;

public class SafeBlock extends Block{
	protected boolean passable;
	public SafeBlock(Coord c) {
		super(c);
		passable = false;
		color = Color.GREEN;
	}
	public SafeBlock(int width, int height, Coord c) {
		super(c,width,height);
		passable = false;
	}
	public SafeBlock(Coord upper_left_corner, Coord lower_right_corner){
		super(upper_left_corner, lower_right_corner);
		passable = false;
	}
	public boolean getPassable(){
		return passable;
	}
	public void update(){}
	/** 
	 * Checks if the player has collided with the block, and moves the player if so is
	 * the case.
	 * @param player
	 */
	public boolean playerColiding(Player player){
		// TODO FIX GETTING TRAPPED BETWEEN OBJECTS does not work if you are pressed ex. up left into a corner or 
		int width = Width(), height = Height();
		if(!passable && shareHitbox(player)){
			double length1, length2;
			Coord[] corners = player.getCorners();
			if(contains(corners[0])){
				length1 = corners[0].sub(c.getX() + width	, corners[0].getY()	).length();
				length2 = corners[0].sub(corners[0].getX()	, c.getY() + height	).length();
				if(length1 > length2){
					player.setY(c.getY() + height			);
				}else{
					player.setX(c.getX() + width			);
				}
			}else if(contains(corners[1])){
				length1 = corners[1].sub(c.getX()			, corners[1].getY()	).length();
				length2 = corners[1].sub(corners[1].getX()	, c.getY() + height	).length();
				if(length1 > length2){
					player.setY(c.getY() + height			);
				}else{
					player.setX(c.getX()  - player.Width()	);
				}
			}else if(contains(corners[2])){
				length1 = corners[2].sub(c.getX()			, corners[2].getY()	).length();
				length2 = corners[2].sub(corners[2].getX()	, c.getY()			).length();
				if(length1 > length2){
					player.setY(c.getY() - player.Height()	);
				}else{
					player.setX(c.getX()  - player.Width()	);
				}
			}else if(contains(corners[3])){
				length1 = corners[3].sub(c.getX() + width	, corners[3].getY()	).length();
				length2 = corners[3].sub(corners[3].getX()	, c.getY()			).length();
				if(length1 > length2){
					player.setY(c.getY() - player.Height()	);
				}else{
					player.setX(c.getX()  + width			);
				}
			}
			return true;
		}
		return false;
	}
	public SafeBlock setColor(Color c){
		color = c;
		return this;
	}
}

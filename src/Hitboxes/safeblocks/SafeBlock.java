package hitboxes.safeblocks;

import hitboxes.Block;
import hitboxes.Player;
import navigation.Coord;

public abstract class SafeBlock extends Block{
	protected boolean passable;
	public SafeBlock(Coord c) {
		super(c);
		passable = false;
	}
	public SafeBlock(int width, int height, Coord c) {
		super(c,width,height);
		passable = false;
	}
	public boolean getPassable(){
		return passable;
	}
	public abstract void update();
	/** 
	 * Checks if the player has collided with the block, and moves the player if so is
	 * the case.
	 * @param player
	 */
	public void playerColiding(Player player){
		if(!passable && player.shareHitbox(this)){
			double length1, length2;
			Coord[] corners = player.getCorners();
			if(contains(corners[0])){
				length1 = corners[0].sub(c.getX() + width	, corners[0].getY()	).length();
				length2 = corners[0].sub(corners[0].getX()	, c.getY() + height	).length();
				if(length1 > length2){
					player.setY(c.getY() + height);
				}else{
					player.setX(c.getX() + width);
				}
			}else if(contains(corners[1])){
				length1 = corners[1].sub(c.getX()			, corners[1].getY()	).length();
				length2 = corners[1].sub(corners[1].getX()	, c.getY() + height	).length();
				if(length1 > length2){
					player.setY(c.getY() + height);
				}else{
					player.setX(c.getX()  - player.getWidth());
				}
			}else if(contains(corners[2])){
				length1 = corners[2].sub(c.getX()			, corners[2].getY()	).length();
				length2 = corners[2].sub(corners[2].getX()	, c.getY()			).length();
				if(length1 > length2){
					player.setY(c.getY() - player.getHeight());
				}else{
					player.setX(c.getX()  - player.getWidth());
				}
			}else if(contains(corners[3])){
					length1 = corners[3].sub(c.getX() + width	, corners[3].getY()	).length();
					length2 = corners[3].sub(corners[3].getX()	, c.getY()			).length();
					if(length1 > length2){
						player.setY(c.getY() - player.getHeight());
					}else{
						player.setX(c.getX()  + width);
					}
			}
			
		}
	}
}

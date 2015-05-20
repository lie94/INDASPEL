package hitboxes.safeblocks;

import java.awt.Color;

import hitboxes.Block;
import hitboxes.Player;
import navigation.Coord;

public class SafeBlock extends Block{
	protected boolean passable;
	/**
	 * Creates a safeblock with default size
	 * @param upper_left_corner
	 * Describes where the upper left corner
	 * of the safeblock is.
	 */
	public SafeBlock(Coord upper_left_corner) {
		super(upper_left_corner);
		passable = false;
		color = Color.BLACK;
	}
	/**
	 * Creates a safeblock with the given size
	 * @param upper_left_corner
	 * Describes the upper left corner of the block
	 * @param size
	 * Describes the blocks size
	 */
	public SafeBlock(Coord upper_left_corner, Coord size){
		super(upper_left_corner, size);
		passable = false;
	}
	/**
	 * Checks if the player can pass through the block
	 * @return
	 */
	public boolean getPassable(){
		return passable;
	}
	/** 
	 * Checks if the player has collided with the block, and moves the player if so is
	 * the case.
	 * @param player
	 */
	public boolean playerColiding(Player player){
		if(!passable && shareHitbox(player)){
			double length1, length2;
			Coord[] corners = player.getCorners();
			if(contains(corners[0])){
				length1 = corners[0].sub(c.X() + Width()	, corners[0].Y()	).length();
				length2 = corners[0].sub(corners[0].X()	, c.Y() + Height()	).length();
				if(length1 > length2){
					player.setY(c.Y() + Height()	);
				}else{
					player.setX(c.X() + Width()		);
				}
			}else if(contains(corners[1])){
				length1 = corners[1].sub(c.X()			, corners[1].Y()	).length();
				length2 = corners[1].sub(corners[1].X()	, c.Y() + Height()	).length();
				if(length1 > length2){
					player.setY(c.Y() + Height()			);
				}else{
					player.setX(c.X()  - player.Width()	);
				}
			}else if(contains(corners[2])){
				length1 = corners[2].sub(c.X()			, corners[2].Y()	).length();
				length2 = corners[2].sub(corners[2].X()	, c.Y()			).length();
				if(length1 > length2){
					player.setY(c.Y() - player.Height()	);
				}else{
					player.setX(c.X()  - player.Width()	);
				}
			}else if(contains(corners[3])){
				length1 = corners[3].sub(c.X() + Width()	, corners[3].Y()	).length();
				length2 = corners[3].sub(corners[3].X()	, c.Y()			).length();
				if(length1 > length2){
					player.setY(c.Y() - player.Height()	);
				}else{
					player.setX(c.X()  + Width()			);
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * Changes the color of the block
	 * @param c
	 * The desired color
	 * @return'
	 * The block itself after the change
	 */
	public SafeBlock setColor(Color c){
		color = c;
		return this;
	}
}

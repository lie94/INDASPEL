package main;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hitboxes.Block;
import hitboxes.Exit;
import hitboxes.killblocks.KillBlock;
import hitboxes.killblocks.KillBlockCycle;
import hitboxes.killblocks.KillBlockPath;
import hitboxes.safeblocks.SafeBlock;
import hitboxes.safeblocks.SafeBlockCycle;
import hitboxes.safeblocks.SafeBlockPath;
import navigation.Coord;

public class MapParser {
	public static Map parseMap(final int i) throws IOException {
		Map temp = null;
		switch(i){
		case 0:
			temp = new Map(ImageIO.read(new File("src/res/images/bg0.jpg")),new Coord((Map.WIDTH - Block.WIDTH) / 2, (Map.HEIGHT - Block.WIDTH) / 2));
			temp.add(new Exit(	
					new Coord(976,0), 							//ULC
					new Coord(1280 - 976,720),					//SIZE 
					1).setVisible(false));
			temp.add(new Exit(	
					new Coord(0,0), 
					new Coord(302,720), 
					-1).setVisible(false));
			break;
		case 1:
			temp = new Map(ImageIO.read(new File("src/res/images/bg1.jpg"))	,new Coord(0,(Map.HEIGHT - Block.HEIGHT) / 2)		);
			//SAFEBLOCKS
			Coord sizeSB = new Coord(Map.WIDTH - (Map.WIDTH) / 12, (720 - 200) / 2);
			temp.add(new SafeBlock(				//Upper black box
					new Coord(Map.WIDTH / 6,0),
					sizeSB).setColor(Color.BLACK));
			temp.add(new SafeBlock(
					new Coord(Map.WIDTH / 6,460),
					sizeSB).setColor(Color.BLACK)); //Lower black box
			//SBC
			temp.add(new SafeBlockCycle(
					new Coord(Map.WIDTH / 6	- 10					,sizeSB.Y() + Block.HEIGHT),
					new Coord((Map.WIDTH * 11) / 12 - Block.WIDTH	,sizeSB.Y() + Block.HEIGHT),
					new Coord((Map.WIDTH * 11) / 12 - Block.WIDTH	,sizeSB.Y() - 10),
					new Coord(Map.WIDTH / 6	- 10					,sizeSB.Y() - 10)
					).setPercent(50));
			//KILLBLOCKS
			Coord sizeKB = new Coord(Map.WIDTH / 6, Block.HEIGHT);
			temp.add(new KillBlockPath(
					new Coord(Map.WIDTH / 6,sizeSB.Y() / 2 - Block.HEIGHT / 2),
					new Coord(Map.WIDTH / 6,Map.HEIGHT - (sizeSB.Y() / 2 - Block.HEIGHT / 2))
					).setSize(sizeKB));
			temp.add(new KillBlockPath(
					new Coord((Map.WIDTH * 3) / 6 - Block.WIDTH		,sizeSB.Y() / 2 - Block.HEIGHT / 2),
					new Coord(Map.WIDTH / 2 - Block.WIDTH			,Map.HEIGHT - (sizeSB.Y() / 2 - Block.HEIGHT / 2) )));
			temp.add(new KillBlockPath(
					new Coord(Map.WIDTH / 2							,sizeSB.Y() / 2 - Block.HEIGHT / 2),
					new Coord(Map.WIDTH / 2							,Map.HEIGHT - (sizeSB.Y() / 2 - Block.HEIGHT / 2))
					).setSize(sizeKB).setPercent(75));
			temp.add(new KillBlockPath(
					new Coord((Map.WIDTH * 5 / 6) - Block.WIDTH		,sizeSB.Y() / 2 - Block.HEIGHT / 2),
					new Coord((Map.WIDTH * 5 / 6) - Block.WIDTH		,Map.HEIGHT - (sizeSB.Y() / 2 - Block.HEIGHT / 2)
					)).setPercent(75));
			//EXITS
			temp.add(new Exit(
					new Coord((Map.WIDTH * 11)/ 12,sizeSB.Y()),
					new Coord(Map.WIDTH / 6, 200),
					2
					));
			break;
		case 2:
			temp = new Map(ImageIO.read(new File("src/res/images/sprite.jpg"))	,new Coord(0,(Map.HEIGHT - Block.HEIGHT) / 2));
			//EXITS
			temp.add(new Exit(
					new Coord((Map.WIDTH * 11)/ 12,720 / 3),
					new Coord(Map.WIDTH / 6, 200),
					0
					));
			break;
		}
		return temp;
	}
}

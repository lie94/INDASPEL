package main;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

import hitboxes.Block;
import hitboxes.Exit;
import hitboxes.Player;
import hitboxes.killblocks.KillBlock;
import hitboxes.killblocks.KillBlockCycle;
import hitboxes.killblocks.KillBlockPath;
import hitboxes.safeblocks.SafeBlock;
import hitboxes.safeblocks.SafeBlockCycle;
import navigation.Coord;

public class MapParser {
	public static Map parseMap(GameState gs, final int i) throws IOException {
		Map temp = null;
		switch(i){
		case 0:
			/*temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg0.jpg")),new Coord((Map.WIDTH - Block.WIDTH) / 2, (Map.HEIGHT - Block.WIDTH) / 2));
			int y = 720 / 3;
			int dx = 1280 / 5;
			temp.add(new SafeBlock(new Coord(Map.getMiddle().X() + 400, 0), new Coord(20,100)).setColor(Color.GREEN));
			temp.add(new SafeBlock(		new Coord(dx - Block.WIDTH					, y	- Block.WIDTH / 2			)).setColor(Color.GREEN));
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
			*/
			temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg0.jpg")),new Coord((Map.WIDTH - Block.WIDTH) / 2, (Map.HEIGHT - Block.WIDTH) / 2));
			temp.add(new Exit(	
					new Coord(976,0), 							//ULC
					new Coord(1280 - 976,720),					//SIZE 
					1).setVisible(false));
			temp.add(new Exit(	
					new Coord(0,0), 
					new Coord(302,720), 
					-1).setVisible(false));
			break;
		case 2:
			temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg1.jpg"))	,new Coord(0,(Map.HEIGHT - Block.HEIGHT) / 2)		);
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
					3
					));
			break;
		case 3:
			Coord sizeSB2 = new Coord(Map.WIDTH - (Map.WIDTH) / 12, (720 - 200) / 2);
			temp = new Map(Color.WHITE	,new Coord(0,(Map.HEIGHT - Block.HEIGHT) / 2)		);
			//SAFEBLOCKS
			temp.add(new SafeBlock(new Coord(0,0),				//Upper black box
					new Coord(Map.WIDTH,42)).setColor(Color.BLACK));
			temp.add(new SafeBlock(new Coord(0,0),				//Right black box
					new Coord(42,Map.HEIGHT)).setColor(Color.BLACK));
			temp.add(new SafeBlock(new Coord(Map.WIDTH-42,0),		//left	
					new Coord(42,Map.HEIGHT)).setColor(Color.BLACK));
			temp.add(new SafeBlock(new Coord(0,Map.HEIGHT-42),		//lower		
					new Coord(Map.WIDTH,42)).setColor(Color.BLACK));
			temp.add(new SafeBlock(new Coord(330,42),				
					new Coord(930-330+20, 153-43)).setColor(Color.BLACK)); //mid övre
			
			temp.add(new SafeBlock(new Coord(330,253),				
					new Coord(930-330+20, 60)).setColor(Color.BLACK));		//mid mid
			temp.add(new SafeBlock(new Coord(330,253+60),				
					new Coord(15, 60)).setColor(Color.BLACK));		//mid mid vänster spalt övre
			temp.add(new SafeBlock(new Coord(330,253+60+60+100),				
					new Coord(15, 250)).setColor(Color.BLACK));		//mid mid vänster spalt nedre
			temp.add(new SafeBlock(new Coord(950-15,253+60),				
					new Coord(15, 60)).setColor(Color.BLACK));		//mid mid höger spalt övre
			temp.add(new SafeBlock(new Coord(950-15,253+60+60+100),				
					new Coord(15, 250)).setColor(Color.BLACK));		//mid mid höger spalt nedre
			
			//temp.add(new SafeBlock(new Coord(445,413),				
			//		new Coord(390, 40)).setColor(Color.BLACK));		//ön i mitten av allt
			temp.add(new KillBlock(new Coord(445,413),				
					new Coord(390, 40)));		//ön i mitten av allt
			
			temp.add(new SafeBlock(new Coord(345,553),				
					new Coord(930-330+20-30, 160)).setColor(Color.BLACK));		//mid lägre
			
			temp.add(new SafeBlock(new Coord(42,250),				
					new Coord(222-42,342-250)).setColor(Color.BLACK));	//övre vänstra
			temp.add(new SafeBlock(new Coord(Map.WIDTH-222,250),				
					new Coord(222-42, 342-250)).setColor(Color.BLACK));	//övre högra
			
			temp.add(new SafeBlock(new Coord(42,350+342-250),				
					new Coord(222-42, Map.HEIGHT-350-342+250-142)).setColor(Color.BLACK)); //lägre vänstra
			temp.add(new SafeBlock(new Coord(Map.WIDTH-222, 350+342-250),				
					new Coord(222-42, Map.HEIGHT-350-342+250-142)).setColor(Color.BLACK));	//lägre högra
			
			temp.add(new SafeBlock(new Coord(142,132),				
					new Coord(330-243, 250-242+20-10)).setColor(Color.BLACK)); //Midblock vänstra
			temp.add(new SafeBlock(new Coord(1030+20,132),				
					new Coord(330-243, 250-242+20-10)).setColor(Color.BLACK)); //Midblock högra
			
			//KILLBLOCKS
			temp.add(new KillBlockPath(
					new Coord(142-243+330+10, 42+10),
					new Coord(142-243+330+10, Map.HEIGHT-142-10)).setSize(new Coord(80,80))); //högra KB
			temp.add(new KillBlockPath(
					new Coord(52, 160),
					new Coord(1030+20+330-243+10, 160)).setSize(new Coord(80,80)).setSpeed(Player.SPEED * 3 / 4));	//Övre KB
			temp.add(new KillBlockPath(
					new Coord(960,42),
					new Coord(960, Map.HEIGHT-142-10)).setSize(new Coord(80,80)));	//Vänstra KB
			
			temp.add(new KillBlockCycle(
					new Coord(330+25,253+70),
					new Coord(950-105,253+70),
					new Coord(950-105,413+40+10),
					new Coord(330+25, 413+40+10),
					new Coord(330+25,253+70)).setSize(new Coord(80,80)).setSpeed(Player.SPEED * 5 / 4));
			
			temp.add(new KillBlockCycle(
					new Coord(950-105,413+40+10),
					new Coord(330+25, 413+40+10),
					new Coord(330+25,253+70),
					new Coord(950-105,253+70),
					new Coord(950-105,413+40+10)).setSize(new Coord(80,80)).setSpeed(Player.SPEED * 5 / 4));
			
			//EXITS
			temp.add(new Exit(
					new Coord((Map.WIDTH * 11)/ 12,sizeSB2.Y()),
					new Coord(Map.WIDTH / 6, 200),
					4
					));
			break;
			
		case 1:
			temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg3.jpg"))	,new Coord(0,(Map.HEIGHT - Block.HEIGHT) / 2)		);
			//SAFEBLOCKS
			temp.add(new SafeBlock(new Coord(-5,521),				//Floor
					new Coord(Map.WIDTH+10,250)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(-5,497),				//Floor
					new Coord(Map.WIDTH+10,521-497)).setColor(new Color(23, 106, 6)));
			temp.add(new SafeBlock(new Coord(400,270),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));//Boxes
			temp.add(new SafeBlock(new Coord(480,270),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(560,270),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(640,270),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(720,270),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			
			temp.add(new SafeBlock(new Coord(140,497-80+2),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(1068,497-80+2),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			
			/*temp.add(new SafeBlock(new Coord(520,43),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(600,43),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));*/
			temp.add(new SafeBlock(new Coord(520,0),				
					new Coord(80,80 + 44)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(600,0),				
					new Coord(80,80 + 44)).setColor(new Color(194, 129, 0)));
			
			temp.add(new SafeBlock(new Coord(400,190),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			temp.add(new SafeBlock(new Coord(720,190),				
					new Coord(80,80)).setColor(new Color(194, 129, 0)));
			//KILLBLOCKS
			
			temp.add(new KillBlockPath(new Coord(220,497-80+2),
					new Coord(320,350),
					new Coord(420, 497-80+2),
					new Coord(520,350), 
					new Coord(620,497-80+2),
					new Coord(720,350),
					new Coord(820,497-80+2),
					new Coord(900,350),
					new Coord(1068-80,497-80+2)).setSize(new Coord(80,80)).setSpeed(Player.SPEED * 3 / 2 + 1));
			temp.add(new KillBlockPath(new Coord(480,190),
					new Coord(560,120),
					new Coord(720-80,190)).setSize(new Coord(80,80)).setSpeed(Player.SPEED / 3 + 2));
			//EXITS
			temp.add(new Exit(
					new Coord((Map.WIDTH-80),497-80+2),
					new Coord(80,80),
					2
					));
			break;
			
		case 4:
			temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg4.jpg"))	,new Coord(26,35)		);
			//KILLBLOCKS
			temp.add(new KillBlockPath(
					new Coord(358,35),
					new Coord(60,304)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(60,304),
					new Coord(358,35)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(689,35),
					new Coord(60,602)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(60,602),
					new Coord(689,35)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(1021,35),
					new Coord(391,602)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(391,602),
					new Coord(1021,35)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(1153,214),
					new Coord(722,602)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(722,602),
					new Coord(1153,214)).setSize(new Coord(80,80)));
			temp.add(new KillBlockPath(
					new Coord(1153,512),
					new Coord(1054,602)).setSize(new Coord(80,80)));
			temp.add(new SafeBlockCycle(
					new Coord(Map.WIDTH - Block.WIDTH, Map.HEIGHT - Block.HEIGHT),
					new Coord(Map.WIDTH - Block.WIDTH, 0),
					new Coord(0,0),
					new Coord(0,Map.HEIGHT - Block.WIDTH)));
			temp.add(new SafeBlockCycle(
					new Coord(0,Map.HEIGHT - Block.WIDTH),
					new Coord(0,0),
					new Coord(Map.WIDTH - Block.WIDTH, 0),
					new Coord(Map.WIDTH - Block.WIDTH, Map.HEIGHT - Block.HEIGHT)));
					
					
					
			
			//EXITS
			temp.add(new Exit(
					new Coord(1153,601),
					new Coord(100,100),
					5
					));
			break;
		case 5:
			temp = new Map(ImageIO.read(gs.getClass().getResourceAsStream("/res/images/bg5.jpg")), Map.getMiddle().sub(Map.WIDTH / 2,-Player.WIDTH / 2));
			//EXITS
			temp.add(new Exit(
					new Coord(Map.WIDTH - 100, Map.HEIGHT / 4),
					new Coord(100,Map.HEIGHT / 2),
					-1));
		}
		return temp;
	}
}

package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import navigation.Coord;
/*
 * För att göra en map:
 * Börja med att skriva 
 * 	//HÄR VÄLJER MAN ANTINGEN EN SIFFRA ELLER EN FÄRG
 * BLACK // Gör en backgrundsfärg
 * 1 // Siffra tar en bild ifrån imagesmappen
 * SPAWN // SPAWN
 * x y 
 * E //SAFEBLOCK
 * x y width height vartexiten leder 	//COORDINATER, kan finnas flera i rad
 * x y 									//Får standardbredd
 * S
 * x y width height 					//Vanligt safeblock
 * x y 									//Safeblock med standardhöjd
 * SP
 * width heigth | x y | x y | x y | ... //Safeblockpath
 * | x y | x y | x y | x y ... 			//Standardsize safeblockpath
 * SC
 * width heigth | x y | x y | x y | ... //Safeblockcykle
 * | x y | x y | x y | x y ... 			//Standardsize safeblockpath
 *  // För killblocks är det samma sak förutom att deras kommandon ska vara efter allt
 * K
 * KP
 * KC
 */
public class MapParser {
	private static final String[] expect = {"SPAWN","E","S","SP","SC","K","KP","KC"};
	private MapParser(){}
	public static Map[] parseMaps() throws IOException{
		File[] rawfiles = new File("src/res/maps/").listFiles();
		Map[] maps = new Map[rawfiles.length];
		ArrayList<BufferedReader> files = new ArrayList<BufferedReader>();
		for(File file : rawfiles){
			files.add(new BufferedReader(new FileReader(file)));
		}
		for(BufferedReader file : files){
			Iterator<String> it = Arrays.asList(expect).iterator();
			String line = file.readLine();
			String next = it.next();
			String curr = "COLOR";
			Color c = null;
			Coord spawn = null;
			while(line != null){
				if(line.equals(next)){
					curr = next;
					next = it.next();
				}else{
					if(curr.equals("COLOR")){
						if(c == null){
							c = stringToColor(line);
							if(c == null){
								throwIAE(files,file);
							}
						}else{
							throwIAE(files,file);
						}
					}else if(curr.equals("SPAWN")){
						if(spawn == null && line.matches("\\d \\d")){
							//spawn = new Coord()
						}else{
							throwIAE(files,file);
						}
					}
				}
				line = file.readLine();
			}
		}
		return null;
	}
	public static Color stringToColor(String s){
		if(s.equals("BLACK")){
			return Color.BLACK;
		}else if(s.equals("WHITE")){
			return Color.WHITE;
		}
		return null;
	}
	public static void throwIAE(ArrayList<BufferedReader> files, BufferedReader file){
		throw new IllegalArgumentException("Something is wrong with map " + files.indexOf(file));
	}
}

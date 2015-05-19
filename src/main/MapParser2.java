package main;

import hitboxes.Exit;
import hitboxes.killblocks.KillBlock;
import hitboxes.safeblocks.SafeBlock;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;

import navigation.Coord;
/*
 * TITTA I EXAMPLE MAP.txt FÖR ETT EXEMPEL
 */
public class MapParser2 {
	private static final String[] expect = {"SPAWN","E","S","SP","SC","K","KP","KC"};
	private MapParser2(){}
	public static Map[] parseMaps() throws IOException{
		File[] rawfiles = new File("src/res/maps/").listFiles();
		Map[] maps = new Map[rawfiles.length - 1];
		for(int i = 0; i < maps.length; i++){
			maps[i] = parseMap(i);
		}
		return maps;
	}
	public static Color stringToColor(String s){
		if(s.equalsIgnoreCase("BLACK")){
			return Color.BLACK;
		}else if(s.equalsIgnoreCase("WHITE")){
			return Color.WHITE;
		}else if(s.equalsIgnoreCase("YELLOW")){
			return Color.YELLOW;
		}else if(s.equalsIgnoreCase("CYAN")){
			return Color.CYAN;
		}else if(s.equalsIgnoreCase("RED")){
			return Color.BLUE;
		}
		return null;
	}
	public static void throwIAE(int i, String line){
		throw new IllegalArgumentException("Something is wrong with map " + i + ". The line " + line + " is not acceptable. ");
	}
	public static Map parseMap(int i) throws IOException{
		@SuppressWarnings("resource")
		BufferedReader file = new BufferedReader(new FileReader(new File ("src/res/maps/map" + i + ".txt")));
		Iterator<String> it = Arrays.asList(expect).iterator();
		String line = file.readLine();
		String next = it.next();
		String curr = "COLOR/MAP";
		Color c = null;
		BufferedImage b = null;
		Coord spawn = null;
		Map temp = null;
		while(line != null){
			line = line.replaceAll("\\s*(#.*)*", "");
			if(line.equals(next) && it.hasNext()){
				curr = next;
				next = it.next();
			}else{
				if(line.matches("^\\s*(#.*)*")){
				}else if(curr.equals("COLOR/MAP")){
					line = line.replaceAll("\\s*#.*", "");
					if(c == null){
						c = stringToColor(line);
						if(c == null){
							b = ImageIO.read(new File("src/res/images/bg" + line + ".jpg"));
							if(b == null){
								throwIAE(i,line);
							}
						}
					}else{
						throwIAE(i,line);
					}
				}else if(curr.equals("SPAWN")){
					if(spawn == null && line.matches("\\d+,\\d+")){
						spawn = stringToCoords(line)[0];
						if(c == null){
							temp = new Map(b,spawn);
						}else
							temp = new Map(c,spawn);
					}else{
						throwIAE(i,line);
					}
				}else if(curr.equals("E")){
					if(line.matches("(-1|\\d+)(,[a-zA-Z ]+){0,1}_\\d+,\\d+(-\\d+,\\d+){0,1}")){
						String[] parts = line.split("_");
						if(parts.length != 2){
							throwIAE(i,line);
						}else{
							Coord[] coords = stringToCoords(parts[1]);
							String[] inputs = parts[0].split(",");
							if(coords.length == 2 && inputs.length == 2){
								temp.add(new Exit(coords[1],coords[0],Integer.parseInt(inputs[0])).setText(inputs[1]));
							}else if(coords.length == 1 && inputs.length == 2){
								temp.add(new Exit(coords[0],Integer.parseInt(inputs[0])).setText(inputs[1]));
							}else if(coords.length == 2 && inputs.length == 1){
								temp.add(new Exit(coords[1],coords[0],Integer.parseInt(inputs[0])));
							}else if(coords.length == 1 && inputs.length == 1){
								temp.add(new Exit(coords[0],Integer.parseInt(inputs[0])));
							}else{
								throwIAE(i,line);
							}
						}
					}else{	
						throwIAE(i,line);
					}
				}else if(curr.equals("S")){
					if(line.matches("\\d+,\\d+(-\\d,\\d){0,1}")){
						Coord[] coords = stringToCoords(line);
						if(coords.length == 1){
							temp.add(new SafeBlock(coords[0]));
						}else if(coords.length == 2){
							temp.add(new SafeBlock(coords[1],coords[0]));
						}else{
							throwIAE(i,line);
						}
					}
				}else if(curr.equals("K")){
					if(line.matches("\\d+,\\d+(-\\d,\\d){0,1}")){
						Coord[] coords = stringToCoords(line);
						if(coords.length == 1){
							temp.add(new KillBlock(coords[0]));
						}else if(coords.length == 2){
							temp.add(new KillBlock(coords[1],coords[0]));
						}else{
							throwIAE(i,line);
						}
					}
				}else{
				}
			}
			line = file.readLine();
		}
		return temp;
	}
	/**
	 * Changes a string of coords to an array containg the coords.
	 * Every coord must be seperated by a - and it must be written as
	 * (x,y). Example:
	 * -100,40-95,90-50,30
	 * @param s
	 * The string of coords that will be converted
	 * @return
	 */
	public static Coord[] stringToCoords(String s){
		ArrayList<String> text_coords  = new ArrayList<String>(Arrays.asList(s.replace(" ", "").split("-")));
		for(int i = text_coords.size() - 1; i >= 0; i -- ){
			if(text_coords.get(i).equals("")){
				text_coords.remove(i);
			}
		}
		if(text_coords.size() > 0){
			Coord[] coords = new Coord[text_coords.size()];
			for(int i = 0; i < text_coords.size(); i++){
				String text_coord = text_coords.get(i);
				if(text_coord.matches("\\d+,\\d+")){
					text_coord = text_coord.replace("(", "");
					text_coord = text_coord.replace(")", "");
					String[] temp = text_coord.split(",");
					coords[i] = new Coord(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				}else{
					throw new IllegalArgumentException("One or more of the text was not coords: " + text_coord);
				}
			}
			return coords;
		}else{
			return new Coord[0];
		}
		
		
	}
}

package hitboxes;

import java.awt.Color;
import java.awt.Graphics;
import navigation.Coord;
import main.DrawText;

public class Exit extends Block{
	private static final int WIDTH = 100, HEIGHT = 100;
	private int exitCode;
	private DrawText text;
	/**
	 * Initiates an Exit
	 * @param upper_left_corner
	 * @param size
	 * @param exit_code
	 * Exit code -1 means the game should end
	 * Any number greater or equal to 0 references to a map
	 */
	public Exit(Coord upper_left_corner, Coord size, int exit_code) {
		super(upper_left_corner, size);
		exitCode = exit_code; 
		color = Color.CYAN;
	}
	/**
	 * Initiates an Exit with default size
	 * @param upper_left_corner
	 * @param exit_code
	 */
	public Exit(Coord upper_left_corner, int exit_code){
		super(upper_left_corner, new Coord(WIDTH,HEIGHT));
		exitCode = exit_code;
		color = Color.CYAN;
	}
	/**
	 * Returns where the exit leads. 
	 * @return
	 */
	public int getExitCode(){
		return exitCode;
	}
	/**
	 * Sets text in the middle of the exit
	 * @param s
	 * The string that should be displayed
	 * @return
	 * this
	 */
	public Exit setText(String s){
		text = new DrawText(getMiddle(),s);
		return this;
	}
	/**
	 * Sets the color of the block
	 * Default color <bold>Color.Cyan</bold>
	 * @param c
	 * The color that the exit should have
	 * @return
	 * this
	 */
	public Exit setColor(Color c){
		color = c;
		return this;
	}
	/**
	 * Draws the block
	 */
	public void draw(Graphics g) {
		if(g.getColor() != color){
			g.setColor(color);
		}
		g.fillRect(c.getX(), c.getY(), Width(), Height());
		draw_border(g);
		if(text != null){
			//TODO Display text in the middle of the exit
			text.draw(g);
		}
	}
}

package main;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Run extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	private static final int TARGET_FPS = 60;
	private final String NAME = "BOC";
	private final int MAXW = 1280, MAXH = 720;
	private JFrame frame;
	private boolean running;
	private GameState gs;
	public int fps = 0;
	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args){
		//Får spelet att fungera bra på unixbaserade operativsystem
		//Utan detta blir spelet hackigt
		System.setProperty("sun.java2d.opengl", "True");
		new Run().start();
	}
	/**
	 * Initiates the frame and starts the key listener
	 */
	Run(){
		
		setMinimumSize(new Dimension(MAXW,MAXH));
		setMaximumSize(new Dimension(MAXW,MAXH));
		setPreferredSize(new Dimension(MAXW,MAXH));
		
		frame = new JFrame(NAME);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.setExtendedState(JFrame.NORMAL);
		
		frame.add(this,BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	/**
	 * Initiates all of the program
	 */
	public synchronized void start(){
		running = true;
		gs = new GameState(this);
		addKeyListener((KeyListener) gs);
		new Thread(new Sound("src/res/sound/BPDG.wav")).start();
		new Thread(this).start();
	}
	/**
	 * Kills the program
	 */
	public synchronized void stop(){
		running = false;
	}
	/**
	 * Starts as the thread is initiated
	 * The main game loop that updates the gamestate
	 * and draws the current map
	 */
	@Override
	public void run() {
		int frames = 0;
		long time = System.currentTimeMillis();
		while(running){
			long t0 = System.currentTimeMillis();
			render();
			frames++;
			running = gs.update();
			long t1 = System.currentTimeMillis();
			if(t1-t0 < 1000.0 / TARGET_FPS){
				while(t1-t0 < 1000.0 / TARGET_FPS){
					t1 = System.currentTimeMillis();
				}
			}
			if(System.currentTimeMillis() - time > 100){
				fps = (int) (frames / 10.0);
				frames = 0;
				time = System.currentTimeMillis();
			}
		}
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	/**
	 * Renders the current map and all the blocks in it
	 */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0,0,getWidth(), getHeight());
		gs.draw(g);
		g.dispose();
		bs.show();
	}
}

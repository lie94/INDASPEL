package main;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Run extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;
	private final String NAME = "BOC";
	private final int MAXW = 1280, MAXH = 720;
	private JFrame frame;
	private boolean running;
	private GameState gs;
	
	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args){
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
		addKeyListener((KeyListener) this);
	}
	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop(){
		running = false;
	}
	@Override
	public void run() {
		init();
		while(running){
			long t0 = System.currentTimeMillis();
			running = gs.update();
			render();
			long t1 = System.currentTimeMillis();
			if(t1-t0 < 16){
				while(t1-t0 < 16){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					t1 = System.currentTimeMillis();
				}
			}
			System.out.println(1000 / (t1 - t0));
		}
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	@Override
	public void keyPressed(KeyEvent e) {
		gs.send(e,true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		gs.send(e,false);
	}
	@Override
	public void keyTyped(KeyEvent e) {}
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
	private void init(){
		gs = new GameState();
		
	}
}

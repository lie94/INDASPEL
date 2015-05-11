import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public class Run extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;
	private final String NAME = "BOC";
	private final int MAXW = 1280, MAXH = 720;
	private JFrame frame;
	private boolean running;
	private GameState gs;
	
	public static void main(String[] args){
		new Run().start();
	}
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
		// TODO Auto-generated method stub
		init();
		while(running){
			long t0 = System.currentTimeMillis();
			gs.update();
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
		}
		
		
	}
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
		// TODO Auto-generated method stub
		gs = new GameState();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		gs.send(e,true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		gs.send(e,false);
	}
	@Override
	public void keyTyped(KeyEvent e) {}
}

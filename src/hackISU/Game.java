package hackISU;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import StateMachine.MenuState;
import StateMachine.StateMachine;

public class Game extends JPanel implements Runnable, KeyListener{
	
	//dimensions
	public static final int kWIDTH = 320;
	public static final int kHEIGHT = 240;
	public static final int kSCALE = 2;
	
	//thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private int time = 1000 / FPS;
	
	private Graphics2D g;
	private BufferedImage bufferedImage;
	private StateMachine sm;
	
	public Game(){
		super();
		setPreferredSize(new Dimension(kWIDTH * 2, kHEIGHT * 2));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	} 
	
	public void init(){
		//bufferedimage
		bufferedImage = new BufferedImage(kWIDTH, kHEIGHT, BufferedImage.TYPE_INT_RGB);
		//graphics
		g = (Graphics2D) bufferedImage.getGraphics();
		running = true;
		
		sm = new StateMachine();
		sm.Add("mainMenu", new MenuState(sm));
		sm.Push("mainMenu");
	}
	
	public void run(){
		init();
		long start;
		long elapsedTime;
		long wait;
		
		while(running){
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsedTime = System.nanoTime() - start;
			wait = time - elapsedTime / 1000000;
			if(wait < 0){
				wait = 1;
			}
			try{
				Thread.sleep(wait);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void update(){
		sm.update();
	}
	
	public void draw(){
		sm.draw(g);
	}
	
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(bufferedImage, 0, 0, kWIDTH * kSCALE, kHEIGHT * kSCALE, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key){
		
	}
	
	public void keyPressed(KeyEvent key){
		sm.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key){
		sm.keyReleased(key.getKeyCode());
	}
	
}

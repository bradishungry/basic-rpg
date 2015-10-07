package StateMachine;

import java.awt.*;
import java.awt.event.KeyEvent;

import audio.audioSounds;
import StateMachine.StateMachine;
import TileMap.Background;

public class MenuState implements IState{
	
	private int currentChoice = 0;
	private Background bg;
	private String[] options = {"Start", "Quit"};
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private StateMachine sm;
	private audioSounds music;
	
	public MenuState(StateMachine sm){
		this.sm = sm;
		try{
			bg = new Background("/Backgrounds/Forest.png", 1);
			bg.setVector(-0.1, 0);
			titleColor = new Color(255, 255, 255);
			titleFont = new Font("Arial", Font.BOLD, 28);
			font = new Font("Arial", Font.PLAIN, 12);
			music = new audioSounds("/Music/Zanarkand.mp3");
			music.play();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init(){
		
	}
	
	public void update(){
		bg.update();
	}
	
	public void draw(java.awt.Graphics2D g){
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Generic RPG XI", 60, 80);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i += 1){
			if (i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 145, 180 + i * 15);
		}
	}
	
	public void KeyPressed(int k){
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP){
			currentChoice -= 1;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN){
			currentChoice += 1;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	
	public void KeyReleased(int k){
		
	}
	
	public void OnEnter(){}
	
	public void OnExit(){}
	
	private void select(){
		if (currentChoice == 0){
			sm.Add("FirstState", new FirstState(sm));
			music.stop(); 
			sm.Push("FirstState");
		} else if (currentChoice == 1){
			System.exit(0);
		}
	}
	
}

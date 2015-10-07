package StateMachine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import audio.audioSounds;
import TileMap.Background;

public class BattleState implements IState {
	
	private int currentChoice = 1;
	private Background bg;
	private Color battleColor;
	private Font battleFont;
	private StateMachine sm;
	private String[] options = {"Fight", "Dance Moves", "Equip", "Beverages"};
	private String[] options2 = {"Running Man"};
	private int selected = 0;
	private boolean danceMove = false;
	private audioSounds music;
	
	public BattleState(StateMachine sm){
		this.sm = sm;
		bg = new Background("/Backgrounds/B.jpg", 0.1);
		battleColor = new Color(255, 255, 255);
		battleFont = new Font("Arial", Font.BOLD, 48);
		battleFont = new Font("Arial", Font.PLAIN, 12);
		music = new audioSounds("/Music/battle.mp3");
		music.play();
	}
	
	public void update(){
		bg.update();
	}
	
	public void draw(java.awt.Graphics2D g){
		bg.draw(g);
		g.setColor(battleColor);
		g.setFont(battleFont);
		if(selected == 0){
		g.drawString("SPOOKY ENEMY HERE", 80, 20);
		} else if(selected == 1){
			g.drawString("I am not good enough at programming yet!", 60, 20);
		} else if(selected == 2){
			g.drawString("Which move will you choose??", 60, 20);
			danceMove = true;
		} else if(selected == 3){
			g.drawString("Can not equip what is not programmed :-/", 60, 20);
		} else if(selected == 4){
			g.drawString("You drink a hot beverage, and die", 60, 20);
		}
		g.setColor(Color.BLUE);
		g.fillRect(10, 140, 90, 100);
		g.setColor(Color.WHITE);
		g.draw(new Rectangle(10, 140, 90, 100));
		
		g.setFont(battleFont);
		for(int i = 0; i < options.length; i += 1){
			if (i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			if(danceMove == false){
			g.drawString(options[i], 16, 165 + i * 15);
			} else if(danceMove == true){
				g.drawString(options2[0], 16, 165 + 0 * 15);
			}
		}
		
	}

	@Override
	public void KeyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP && danceMove == false){
			currentChoice -= 1;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN && danceMove == false){
			currentChoice += 1;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
		if (k == KeyEvent.VK_UP && danceMove == true){
			currentChoice -= 1;
			if(currentChoice == -1){
				currentChoice = options2.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN && danceMove == true){
			currentChoice += 1;
			if(currentChoice == options2.length){
				currentChoice = 0;
			}
		}
		
	}
	
	private void select(){
		if(danceMove == true){
			music.stop();
			sm.Pop();
		}
		if (currentChoice == 0){
			selected = 1;
		} else if (currentChoice == 1){
			selected = 2;
		} else if (currentChoice == 2){
			selected = 3;
		} else if(currentChoice == 3){
			selected = 4;
		}
	}

	@Override
	public void KeyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnExit() {
		// TODO Auto-generated method stub
		
	}

}

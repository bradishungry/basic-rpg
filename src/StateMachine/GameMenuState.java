package StateMachine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import TileMap.Background;

public class GameMenuState implements IState {
	
	private StateMachine sm;
	private Background bg;
	private String[] options = {"Item", "Skills", "Equip", "Acc.", "Status", "Option", "Menu", "Save"};
	private int currentChoice = 1;
	private Font font;
	private double money;
	private int heartPoints = 9999;
	private int maxHeartPoints = 9999;
	private int magicPoints = 999;
	private int maxMagicPoints = 999;
	private int LV = 99;
	private long sTime;
	private long lTime;
	private BufferedImage tidus;
	private BufferedImage yuna;
	private BufferedImage Kimari;
	
	public GameMenuState(StateMachine sm){
		this.sm = sm;
		bg = new Background("/Backgrounds/B.jpg", 0.1);
		font = new Font("Arial", Font.PLAIN, 12);
		sTime = System.currentTimeMillis() / 1000;
		money = 13.00;
		try {
			tidus = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/tidus2.png"));
			yuna = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/yuna.png"));
			Kimari = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Kimari.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(Color.BLUE);
		g.fillRoundRect(0, 10, 240, 220, 15, 15);
		g.fillRoundRect(240, 5, 79, 130, 15, 15);
		g.fillRoundRect(240, 135, 79, 60, 15, 15);
		g.fillRoundRect(240, 195, 79, 40, 15, 15);
	
		g.setColor(Color.WHITE);
		g.drawRoundRect(0, 10, 240, 220, 15, 15);
		g.drawRoundRect(240, 5, 79, 130, 15, 15);
		g.drawRoundRect(240, 135, 79, 60, 15, 15);
		g.drawRoundRect(240, 195, 79, 40, 15, 15);
		g.setFont(font);
		g.drawImage(tidus, 10, 17, null);
		g.drawImage(yuna, 10, 73, null);
		g.drawImage(Kimari, 10, 126, null);
		g.drawString("Tidus", 70, 26);
		g.drawString("LV", 75, 41);
		g.drawString("HP", 75, 51);
		g.drawString("MP", 75, 61);
		g.drawString(LV + "", 114, 41);
		g.drawString(heartPoints + "/" + maxHeartPoints, 100, 51);
		g.drawString(magicPoints + "/" + maxMagicPoints, 114, 61);
		g.drawString("Yuna", 70, 77);
		g.drawString("LV", 75, 93);
		g.drawString("HP", 75, 103);
		g.drawString("MP", 75, 113);
		g.drawString(LV + "", 114, 93);
		g.drawString(heartPoints + "/" + maxHeartPoints, 100, 103);
		g.drawString(magicPoints + "/" + maxMagicPoints, 114, 113);
		g.drawString("Kimari", 70, 133);
		g.drawString("LV", 75, 149);
		g.drawString("HP", 75, 159);
		g.drawString("MP", 75, 169);
		g.drawString(LV + "", 114, 149);
		g.drawString(heartPoints + "/" + maxHeartPoints, 100, 159);
		g.drawString(magicPoints + "/" + maxMagicPoints, 114, 169);
		
		
		for(int i = 0; i < options.length; i += 1){
			if (i == currentChoice){
				g.setColor(Color.CYAN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 245, 21 + i * 15);
		}
		
		g.drawString("Time: ", 245, 155);
		lTime = System.currentTimeMillis() / 1000;
		g.drawString((lTime - sTime) + "", 280, 155);
		g.drawString("Steps: " + getSteps(), 245, 175);
		g.drawString("$$$:  " + money + "0", 245, 220);
	}

	public void KeyPressed(int k) {
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
		
		if(k == KeyEvent.VK_S){
			sm.Pop();
		}
	}
	
	public void KeyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	public void OnEnter() {
		// TODO Auto-generated method stub
		
	}

	public void OnExit() {
		// TODO Auto-generated method stub
		
	}
	
	private void select(){
		if (currentChoice == 6){
			toMenu();
	}
	}
	
	private void toMenu(){
		sm.Pop();
		sm.Pop();
	}
	
	public long getSteps(){
		return FirstState.steps / 10;
	}

}

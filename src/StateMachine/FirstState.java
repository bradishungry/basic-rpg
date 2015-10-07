package StateMachine;

import hackISU.Game;

import java.awt.Graphics2D;

import playerObjects.Player;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.event.KeyEvent;

import audio.audioSounds;

public class FirstState implements IState{
	private TileMap tileMap;
	private Background bg;
	private StateMachine sm;
	private Player player;
	private audioSounds music;
	protected static long steps;
	
	public FirstState(StateMachine sm){
		this.sm = sm;
		init();
	}
	
	public void init(){
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/map2.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		player = new Player(tileMap);
		steps = 0;
		player.setPostition(100, 100);
		music = new audioSounds("/Music/besaid.mp3");
		music.play();
	}
	
	public void update() {
		player.update();
		tileMap.setPosition(Game.kWIDTH / 2 - player.getx(), Game.kHEIGHT /  2 - player.gety());
//		battleState();
		
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		tileMap.draw(g);
		player.draw(g);
	}
	
	public void KeyPressed(int k) {
		if(k == KeyEvent.VK_LEFT){
			steps += 1;
			player.setLeft(true);
		}
		
		if(k == KeyEvent.VK_RIGHT){
			steps += 1;
			player.setRight(true);
		}
		
		if(k == KeyEvent.VK_UP){
			steps += 1;
			player.setUp(true);
		}
		
		if(k == KeyEvent.VK_DOWN){
			steps += 1;
			player.setDown(true);
		}
		
		if(k == KeyEvent.VK_S){
			sm.Add("GameMenuState", new GameMenuState(sm));
			sm.Push("GameMenuState");
		}
		
		if(k == KeyEvent.VK_ESCAPE){
			sm.Pop();
		}
		
	}
	
	public void KeyReleased(int k) {
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		
		if(k == KeyEvent.VK_UP){
			player.setUp(false);
		}
		
		if(k == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		
	}
	
	public void OnEnter() {
		// TODO Auto-generated method stub
		
	}
	
	public void OnExit() {
		// TODO Auto-generated method stub
		
	}
	
//	public void battleState(){
//		if(player.battleTimer() == true){
//			sm.Add("BattleState", new BattleState(sm));
//			music.stop();
//			sm.Push("BattleState");
//		}
//	}
}

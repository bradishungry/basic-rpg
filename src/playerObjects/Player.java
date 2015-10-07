package playerObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Player extends MapObject{
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			1, 4, 0, 4
	};
	public static final int IDLE = 0;
	public static final int WALKING = 1;
	public static final int EMPTY = 2;
	public static final int BACK = 3;
	
	public Player(TileMap tm){
		super(tm);
		
		width = 32;
		height = 48;
		cwidth = 20;
		cheight = 20;
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		
		try{
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/tidus.png"));
			sprites = new ArrayList<BufferedImage[]>();

			for(int i = 0; i < 4; i += 1){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j += 1){
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				sprites.add(bi);
			}
			BufferedImage[] bi = new BufferedImage[4];
			for(int j = 0; j < 4; j += 1){
				bi[j] = spritesheet.getSubimage(j * width, 0, width, height);
			}
			sprites.add(bi);
		} catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
	}
	
	private void getNextPosition(){
		if(left){
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		} else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		} else {
			if(dx > 0){
				dx -= stopSpeed;
				if(dx < 0){
					dx = 0;
				}
			}
			else if(dx < 0){
				dx += stopSpeed;
				if(dx > 0){
					dx = 0;
				}
			}
		}
		if(up){
			dy -= moveSpeed;
			if(dy < -maxSpeed){
				dy = -maxSpeed;
			}
		} else if(down){
			dy += moveSpeed;
			if(dy > maxSpeed){
				dy = maxSpeed;
			}
		} else {
			if(dy > 0){
				dy -= stopSpeed;
				if(dy < 0){
					dy = 0;
				}
			}
			else if(dy < 0){
				dy += stopSpeed;
				if(dy > 0){
					dy = 0;
				}
			}
		}
	}
	
	
	public void update(){
		getNextPosition();
//		battleTimer();
		checkTileMapCollision();
		setPostition(xtemp, ytemp);
		if(up){
			if(currentAction != BACK){
				currentAction = BACK;
				animation.setFrames(sprites.get(BACK));
				animation.setDelay(250);
				width = 32;
			}
		}
		if(left || right){
			if(currentAction != WALKING){
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(250);
				width = 32;
			}
		}
		else{
			if(!(up)){
				if(currentAction != IDLE){
					currentAction = IDLE;
					animation.setFrames(sprites.get(IDLE));
					animation.setDelay(250);
					width = 32;
				}
			}
		}
		animation.update();
		
		if(right){
			facingRight = false;
		}
			
		if(left){
			facingRight = true;
		}
		
		
	}
	
	
	public void draw(Graphics2D g){
		setMapPosition();
		if(facingRight){
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width, height, null);
		}
	}
	
//	public boolean battleTimer(){
//		float time = System.currentTimeMillis();
//		Random rand = new Random();
//		if(time % (float) rand.nextInt() > 0){
//			return true;
//		}
//		return false;
//	}
	
	
}

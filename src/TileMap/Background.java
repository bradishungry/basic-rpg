package TileMap;

import hackISU.Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double movescale;
	
	public Background(String s, double ms){
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = (x * movescale) % Game.kWIDTH;
		this.y = (y * movescale) % Game.kHEIGHT;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;;
	}
	
	public void update(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, (int) x, (int) y, null);
		if(x < 0){
			g.drawImage(image, (int) x + Game.kWIDTH, (int) y, null);
		} else if(x > 0){
			g.drawImage(image, (int) x - Game.kWIDTH, (int) y, null);
		}
	}
}

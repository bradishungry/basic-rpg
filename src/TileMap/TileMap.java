	package TileMap;

	import java.awt.*;
import java.awt.image.*;
import java.io.*;

	import javax.imageio.ImageIO;

import hackISU.Game;

	public class TileMap {
		
		private double x;
		private double y;
		
		private int xmin;
		private int ymin;
		private int xmax;
		private int ymax;
		
		private int[][] map;
		private int tileSize;
		private int numRows;
		private int numCols;
		private int width;
		private int height;
		
		private BufferedImage tileset;
		private int numTilesAcross;
		private Tile[][] tiles;
		
		private int rowOffset;
		private int colOffset;
		private int numRowsToDraw;
		private int numColsToDraw;
		
		public TileMap(int tileSize){
			
			this.tileSize = tileSize;
			numRowsToDraw = Game.kHEIGHT / tileSize + 2;
			numColsToDraw = Game.kWIDTH / tileSize + 2;
		}
		
		public void loadTiles(String s){
			try {
				tileset = ImageIO.read(getClass().getResourceAsStream(s));
				numTilesAcross = tileset.getWidth() / tileSize;
				tiles = new Tile[2][numTilesAcross];
				
				BufferedImage subimage;
				for(int col = 0; col < numTilesAcross; col += 1){
					subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
					tiles[0][col] = new Tile(subimage,Tile.kNORMAL);
					subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
					tiles[1][col] = new Tile(subimage, Tile.kBLOCKED);
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public int getNumRows(){
			return numRows;
		}
		
		public int getNumCols(){
			return numCols;
		}
		
		public void loadMap(String s){
			
			try{
				InputStream in = getClass().getResourceAsStream(s);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				numCols = Integer.parseInt(br.readLine());
				numRows = Integer.parseInt(br.readLine());
				map = new int[numRows][numCols];
				width = numCols * tileSize;
				height = numRows * tileSize;
				xmin = Game.kWIDTH - width;
				xmax = 0;
				ymin = Game.kHEIGHT - height;
				ymax = 0;
				
				
				String delims = "\\s+";
				for(int row = 0; row < numRows; row += 1){
					String line = br.readLine();
					String[] tokens = line.split(delims);
					for(int cols = 0; cols < numCols; cols += 1){
						map[row][cols] = Integer.parseInt(tokens[cols]);
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public int getTileSize(){
			return tileSize;
		}
		
		public int getx(){
			return (int) x;
		}	
		
		public int gety(){
			return (int) y;
		}
		
		public int returnWidth(){
			return width;
		}
		
		public int returnHeight(){
			return height;
		}
		
		public int getType(int row, int col){
			int rc = map[row][col];
			int r = rc / numTilesAcross;
			int c = rc % numTilesAcross;
			return tiles[r][c].getType();
		}
		
		public void setPosition(double x, double y){
			this.x += (x - this.x);
			this.y += (y - this.y);
			
			fixBounds();
			
			colOffset = (int) -this.x / tileSize;
			rowOffset = (int) -this.y / tileSize;
		}
		
		private void fixBounds(){
			if(x < xmin) x = xmin;
			if(y < ymin) y = ymin;
			if(x > xmax) x = xmax;
			if(y > ymax) y = ymax;
		}
		
		public void draw(Graphics2D g){
			
			for(int row = rowOffset; row < rowOffset + numRowsToDraw; row += 1){
				if(row >= numRows) break;
				
				for(int col = colOffset; col < colOffset + numColsToDraw; col += 1) {
					if(col >= numCols) break;
					if(map[row][col] == 0) continue;
					
					int rc = map[row][col];
					int r = rc / numTilesAcross;
					int c = rc % numTilesAcross;
					
					g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
					
				}
			}
		}
	}	

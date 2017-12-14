package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import Game.Main;



public class Ground {
	private boolean [][] walls = new boolean[Main.Width][Main.Height];
	private int mapType = 1;
	
	
	
	public int getMapType() {
		return mapType;
	}

	public void setMapType(int mapType) {
		this.mapType = mapType;
	}
	
	 
	public void clear() {
		for (int x = 0; x < Main.Width; x++)
			for (int y = 0; y < Main.Height; y++)
				walls[x][y] = false;
	}
	

	public void generateRocks1() {
		for (int x = 0; x < Main.Width; x++)
			walls[x][0] = walls[x][Main.Height - 1] = true;
		for (int y = 0; y < Main.Height; y++)
			walls[0][y] = walls[Main.Width - 1][y] = true;
	}
	
	
	public void generateRocks2() {

		for (int y = 0; y < 6; y++) {
			walls[0][y] = true;
			walls[Main.Width - 1][y] = true;
			walls[0][Main.Height - 1 - y] = true;
			walls[Main.Width - 1][Main.Height - 1 - y] = true;
		}
		for (int y = 6; y < Main.Height - 6; y++) {
			walls[6][y] = true;
			walls[Main.Width - 7][y] = true;
		}
	}
	
	
	public void generateRocks3() {
		for(int x=4;x<14;x++)
			walls[x][5] = true;
		for(int j=5;j<15;j++)
			walls[21][j] = true;
		for(int y=13;y<20;y++)
			walls[14][y] = true;
		for(int x=2;x<10;x++)
			walls[x][17] = true;
		for(int i=10;i<Main.Width-3;i++)
			walls[i][Main.Height-3] = true;
	}
	
	
	public boolean isGroundEated(Snake snake) {
		for(int x=0; x<Main.Width;x++) {
			for(int y=0; y<Main.Height;y++) {
				if(walls[x][y] == true &&
					(x==snake.getHead().x &&y==snake.getHead().y)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public Point getPoint() {
		Random random = new Random();
		int x=0,y=0;
		do {
			x = random.nextInt(Main.Width);
			y = random.nextInt(Main.Height);
		} while (walls[x][y]==true);

		return new Point(x,y);
	}
	
	public void drawMe(Graphics g) {
		g.setColor(new Color(253,218,4));
		
		for(int x=0; x<Main.Width;x++) {
			for(int y=0; y<Main.Height;y++) {
				if(walls[x][y] == true) {
					g.fill3DRect(x*Main.UnitSize, y*Main.UnitSize, 
							Main.UnitSize,Main.UnitSize, true);
				}
			}
		}
	}
}

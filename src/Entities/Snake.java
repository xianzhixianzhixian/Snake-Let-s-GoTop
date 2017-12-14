package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import Game.Main;
import Listener.SnakeListener;


public class Snake {

	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

	private LinkedList<Point> node = new LinkedList<Point>();

	private boolean alive;
	private boolean pause;					
	private int newDirection,oldDirection;	
	private Point oldTail;					
	private int foodcount = 0;
	private Color headColor;
	private Color bodyColor;
	private int sleepTime;
	
	public Thread t=null;
	
	public LinkedList<Point> getNode() {
		return node;
	}
	
	public int getFoodcount() {
		return foodcount;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setFoodcount(int foodcount) {
		this.foodcount = foodcount;
	}
	
	public boolean isalive() {
		return alive;
	}
	
	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}

	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}


	public void init() {
		int x = Main.Width/2;
		int y = Main.Height/2;
		for(int i=0;i<3;i++) {
			node.addLast(new Point(x--,y));
		}
		
		oldDirection = newDirection = RIGHT;
		foodcount = 0;	
		alive = true;
		pause = false;
		
		sleepTime = 650;
	}
	
	public void clear() {
		node.clear();
	}

	public void setLife(boolean life) {
		this.alive = life;
	}

	public boolean getPause() {
		return pause;
	}	
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	
	public void changePause() {
		pause = !pause;
	}

	//Go die
	public void die() {
		alive = false;
	}

	public void move() {
		if(!(oldDirection + newDirection==0)) {
			oldDirection = newDirection ;
		}
		
		oldTail = node.removeLast();
		int x = node.getFirst().x;
		int y = node.getFirst().y;
		
		switch(oldDirection) {
			case UP:
				y--;
				if(y<0) {
					y = Main.Height -1 ;
				}
				break;
			case DOWN:
				y++;
				if(y >= Main.Height) {
					y = 0;
				}
				break;
			case LEFT:
				x--;
				if(x<0) {
					x = Main.Width-1;
				}
				break;
			case RIGHT:
				x++;
				if(x >= Main.Width) {
					x = 0;
				}
				break;
		}
		
		Point newHead = new Point(x, y);
		node.addFirst(newHead);
	}
	
	
	public void changeDirection(int direction) {
			newDirection = direction;		
	}
	
	
	public void eatFood() {		
		node.addLast(oldTail);
		foodcount++;
		
		//加速
		if(sleepTime>30)
		{
			sleepTime = 650-foodcount*15;
		}
	}
	
	public int getFoodCount() {
		return foodcount;
	}
	
	
	public boolean isEatBody() {
		for(int i=1;i<node.size();i++) {
			if(node.get(i).equals(this.getHead())) 
				return true;
		}
		
		return false;	
	}
	
	
	public Point getHead() {
		return node.getFirst();
	}
	
	
	public void drawMe(Graphics g) {
		if(bodyColor==null) {
			g.setColor(new Color(226,211,172));
		} else {
			g.setColor(bodyColor);
		}
		
		for(Point p : node) {
			
			g.fillRoundRect(p.x*Main.UnitSize, p.y*Main.UnitSize,
					Main.UnitSize, Main.UnitSize, 15,12 );
		}
		drawHead(g);
	}
	
	
	public void drawHead(Graphics g) {
		if(headColor==null)
			g.setColor(new Color(207,191,140));
		else {
			g.setColor(headColor);
		}
		
		g.fillRoundRect(getHead().x * Main.UnitSize, getHead().y* Main.UnitSize, 
				Main.UnitSize, Main.UnitSize, 15,12);
	}
	
	

	private class SnakeDriver implements Runnable {
		public void run() {
			while(alive) {
				if(pause==false) {
					move();			
					for(SnakeListener l : listeners)
						l.snakeMoved(Snake.this);
				}
					
				try {	
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public void begin() {
		t=new Thread(new SnakeDriver());
		t.start();
	}

	
	
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			listeners.add(l);
		}
	}

	
	public void speedUp() {
		if(sleepTime>50) {
			sleepTime-=20;
		}
	}
	
	
	public void speedDown() {
		if(sleepTime<700) {
			sleepTime+=20;
		}
	}

}

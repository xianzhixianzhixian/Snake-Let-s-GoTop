package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

import Game.Main;
import Listener.SnakeListener;

public class Food extends Point{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Color foodColor;
	
	

	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
	}

	public Color getFoodColor() {
		return foodColor;
	}

	public void newFood(Point p) {
		setLocation(p);
	}
	
	public boolean isFoodEated(Snake snake) {			
		return 	this.equals(snake.getHead());
	}
	
	public void drawMe(Graphics g) {
		if(foodColor==null) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(foodColor);
		}
		
		g.fill3DRect(x*Main.UnitSize, y*Main.UnitSize, Main.UnitSize, Main.UnitSize, true);
	}
}

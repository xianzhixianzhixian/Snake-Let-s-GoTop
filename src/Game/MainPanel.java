package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Entities.Food;
import Entities.Ground;
import Entities.Snake;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Snake snake;
	private Food food;
	private Ground ground;	
	public Color BackgroundColor;
	
	public MainPanel() {
		setLocation(0, 0);		
	
		this.setSize(Main.Width * Main.UnitSize, Main.Height
				* Main.UnitSize);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.setFocusable(true);
	}


	public void display(Snake snake,Food food,Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		
		repaint();
	}

	
	//BackGround
	public void clearDraw(Graphics g) {
			if(BackgroundColor==null) {
				g.setColor(new Color(35,31,32));
			}
			else {
				g.setColor(BackgroundColor);
			}
			g.fillRect(0, 0, Main.Width*Main.UnitSize, Main.Height*Main.UnitSize);
	}
	
	
	@Override
	public void paint(Graphics g) {
		clearDraw(g);
				
		if(ground != null && snake != null && food != null) {
			
				snake.drawMe(g);
				ground.drawMe(g);
				food.drawMe(g);
				
		}
		
		if(snake!=null && snake.isalive()==false)  {
			recover(g);
		}

	}
	
	//Game Over
	public void recover(Graphics g) {
		clearDraw(g);
		

		g.setColor(new Color(226,211,172));
		g.setFont(new Font("Sans Serif",Font.BOLD,60));
		g.drawString("Game Over",175, 300);
		
	}
}

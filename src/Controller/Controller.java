package Controller;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Set;

import Entities.Food;
import Entities.Ground;
import Entities.Snake;
import Game.BottonPanel;
import Game.GameMenu;
import Game.MainPanel;
import Game.MusicDemo;
import Listener.SnakeListener;

public class Controller extends KeyAdapter implements SnakeListener{
	private Snake snake;
	private Food food;
	private Ground ground;
	private MainPanel mainPanel;
	private GameMenu gameMenu;
	private BottonPanel bottonPanel;
	private MusicDemo musicDemo;
	
	
	public Controller(Snake snake, Food food, Ground ground,MainPanel mainPanel,GameMenu gameMenu,BottonPanel bottonPanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.mainPanel = mainPanel;
		this.gameMenu = gameMenu;
		this.bottonPanel = bottonPanel;
		
		init();
	}
	
	

	public void init() {
		//播放音乐
		musicDemo=new MusicDemo();
		
		bottonPanel.getStartButton().addActionListener(new startHandler());
		bottonPanel.getPauseButton().addActionListener(new pauseHandler());
		bottonPanel.getEndButton().addActionListener(new endHandler());
		
		gameMenu.getMapItem1().addActionListener(new mapItem1Handler());
		gameMenu.getMapItem2().addActionListener(new mapItem2Handler());
		gameMenu.getMapItem3().addActionListener(new mapItem3Handler());
		
		bottonPanel.getStartButton().setEnabled(true);
	}


	public Snake getSnake() {
		return snake;
	}
	
	public Ground getGround() {
		return ground;
	}
	
	public MainPanel getGamePanel() {
		return mainPanel;
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}
	
	public BottonPanel getBottonPanel() {
		return bottonPanel;
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:			
				snake.changeDirection(Snake.UP);
				break;
			case KeyEvent.VK_DOWN:				
				snake.changeDirection(Snake.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				snake.changeDirection(Snake.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake.changeDirection(Snake.RIGHT);
				break;
			case KeyEvent.VK_W:
				snake.speedUp();
				break;
			case KeyEvent.VK_S:
				snake.speedDown();
				break;
			default:
				break;
		}
	}


	@Override
	public void snakeMoved(Snake snake) {
	
		mainPanel.display(snake, food, ground);			
		
		if(food.isFoodEated(snake)) {
			snake.eatFood();
			
			//解决蛇身上出现食物的情况
			LinkedList<Point> temp=snake.getNode();
			Point p=ground.getPoint();
			boolean isOnSnake=true;
			while(isOnSnake)
			{
				for(int i=0;i<temp.size();i++)
				{
					//如果选取的食物点在贪吃蛇的身上，就跳出本次循环进行下一次选食物点
					if(p==temp.get(i))
					{
						isOnSnake=true;
						break;
					}
					//若到了贪吃蛇的最后一个节点所选取的食物点还不在其上，则可以绘制出该食物
					if( i==temp.size()-1 && p!=temp.get(i))
					{
						isOnSnake=false;
					}
				}
			}
			food.newFood(p);
			
			bottonPanel.repaint();
			setScore();		
		}
		
		if(ground.isGroundEated(snake)) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
		if(snake.isEatBody()) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
		
	}

	public void setScore() {
		int score = snake.getFoodCount() ;
		bottonPanel.setScore(score);
	}
	

	public void newGame() {
		ground.clear();
		
		switch (ground.getMapType()) {
			case 1:
				ground.generateRocks1();
				break;
			case 2:
				ground.generateRocks2();
				break;
			case 3:
				ground.generateRocks3();
				break;
		}
		
		food.newFood(ground.getPoint());	
		bottonPanel.setScore(0);		
		
		bottonPanel.repaint();
	}

	
	public void startGame() {
		mainPanel.requestFocus(true);	
		snake.clear();
		snake.init();		
		snake.begin();
		newGame();
	}
	
	class startHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			mainPanel.requestFocus(true);	
			snake.clear();
			snake.init();		
			snake.begin();
			newGame();
			bottonPanel.getStartButton().setEnabled(false);
		}
		
	}
	
	class pauseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.requestFocus(true);
			snake.changePause();

			if(e.getActionCommand().equals("Pause"))
			{
				
				bottonPanel.getPauseButton().setText("Continue");
			}
			else
			{
				bottonPanel.getPauseButton().setText("Pause");
			}
		}
		
	}

	class endHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
	}
	
	class mapItem1Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(1);
			startGame();
		}
		
	}
	
	class mapItem2Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(2);
			startGame();
		}
		
	}
	
	class mapItem3Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(3);	
			startGame();
		}
		
	}

	
}

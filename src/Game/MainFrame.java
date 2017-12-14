package Game;


import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Entities.Food;
import Entities.Ground;
import Entities.Snake;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MainFrame(new Controller(new Snake(), new Food(), new Ground(), 
				new MainPanel(), new GameMenu(),new BottonPanel()));

	}

	private MainPanel mainPanel;
	private GameMenu gameMenu;
	private Snake snake;
	private Controller controller;
	private JPanel buttonPanel;

	
	public MainFrame(Controller c) {
		
		this.controller = c;
		snake = controller.getSnake();
		gameMenu = controller.getGameMenu();
		mainPanel = controller.getGamePanel();
		buttonPanel = controller.getBottonPanel();
		
		setTitle("Snake,Let's Go!");
		setBounds(300,100,Main.Width*Main.UnitSize+250,Main.Height*Main.UnitSize+60);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = this.getContentPane(); 		
		this.setJMenuBar(gameMenu);
		
		contentPane.add(mainPanel);
		contentPane.add(buttonPanel);
		
		setResizable(false);
		setVisible(true);

		
		this.setLocation(this.getToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, this.getToolkit().getScreenSize().height
				/ 2 - this.getHeight() / 2);
		
		
		mainPanel.addKeyListener(controller);
		snake.addSnakeListener(controller);	
		controller.newGame();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}

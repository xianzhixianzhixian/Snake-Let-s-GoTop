package Game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BottonPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton endButton;
	private JLabel scoreLabel;
	private int score;
	
	public BottonPanel() {

		//setFocusable(false);
		setLayout(null);
		setBounds(653,0,440, 660);
		
		startButton = new JButton(" Let's Go ");
		startButton.setFont(new Font("Serif", Font.ROMAN_BASELINE ,18));
		startButton.setBounds(60,40, 150, 50);
		add(startButton);
		
		pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Serif", Font.ROMAN_BASELINE ,18));
		pauseButton.setBounds(60, 120, 150, 50);
		add(pauseButton);	
		
		endButton = new JButton("End");
		endButton.setFont(new Font("Serif", Font.ROMAN_BASELINE ,18));
		endButton.setBounds(60,200,150, 50);
		add(endButton);		
		
		scoreLabel = new JLabel("Your Score:");
		scoreLabel.setFont(new Font("Sans Serif",Font.BOLD,20));
		scoreLabel.setBounds(40,350,150, 30);
		add(scoreLabel);
		
		/*
		scorePanel = new JPanel();
		scorePanel.setBounds(30, 180, 150, 100);
		//scorePanel.setBackground(Color.BLUE);
		add(scorePanel);*/
		
		Color c= new Color(253,218,4);
		this.setBackground(c);
		
		this.setFocusable(true);
		
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}


	public JButton getEndButton() {
		return endButton;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sans Serif",Font.BOLD,50));
		g.drawString(score+"", 120, 450);
	}
}

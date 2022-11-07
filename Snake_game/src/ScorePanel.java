import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class ScorePanel extends JPanel implements ActionListener{
	


	Timer timer;
	JButton restartButton;
	JLabel scoreLabel;
	static final int DELAY = 125;
	public ScorePanel() {
		//this.setLayout(null);
		restartButton = new JButton("NEW GAME");
		restartButton.setFont(new Font("Agency FB", Font.BOLD, 35));
		restartButton.setBackground(new Color(135, 160, 118));
		restartButton.setBorderPainted(false);
		//restartButton.setBounds(1000, 1023, 2040, 40);
		restartButton.setLocation (500, 125);
		this.add(restartButton);
		restartButton.setFocusable(false);
		restartButton.addActionListener(this);
		
		
		scoreLabel = new JLabel("SCORE: " +  0);
		scoreLabel.setFont(new Font("Agency FB", Font.BOLD, 35));
		this.add(scoreLabel);
		//scoreLabel.addActionListener(this);
		
		this.setBackground(new Color(135, 160, 118));
		//this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		start();
		//scoreLabel.addActionListener(e -> this.locallabel.setText("SCORE: " + SnakeGame.applesEaten));
		
		
	}
	public void paintComponent(Graphics g) {
		

		super.paintComponent(g);

	}
	
	 
	       
	public void start() {
		System.out.println("startgame");
		timer = new Timer(DELAY, (ActionListener) this);
		timer.start();

	}
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.toString());
		
		if(e.getSource() == timer ) {
			System.out.println(e.getSource()+ "score");
			this.scoreLabel.setText("SCORE: " + SnakeGame.applesEaten);
		}
		if(e.getSource() == restartButton) {
			System.out.println(e.getSource() + "restart");
			new SnakeGame();
			 SnakeGame.applesEaten = 0;
		}
		
	}
}

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class GameScore extends JPanel implements ActionListener{
	


	Timer timer;
	JLabel locallabel;
	static final int DELAY = 125;
	public GameScore() {
		
		JLabel label1 = new JLabel("SCORE: " +  0);
		label1.setFont(new Font("Agency FB", Font.BOLD, 35));
		this.locallabel = label1;
		this.add(label1);
		
		this.setBackground(new Color(135, 160, 118));
		//this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		start();
		
		
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
		this.locallabel.setText("SCORE: " + SnakeGame.applesEaten);
		
	}
}

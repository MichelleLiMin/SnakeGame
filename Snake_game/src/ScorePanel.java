import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ScorePanel extends JPanel implements ActionListener {

	Timer timer;
	JButton restartButton;
	JLabel scoreLabel;
	static final int DELAY = 125;

	public ScorePanel() {
		this.setLayout(new BorderLayout());
		restartButton = new JButton("NEW GAME");
		restartButton.setFont(new Font("Agency FB", Font.BOLD, 35));
		restartButton.setBackground(new Color(86, 136, 57));
		restartButton.setBorderPainted(false);

		this.add(restartButton, BorderLayout.WEST);
		restartButton.setFocusable(false);
		restartButton.addActionListener(this);

		
		scoreLabel = new JLabel(" " + 0);
		scoreLabel.setFont(new Font("Agency FB", Font.BOLD, 35));
		ImageIcon app = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("apple.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		//ImageIcon app = new ImageIcon(getClass().getResource("apple.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		scoreLabel.setIcon(app);
		this.add(scoreLabel, BorderLayout.EAST);
		// scoreLabel.addActionListener(this);

		this.setBackground(new Color(86, 136, 57));
		// this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		start();
		// scoreLabel.addActionListener(e -> this.locallabel.setText("SCORE: " +
		// SnakeGame.applesEaten));

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
		// System.out.println(e.toString());

		if (e.getSource() == timer) {
			// System.out.println(e.getSource()+ "score");
			this.scoreLabel.setText(" " + SnakeGame.applesEaten);
		}
		if (e.getSource() == restartButton) {
			// System.out.println(e.getSource() + "restart");
			new SnakeGame();
			SnakeGame.applesEaten = 0;
		}

	}
}

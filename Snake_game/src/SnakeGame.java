import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {
	public static int applesEaten;

	public SnakeGame() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 30); // top padding
		this.getContentPane().add(new ScorePanel(), c);

		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 0, 10); // top padding
		this.getContentPane().add(new GamePanel(), c);

		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setSize(650, 750);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(86, 136, 57));
	}

	public static void main(String[] args) {

		new SnakeGame();

	}

}

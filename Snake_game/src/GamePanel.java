
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
// GUI (Graphical User Interface) 
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 125;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	// Timers are constructed by specifying both a delay parameter and an
	// ActionListener. Once the timer has been started,it waits for the initial
	// delay before firing its first ActionEvent to registered listeners
	Timer timer;
	Random random;

	public GamePanel() {
		random = new Random();
		 
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(135, 160, 118));

		// setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
		// BorderFactory.createLoweredBevelBorder()));
		this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		//
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();

	}

	// paintComponent is called "when it needs to be."
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		g.setColor(Color.black);
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3.0F));*/
		draw(g);

	}

	public void draw(Graphics g) {
		if (running) {
			// draw grid
			
			for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {

				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

			}
			// draw apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			// draw snake
			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					// snake head
					g.setColor(Color.blue);
					g.fillRoundRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE, 17, 17);
					

				} else {
					// snake body
					g.setColor(Color.black);
					// paint background
					g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE, 17, 17);
				}
			}

		} else {
			// call game over method
			gameover(g);
		}
	}

	public void newApple() {
		// random apple position
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

	}

	public void move() {
		// each bodyPart follow the previous bodyPart, follows the head
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		// define move direction
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	public void checkApple() {
		// if the snake eat the apple, bodyParts +1, score +1, generate a new apple
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			SnakeGame.applesEaten++;
			System.out.println(SnakeGame.applesEaten);
			newApple();
		}
	}

	public void checkCollisions() {
		// checks if head collides with body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// check if head touches left border
		if (x[0] < 0) {
			running = false;
		}
		// check if head touches right border
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if (!running) {

			timer.stop();
		}
	}

	public void gameover(Graphics g) {
		// Score display
		/*g.setColor(Color.black);
		g.setFont(new Font("Agency FB", Font.BOLD, 35));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + SnakeGame.applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + SnakeGame.applesEaten)) / 2,
				g.getFont().getSize());*/
		// Game Over text
		g.setColor(Color.black);
		g.setFont(new Font("Agency FB", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
	}

	// When a method in a subclass has the same name, same parameters or signature,
	// and same return type(or sub-type) as a method in its super-class, then the
	// method in the subclass is said to override the method in the super-class.
	@Override
	// The actionPerformed() method is invoked automatically whenever you click on
	// the registered component.
	public void actionPerformed(ActionEvent e) {

		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		// It controls the update() -> paint() cycle.
		// programmatically trigger a repaint of certain components (which I assume
		// calls the correct paintComponent methods downstream.
		repaint();
	}

	// An abstract adapter class for receiving keyboard events.The methods in this
	// class are empty. This class exists as convenience for creating listener
	// objects.
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch (e.getKeyCode()) {
			// cannot turn 180 degree
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;

			}
		}
	}


}

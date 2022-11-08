
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
// GUI (Graphical User Interface) 
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 30;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 130;
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
	private Image bodyH;
	private Image bodyV;
	private Image headU;
	private Image headD;
	private Image headR;
	private Image headL;
	private Image tailU;
	private Image tailD;
	private Image tailR;
	private Image tailL;
	private Image cornerTR;
	private Image cornerTL;
	private Image cornerBR;
	private Image cornerBL;
	private Image apple;

	public GamePanel() {
		random = new Random();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(86, 136, 57));

		
		//this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		loadImages();
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
		/*
		 * Graphics2D g2d = (Graphics2D) g; g2d.setStroke(new BasicStroke(3.0F));
		 */
		draw(g);

	}

	public void loadImages() {
		ImageIcon app = new ImageIcon(getClass().getResource("apple.png"));
		apple = app.getImage();
		ImageIcon headRR = new ImageIcon(getClass().getResource("headR.png"));
		headR = headRR.getImage();
		ImageIcon headLL = new ImageIcon(getClass().getResource("headL.png"));
		headL = headLL.getImage();
		ImageIcon headUU = new ImageIcon(getClass().getResource("headU.png"));
		headU = headUU.getImage();
		ImageIcon headDD = new ImageIcon(getClass().getResource("headD.png"));
		headD = headDD.getImage();
		ImageIcon bodyHH = new ImageIcon(getClass().getResource("bodyH.png"));
		bodyH = bodyHH.getImage();
		ImageIcon bodyVV = new ImageIcon(getClass().getResource("bodyV.png"));
		bodyV = bodyVV.getImage();
		ImageIcon tailRR = new ImageIcon(getClass().getResource("tailR.png"));
		tailR = tailRR.getImage();
		ImageIcon tailLL = new ImageIcon(getClass().getResource("tailL.png"));
		tailL = tailLL.getImage();
		ImageIcon tailUU = new ImageIcon(getClass().getResource("tailU.png"));
		tailU = tailUU.getImage();
		ImageIcon tailDD = new ImageIcon(getClass().getResource("tailD.png"));
		tailD = tailDD.getImage();
		ImageIcon cornerTTR = new ImageIcon(getClass().getResource("cornerTR.png"));
		cornerTR = cornerTTR.getImage();
		ImageIcon cornerTTL = new ImageIcon(getClass().getResource("cornerTL.png"));
		cornerTL = cornerTTL.getImage();
		ImageIcon cornerBBL = new ImageIcon(getClass().getResource("cornerBL.png"));
		cornerBL = cornerBBL.getImage();
		ImageIcon cornerBBR = new ImageIcon(getClass().getResource("cornerBR.png"));
		cornerBR = cornerBBR.getImage();
	}

	public void draw(Graphics g) {
		if (running) {
			// draw grid
			// to do grid color pattern
			for (int row = 0; row < SCREEN_HEIGHT / UNIT_SIZE; row++) {

				for (int col = 0; col < SCREEN_HEIGHT / UNIT_SIZE; col++) {
					if ((col + row) % 2 != 0) {
						g.setColor(new Color(161, 209, 74));
					} else {
						g.setColor(new Color(170, 215, 82));
					}
					g.fillRect(col * UNIT_SIZE, row * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

				}
			}
			// draw apple
			g.drawImage(apple, appleX, appleY, UNIT_SIZE, UNIT_SIZE, this);
			// draw snake
			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					// snake head
					if (direction == 'R') {
						// System.out.println(y[0]);
						g.drawImage(headR, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					} else if (direction == 'D') {
						// System.out.println(x[0]);
						g.drawImage(headD, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					} else if (direction == 'L') {
						g.drawImage(headL, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					} else if (direction == 'U') {
						g.drawImage(headU, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}

				} else if (i == bodyParts - 1) {
					// snake tail
					if ((y[i] == y[i - 1]) && (x[i] > x[i - 1])) {
						g.drawImage(tailL, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
					} else if ((y[i] == y[i - 1]) && (x[i] < x[i - 1])) {
						g.drawImage(tailR, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
					} else if ((x[i] == x[i - 1]) && (y[i] > y[i - 1])) {
						g.drawImage(tailU, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
					} else if ((x[i] == x[i - 1]) && (y[i] < y[i - 1])) {
						g.drawImage(tailD, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
					}
				}

				else {
					// snake body

					// top right corner
					if (((x[i] > x[i - 1]) && (y[i] < y[i + 1])) | ((x[i] > x[i + 1]) && (y[i] < y[i - 1]))) {
						// System.out.println("top right");
						g.drawImage(cornerTR, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}
					// bottom right corner
					else if (((x[i] > x[i - 1]) && (y[i] > y[i + 1])) | ((x[i] > x[i + 1]) && (y[i] > y[i - 1]))) {
						// System.out.println("bottom right");
						g.drawImage(cornerBR, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}
					// top left corner
					else if (((x[i] < x[i - 1]) && (y[i] < y[i + 1])) | ((x[i] < x[i + 1]) && (y[i] < y[i - 1]))) {
						// System.out.println("top left");
						g.drawImage(cornerTL, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}
					// bottom left corner
					else if (((x[i] < x[i + 1]) && (y[i] > y[i - 1])) | ((x[i] < x[i - 1]) && (y[i] > y[i + 1]))) {
						// System.out.println("bottom left");
						g.drawImage(cornerBL, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}
					// body horizontal
					else if (y[i] == y[i - 1]) {
						g.drawImage(bodyH, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
					}
					// body vertical
					else if (x[i] == x[i - 1]) {
						g.drawImage(bodyV, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);

					}

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
			// System.out.println(SnakeGame.applesEaten);
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
		if (x[0] >= SCREEN_WIDTH) {
			running = false;
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if (y[0] >= SCREEN_HEIGHT) {
			running = false;
		}
		if (!running) {

			timer.stop();
		}
	}

	public void gameover(Graphics g) {
		// draw grid
		for (int row = 0; row < SCREEN_HEIGHT / UNIT_SIZE; row++) {

			for (int col = 0; col < SCREEN_HEIGHT / UNIT_SIZE; col++) {
				if ((col + row) % 2 != 0) {
					g.setColor(new Color(161, 209, 74));
				} else {
					g.setColor(new Color(170, 215, 82));
				}
				g.fillRect(col * UNIT_SIZE, row * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

			}
		}

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
			case KeyEvent.VK_SPACE:
				new SnakeGame();
				SnakeGame.applesEaten = 0;
				break;

			}
		}
	}

}

package games;

import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.*;
import javax.swing.*;

public class PongPanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (5 / 9.0));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	boolean gameOver = false;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	PongPaddle paddle1;
	PongPaddle paddle2;
	PongBall ball;
	PongScore score;

	PongPanel() {
		newPaddles();
		newBall();
		score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this);
		gameThread.start();
	}

	public void newBall() {
		random = new Random();
		ball = new PongBall((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	public void newPaddles() {
		paddle1 = new PongPaddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PongPaddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g, score.player1, score.player2);
	}

	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	public void checkCollision() {
		// Stops paddles at window edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

		// bounce ball off top & bottom window edges
		if (ball.y <= 0)
			ball.setYDirection(-ball.yVelocity);
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER)
			ball.setYDirection(-ball.yVelocity);

		// bounces ball off of our paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

		// give a player 1 point and creates new paddles and a new ball
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (!gameOver) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
			if (score.player1 == 10) {
				gameOver = true;
			}
			if (score.player2 == 10) {
				gameOver = true;
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				new Pong();
				break;
			case KeyEvent.VK_ESCAPE:
				new Game_Selection();
				break;
			}
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}

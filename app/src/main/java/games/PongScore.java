package games;

import java.awt.*;
import java.io.Serial;

public class PongScore extends Rectangle {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;

	PongScore(int GAME_WIDTH, int GAME_HEIGHT) {
		PongScore.GAME_WIDTH = GAME_WIDTH;
		PongScore.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
		g.drawString(player1 / 10 + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 85, 50);
		g.drawString(player2 / 10 + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 20, 50);
		if (x == 10) {
			g.setFont(new Font("Ink Free", Font.PLAIN, 30));
			g.drawString("Player 1 won", 20, 30);
			g.drawString("Press 'Enter' to play again", 20, 60);
			g.drawString("Press 'Esc' to play a different game", 20, 90);
		}
		if (y == 10) {
			g.setFont(new Font("Ink Free", Font.PLAIN, 30));
			g.drawString("Player 2 won", 815, 30);
			g.drawString("Press 'enter' to play again", 650, 60);
			g.drawString("Press 'Esc' to play a different game", 530, 90);
		}
	}
}

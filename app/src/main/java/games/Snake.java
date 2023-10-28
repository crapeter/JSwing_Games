package games;

import javax.swing.JFrame;

public class Snake extends JFrame {
	public SnakePanel snake;

	Snake() {
		snake = new SnakePanel();
		this.add(new SnakePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}

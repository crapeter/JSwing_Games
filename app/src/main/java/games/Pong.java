package games;

import java.awt.*;
import java.io.Serial;
import javax.swing.*;

public class Pong extends JFrame {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	PongPanel pongPanel;

	Pong() {
		pongPanel = new PongPanel();
		this.add(pongPanel);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}

package jSwing_Games;

import java.awt.*;
import javax.swing.*;

public class Pong extends JFrame {
	/**
	 * 
	 */
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

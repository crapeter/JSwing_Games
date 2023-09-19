package jSwing_Games;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game_Selection implements ActionListener {
	JFrame frame = new JFrame();
	JPanel button = new JPanel();
	JButton[] buttons = new JButton[6];

	Game_Selection() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 800);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		button.setLayout(new GridLayout(5, 1));
		button.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 5; i++) {
			buttons[i] = new JButton();
			button.add(buttons[i]);
			buttons[i].setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 100));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.BLACK);
			if (i == 0) {
				buttons[i].setText("Snake");
				buttons[i].setForeground(Color.GREEN);
			}
			if (i == 1) {
				buttons[i].setText("Tic Tac Toe");
				buttons[i].setForeground(Color.BLUE);
			}
			if (i == 2) {
				buttons[i].setText("Pong");
				buttons[i].setForeground(Color.RED);
			}
			if (i == 3) {
				buttons[i].setText("Connect Four");
				buttons[i].setForeground(Color.YELLOW);
			}
			if (i == 4) {
				buttons[i].setText("Battleship");
				buttons[i].setForeground(Color.CYAN);
			}
		}

		frame.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == buttons[i]) {
				String gameChosen = buttons[i].getText();
				switch (gameChosen) {
				case "Snake":
					frame.setVisible(false);
					new Snake();
					break;
				case "Tic Tac Toe":
					frame.setVisible(false);
					new TicTacToe();
					break;
				case "Pong":
					frame.setVisible(false);
					new Pong();
					break;
				case "Connect Four":
					frame.setVisible(false);
					new ConnectFour();
					break;
				case "Battleship":
					frame.setVisible(false);
					new Battleship();
					break;
				}
			}
		}
	}
}
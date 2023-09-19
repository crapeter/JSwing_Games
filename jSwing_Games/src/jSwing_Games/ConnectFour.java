package jSwing_Games;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ConnectFour implements ActionListener {
	Random rand = new Random();
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel button = new JPanel();
	JLabel textField = new JLabel();
	JButton[] buttons = new JButton[60];
	boolean player1_turn;
	int count = 0;

	public ConnectFour() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250, 900);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		textField.setBackground(new Color(25, 25, 25));
		textField.setForeground(new Color(25, 255, 0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Connect Four");
		textField.setOpaque(true);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);

		button.setLayout(new GridLayout(6, 10));
		button.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 60; i++) {
			buttons[i] = new JButton();
			button.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		titlePanel.add(textField);
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(button);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 60; i++) {
			if (e.getSource() == buttons[i]) {
				if (player1_turn) {
					if (buttons[i].getText() == "") {
						// placing move on bottom level
						if (i >= 50) {
							buttons[i].setForeground(Color.RED);
							buttons[i].setBackground(Color.RED);
							buttons[i].setText("R");
							player1_turn = false;
							textField.setText("Yellow's turn");
						}
						// making sure the players move is possible
						else if (buttons[i + 10].getText() != "") {
							buttons[i].setForeground(Color.RED);
							buttons[i].setBackground(Color.RED);
							buttons[i].setText("R");
							player1_turn = false;
							textField.setText("Yellow's turn");
						}
						check();
					}
				} else {
					if (buttons[i].getText() == "") {
						// placing move on bottom level
						if (i >= 50) {
							buttons[i].setForeground(Color.YELLOW);
							buttons[i].setBackground(Color.YELLOW);
							buttons[i].setText("Y");
							player1_turn = true;
							textField.setText("Red's turn");
						}
						// making sure the players move is possible
						else if (buttons[i + 10].getText() != "") {
							buttons[i].setForeground(Color.YELLOW);
							buttons[i].setBackground(Color.YELLOW);
							buttons[i].setText("Y");
							player1_turn = true;
							textField.setText("Red's turn");
						}
						check();
					}
				}
			}
		}
		// Play again or return to starting screen buttons
		if (e.getSource() == buttons[4]) {
			if (buttons[4].getText() == "Reset") {
				frame.setVisible(false);
				new ConnectFour();
			}
		}
		if (e.getSource() == buttons[5]) {
			if (buttons[5].getText() == "Menu") {
				frame.setVisible(false);
				new Game_Selection();
			}
		}
	}

	public void firstTurn() {
		if (rand.nextInt(2) == 0) {
			player1_turn = true;
			textField.setText("Red's turn");

		} else {
			player1_turn = false;
			textField.setText("Yellow's turn");
		}
	}

	public void check() {
		for (int i = 59; i > 29; i--) {
			if (buttons[i].getText() != "") {
				// checking everything except left diagonals
				if (i % 10 < 3) {
					// Checking if there are 4 in a row in the same row
					if (buttons[i].getText() == buttons[i + 1].getText()
							&& buttons[i].getText() == buttons[i + 2].getText()
							&& buttons[i].getText() == buttons[i + 3].getText()) {
						winner(buttons[i].getText(), i, i + 1, i + 2, i + 3);
					}
					// Checking if there are 4 in a row in the same column
					else if (buttons[i].getText() == buttons[i - 10].getText()
							&& buttons[i].getText() == buttons[i - 20].getText()
							&& buttons[i].getText() == buttons[i - 30].getText()) {
						winner(buttons[i].getText(), i, i - 10, i - 20, i - 30);
					}
					// Checking if there are 4 in a row if a right diagonal
					else if (buttons[i].getText() == buttons[i - 9].getText()
							&& buttons[i].getText() == buttons[i - 18].getText()
							&& buttons[i].getText() == buttons[i - 27].getText()) {
						if (i % 10 < 7)
							winner(buttons[i].getText(), i, i - 9, i - 18, i - 27);
					}
				}
				// checking everything
				else if (i % 10 >= 3 && i % 10 < 7) {
					// Checking if there are 4 in a row in the same row
					if (buttons[i].getText() == buttons[i + 1].getText()
							&& buttons[i].getText() == buttons[i + 2].getText()
							&& buttons[i].getText() == buttons[i + 3].getText()) {
						winner(buttons[i].getText(), i, i + 1, i + 2, i + 3);
					}
					// Checking if there are 4 in a row in the same column
					else if (buttons[i].getText() == buttons[i - 10].getText()
							&& buttons[i].getText() == buttons[i - 20].getText()
							&& buttons[i].getText() == buttons[i - 30].getText()) {
						winner(buttons[i].getText(), i, i - 10, i - 20, i - 30);
					}
					// Checking if there are 4 in a row if a left diagonal
					else if (buttons[i].getText() == buttons[i - 11].getText()
							&& buttons[i].getText() == buttons[i - 22].getText()
							&& buttons[i].getText() == buttons[i - 33].getText()) {
						if (i % 10 > 2)
							winner(buttons[i].getText(), i, i - 11, i - 22, i - 33);
					}
					// Checking if there are 4 in a row if a right diagonal
					else if (buttons[i].getText() == buttons[i - 9].getText()
							&& buttons[i].getText() == buttons[i - 18].getText()
							&& buttons[i].getText() == buttons[i - 27].getText()) {
						if (i % 10 < 7)
							winner(buttons[i].getText(), i, i - 9, i - 18, i - 27);
					}
				}
				// checking everything except right diagonals and rows
				else {
					// Checking if there are 4 in a row in the same column
					if (buttons[i].getText() == buttons[i - 10].getText()
							&& buttons[i].getText() == buttons[i - 20].getText()
							&& buttons[i].getText() == buttons[i - 30].getText()) {
						winner(buttons[i].getText(), i, i - 10, i - 20, i - 30);
					}
					// Checking if there are 4 in a row if a left diagonal
					else if (buttons[i].getText() == buttons[i - 11].getText()
							&& buttons[i].getText() == buttons[i - 22].getText()
							&& buttons[i].getText() == buttons[i - 33].getText()) {
						if (i % 10 > 2)
							winner(buttons[i].getText(), i, i - 11, i - 22, i - 33);
					}
				}
			}
		}

		// checking for a tie
		for (int i = 0; i < 60; i++)
			if (buttons[i].getText() != "")
				count++;

		if (count == 60) {
			textField.setText("It's a tie");
			for (int i = 0; i < 60; i++) {
				if (buttons[i].getText() == "Y") {
					buttons[i].setBackground(Color.yellow);
					buttons[i].setForeground(Color.yellow);
				} else {
					buttons[i].setBackground(Color.red);
					buttons[i].setForeground(Color.red);
				}
			}
			buttons[4].setEnabled(true);
			buttons[4].setText("Reset");
			buttons[4].setForeground(Color.BLACK);
			buttons[4].setFont(new Font("Ink Free", Font.BOLD, 30));

			buttons[5].setEnabled(true);
			buttons[5].setText("Menu");
			buttons[5].setForeground(Color.BLACK);
			buttons[5].setFont(new Font("Ink Free", Font.BOLD, 30));
		}
		count = 0;
	}

	public void winner(String winner, int a, int b, int c, int d) {
		for (int i = 0; i < 60; i++) {
			if (buttons[i].getText() == "Y") {
				buttons[i].setEnabled(false);
				buttons[i].setBackground(Color.yellow);
				buttons[i].setForeground(Color.yellow);
			} else if (buttons[i].getText() == "R") {
				buttons[i].setEnabled(false);
				buttons[i].setBackground(Color.red);
				buttons[i].setForeground(Color.red);
			} else {
				buttons[i].setEnabled(false);
				buttons[i].setBackground(Color.white);
			}
		}

		// displaying where the four in a row occurred
		buttons[a].setBackground(Color.black);
		buttons[b].setBackground(Color.black);
		buttons[c].setBackground(Color.black);
		buttons[d].setBackground(Color.black);

		// restart button
		buttons[4].setEnabled(true);
		buttons[4].setText("Reset");
		buttons[4].setForeground(Color.BLACK);
		buttons[4].setFont(new Font("Ink Free", Font.BOLD, 30));

		// menu button
		buttons[5].setEnabled(true);
		buttons[5].setText("Menu");
		buttons[5].setForeground(Color.BLACK);
		buttons[5].setFont(new Font("Ink Free", Font.BOLD, 30));

		// displaying the winner
		if (winner.equals("R"))
			textField.setText("Red won");
		else
			textField.setText("Yellow won");
	}
}
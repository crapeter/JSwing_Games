package jSwing_Games;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
	Random rand = new Random();
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel button = new JPanel();
	JLabel textField = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;

	TicTacToe() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		textField.setBackground(new Color(25, 25, 25));
		textField.setForeground(new Color(25, 255, 0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Tic-Tac-Toe");
		textField.setOpaque(true);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);

		button.setLayout(new GridLayout(3, 3));
		button.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
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
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (player1_turn) {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(Color.RED);
						buttons[i].setText("X");
						player1_turn = false;
						textField.setText("O's turn");
						check();
					}
				} else {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(Color.BLUE);
						buttons[i].setText("O");
						player1_turn = true;
						textField.setText("X's turn");
						check();
					}
				}
			}
		}

		if (e.getSource() == buttons[1]) {
			if (buttons[1].getText() == "Menu") {
				frame.setVisible(false);
				new Game_Selection();
			}
		}

		if (e.getSource() == buttons[4]) {
			if (buttons[4].getText() == "Play Again") {
				frame.setVisible(false);
				new TicTacToe();
			}
		}
	}

	public void firstTurn() {
		if (rand.nextInt(2) == 0) {
			player1_turn = true;
			textField.setText("X's turn");

		} else {
			player1_turn = false;
			textField.setText("O's turn");
		}
	}

	public void checkWinner(String letter) {
		// Horizontal
		if ((buttons[0].getText() == letter) && (buttons[1].getText() == letter) && (buttons[2].getText() == letter))
			winner(0, 1, 2);
		if ((buttons[3].getText() == letter) && (buttons[4].getText() == letter) && (buttons[5].getText() == letter))
			winner(3, 4, 5);
		if ((buttons[6].getText() == letter) && (buttons[7].getText() == letter) && (buttons[8].getText() == letter))
			winner(6, 7, 8);

		// Vertical
		if ((buttons[0].getText() == letter) && (buttons[3].getText() == letter) && (buttons[6].getText() == letter))
			winner(0, 3, 6);
		if ((buttons[1].getText() == letter) && (buttons[4].getText() == letter) && (buttons[7].getText() == letter))
			winner(1, 4, 7);
		if ((buttons[2].getText() == letter) && (buttons[5].getText() == letter) && (buttons[8].getText() == letter))
			winner(2, 5, 8);

		// Diagonal
		if ((buttons[0].getText() == letter) && (buttons[4].getText() == letter) && (buttons[8].getText() == letter))
			winner(0, 4, 8);
		if ((buttons[2].getText() == letter) && (buttons[4].getText() == letter) && (buttons[6].getText() == letter))
			winner(2, 4, 6);
	}

	public void check() {
		checkWinner("X");
		checkWinner("O");

		// Checking for tie
		int count = 0;
		for (int i = 0; i < 9; i++) {
			if (buttons[i].getText() != "")
				count++;
		}
		if (count == 9) {
			textField.setText("It's a tie");
			for (int i = 0; i < 9; i++) {
				if (i == 4) {
					buttons[4].setBackground(Color.RED);
					buttons[4].setForeground(Color.BLACK);
					buttons[4].setText("Play Again");
					buttons[4].setFont(new Font("Ink Free", Font.BOLD, 40));
				} else if (i == 1) {
					buttons[1].setBackground(Color.RED);
					buttons[1].setForeground(Color.BLACK);
					buttons[1].setText("Menu");
					buttons[1].setFont(new Font("Ink Free", Font.BOLD, 40));
				} else {
					buttons[i].setEnabled(false);
					buttons[i].setBackground(Color.RED);
				}
			}
		}
		count = 0;
	}

	public void winner(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);

		String letter = buttons[a].getText();

		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				buttons[4].setText("Play Again");
				buttons[4].setForeground(Color.BLACK);
				buttons[4].setFont(new Font("Ink Free", Font.BOLD, 40));
			} else if (i == 1) {
				buttons[1].setText("Menu");
				buttons[1].setForeground(Color.BLACK);
				buttons[1].setFont(new Font("Ink Free", Font.BOLD, 40));
			} else {
				buttons[i].setEnabled(false);
			}
		}
		textField.setText(letter + " wins");
	}
}

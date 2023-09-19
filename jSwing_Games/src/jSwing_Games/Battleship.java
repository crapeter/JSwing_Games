package jSwing_Games;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Battleship implements ActionListener {
	Random rand = new Random();
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel button = new JPanel();
	Border border = new LineBorder(Color.BLUE, 1, true);
	JLabel textField = new JLabel();
	JButton[] buttons = new JButton[100];
	int count = 0;

	public Battleship() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250, 950);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		textField.setBackground(Color.black);
		textField.setForeground(Color.cyan);
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Destroy the battleships");
		textField.setOpaque(true);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);

		button.setLayout(new GridLayout(10, 10));
		button.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 100; i++) {
			buttons[i] = new JButton();
			button.add(buttons[i]);
			button.setBorder(border);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setBorder(border);
			buttons[i].setBackground(Color.blue);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		titlePanel.add(textField);
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(button);

		setup();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 100; i++) {
			if (e.getSource() == buttons[i]) {
				if (buttons[i].getText() == "s") {
					buttons[i].setText("!");
					buttons[i].setBackground(Color.gray);
					buttons[i].setForeground(Color.red);
					gameOver();
				} else if (buttons[i].getText() == "") {
					buttons[i].setText("x");
					buttons[i].setForeground(Color.red);
					gameOver();
				}
			}
		}
		if (e.getSource() == buttons[4]) {
			if (buttons[4].getText() == "r") {
				frame.setVisible(false);
				new Battleship();
			}
		}
		if (e.getSource() == buttons[5]) {
			if (buttons[5].getText() == "e") {
				frame.setVisible(false);
				new Game_Selection();
			}
		}
	}

	public void gameOver() {
		count = 0;

		for (int i = 0; i < 100; i++) {
			if (buttons[i].getText() == "!")
				count++;
		}

		if (count == 17) {
			textField.setText("Game Over");
			for (int i = 0; i < 100; i++) {
				buttons[i].setEnabled(false);
				buttons[i].setForeground(Color.red);
				if (i == 4) {
					buttons[i].setEnabled(true);
					buttons[i].setText("r");
					buttons[i].setForeground(Color.black);
				}
				if (i == 5) {
					buttons[i].setEnabled(true);
					buttons[i].setText("e");
					buttons[i].setForeground(Color.black);
				}
			}
		}
	}

	public void setup() {
		int shipLen[] = { 2, 3, 3, 4, 5 };

		int shipPos = rand.nextInt(100);
		int dir = rand.nextInt(2);

		boolean ship1 = false;
		boolean ship2 = false;
		boolean ship3 = false;
		boolean ship4 = false;

		for (int i = shipLen.length - 1; i >= 0; i--) {
			// horizontal ships
			if (dir == 0) {
				if (shipLen[i] == 5) {
					while (!ship1) {
						// right
						if (shipPos % 10 < 6) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 1].getText() == ""
									&& buttons[shipPos + 2].getText() == "" && buttons[shipPos + 3].getText() == ""
									&& buttons[shipPos + 4].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship1 = true;
							}
						}
						// left
						else if (shipPos % 10 > 3) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 1].getText() == ""
									&& buttons[shipPos - 2].getText() == "" && buttons[shipPos - 3].getText() == ""
									&& buttons[shipPos - 4].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship1 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
				if (shipLen[i] == 4) {
					while (!ship2) {
						// right
						if (shipPos % 10 < 7) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 1].getText() == ""
									&& buttons[shipPos + 2].getText() == "" && buttons[shipPos + 3].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship2 = true;
							}
						}
						// left
						else if (shipPos % 10 > 2) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 1].getText() == ""
									&& buttons[shipPos - 2].getText() == "" && buttons[shipPos - 3].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship2 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
				if (shipLen[i] == 3) {
					while (!ship3) {
						// right
						if (shipPos % 10 < 8) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 1].getText() == ""
									&& buttons[shipPos + 2].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship3 = true;
							}
						}
						// left
						else if (shipPos % 10 > 1) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 1].getText() == ""
									&& buttons[shipPos - 2].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship3 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
					ship3 = false;
				}
				if (shipLen[i] == 2) {
					while (!ship4) {
						// right
						if (shipPos % 10 < 9) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 1].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship4 = true;
							}
						}
						// left
						else if (shipPos % 10 > 0) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 1].getText() == "") {
								for (int j = 0; j < shipLen[i]; j++) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship4 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
			}
			// vertical ships
			else {
				if (shipLen[i] == 5) {
					while (!ship1) {
						// right
						if (shipPos / 10 < 6) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 10].getText() == ""
									&& buttons[shipPos + 20].getText() == "" && buttons[shipPos + 30].getText() == ""
									&& buttons[shipPos + 40].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship1 = true;
							}
						}
						// left
						else if (shipPos / 10 > 3) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 10].getText() == ""
									&& buttons[shipPos - 20].getText() == "" && buttons[shipPos - 30].getText() == ""
									&& buttons[shipPos - 40].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship1 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
				if (shipLen[i] == 4) {
					while (!ship2) {
						// right
						if (shipPos / 10 < 7) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 10].getText() == ""
									&& buttons[shipPos + 20].getText() == "" && buttons[shipPos + 30].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship2 = true;
							}
						}
						// left
						else if (shipPos / 10 > 2) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 10].getText() == ""
									&& buttons[shipPos - 20].getText() == "" && buttons[shipPos - 30].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship2 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
				if (shipLen[i] == 3) {
					while (!ship3) {
						// right
						if (shipPos / 10 < 8) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 10].getText() == ""
									&& buttons[shipPos + 20].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship3 = true;
							}
						}
						// left
						else if (shipPos / 10 > 1) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 10].getText() == ""
									&& buttons[shipPos - 20].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship3 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
					ship3 = false;
				}
				if (shipLen[i] == 2) {
					while (!ship4) {
						// right
						if (shipPos / 10 < 9) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos + 10].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos + j].setText("s");
									buttons[shipPos + j].setForeground(Color.blue);
								}
								ship4 = true;
							}
						}
						// left
						else if (shipPos / 10 > 0) {
							if (buttons[shipPos].getText() == "" && buttons[shipPos - 10].getText() == "") {
								for (int j = 0; j < shipLen[i] * 10; j += 10) {
									buttons[shipPos - j].setText("s");
									buttons[shipPos - j].setForeground(Color.blue);
								}
								ship4 = true;
							}
						}
						shipPos = rand.nextInt(100);
					}
				}
			}
			dir = rand.nextInt(2);
		}
	}
}
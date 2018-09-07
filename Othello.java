import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Othello extends JFrame implements ActionListener {
	JButton[][] button = new JButton[8][8];
	boolean blacksTurn = true;

	static final int NORTH = 1;
	static final int NE = 2;
	static final int EAST = 3;
	static final int SE = 4;
	static final int SOUTH = 5;
	static final int SW = 6;
	static final int WEST = 7;
	static final int NW = 8;

	public static void main(String[] args) {
		new Othello();
	}

	public Othello() {
		super("Othello");

		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//grid container
		GridLayout grid = new GridLayout(8, 8);
		setLayout(grid);

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				button[i][j] = new JButton();
				button[i][j].addActionListener(this);
				add(button[i][j]);
			}
		}
		// opens the window at the time of execution
		setVisible(true);
		startGame();
	}

	public void startGame() {
		button[3][3].setBackground(Color.white);
		button[3][4].setBackground(Color.black);
		button[4][3].setBackground(Color.black);
		button[4][4].setBackground(Color.white);

		blacksTurn = true;
		markValidMoves();
	}

	public int markValidMoves() {
		int retVal = 0;

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (isValidCell(i, j)) {
					button[i][j].setBackground(blacksTurn ? Color.blue : Color.red);
					retVal++;
				} else {
					if (button[i][j].getBackground() != Color.black && button[i][j].getBackground() != Color.white) {
						button[i][j].setBackground(Color.green);
					}
				}
			}
		}

		return retVal;
	}

	public boolean isValidCell(int i, int j) {
		Color backgroundColor = button[i][j].getBackground();
		if (backgroundColor == Color.black || backgroundColor == Color.white) {
			return false;
		} else {
			int initI, initJ;

			// dir north (i--)
			if (checkValidityInDirection(NORTH, i, j)) {
				return true;
			}

			// dir north east (i--, j++)
			if (checkValidityInDirection(NE, i, j)) {
				return true;
			}

			// dir east (j++)
			if (checkValidityInDirection(EAST, i, j)) {
				return true;
			}

			// dir south east (i++, j++)
			if (checkValidityInDirection(SE, i, j)) {
				return true;
			}

			// dir south (i++)
			if (checkValidityInDirection(SOUTH, i, j)) {
				return true;
			}

			// dir south west (i++, j--)
			if (checkValidityInDirection(SW, i, j)) {
				return true;
			}

			// dir west (j--)
			if (checkValidityInDirection(WEST, i, j)) {
				return true;
			}

			// dir north west (i--, j--)
			if (checkValidityInDirection(NW, i, j)) {
				return true;
			}

			// invalid in all directions
			return false;
		}
	}

	
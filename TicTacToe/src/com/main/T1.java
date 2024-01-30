package com.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class T1 extends JFrame implements ItemListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[9];
	private JButton resetButton;
	private JButton startButton;
	private JPanel welcomePanel;
	private boolean playerXTurn = true;
	private Icon x = new ImageIcon("C://Users//mgarg//Desktop//New folder (2)//TicTacToe//src//com//main//X.jpg");
	private Icon o = new ImageIcon("C://Users//mgarg//Desktop//New folder (2)//TicTacToe//src//com//main//O.JPG");
	
	
	int a[][] = { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
			{ 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };
	int a1[][] = { { 10, 1, 2, 3, 11 }, { 10, 1, 4, 7, 11 }, { 10, 1, 5, 9, 11 }, { 10, 2, 5, 8, 11 },
			{ 10, 3, 5, 7, 11 }, { 10, 3, 6, 9, 11 }, { 10, 4, 5, 6, 11 }, { 10, 7, 8, 9, 11 } };
	
	public T1() {
		super("Tic Tac Toe - By Manan");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(330, 450);
		setLayout(null); // Using null layout for manual positioning of components

		welcomeBoard();

		setLocationRelativeTo(); // Center the frame on the screen
		setVisible(true);
	}

	public void welcomeBoard() {
		welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
		welcomePanel.setBounds(0, 0, 330, 450);

		JLabel welcomeLabel = new JLabel("Created by Manan Garg");
		welcomeLabel.setBounds(80, 100, 200, 20);
		welcomePanel.add(welcomeLabel);

		startButton = new JButton("Start");
		startButton.setBounds(120, 150, 100, 50);
		startButton.addActionListener(this);
		welcomePanel.add(startButton);

		add(welcomePanel);
	}

	private void initializeComponents() {
		// Game board buttons
		int x = 10, y = 10, j = 0;
		for (int i = 0; i < 9; i++, x += 100, j++) {
			buttons[i] = new JButton();
			if (j == 3) {
				j = 0;
				y += 100;
				x = 10;
			}
			buttons[i].setBounds(x, y, 100, 100);
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}

		// Reset button
		resetButton = new JButton("RESET");
		resetButton.setBounds(100, 350, 100, 50);
		add(resetButton);

		// TODO: Add action listeners for buttons and radio buttons
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JButton button : buttons) {
					button.setIcon(null);
				}
				playerXTurn = true;
			}
		});
	}

	public void setLocationRelativeTo() {
		// Center the frame on the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			remove(welcomePanel);
			initializeComponents();
			repaint();
		}	else if(e.getSource() == resetButton)	{
			resetGame();
			return;
		}	else	{
			for(int i=0;i<9;i++)	{
				if(e.getSource() == buttons[i])	{
					if(buttons[i].getIcon() == null)	{
						if(playerXTurn)	{
							buttons[i].setIcon(x);
						}	else	{
							buttons[i].setIcon(o);
						}
						playerXTurn = !playerXTurn;
					}
				}
			}
		}
		
		if (checkForWin()) {
			// Display win message
			String winner = playerXTurn ? "Player O" : "Player X";
			String message = "Congratulations! " + winner + " wins!";
			JOptionPane.showMessageDialog(this, message);
			// Reset the game
			resetGame();
		} else if (checkForTie()) {
			// Display tie message
			JOptionPane.showMessageDialog(this, "It's a tie!");
			// Reset the game
			resetGame();
		}
		
	}
	
	private void resetGame() {
		// Reset all buttons and turn to Player X
		for (JButton button : buttons) {
			button.setIcon(null);
		}
		playerXTurn = true;
		new T1();
	}
	
	private boolean checkForWin() {
		// Check all possible winning combinations
		for (int i = 0; i < 8; i++) {
			int pos1 = a[i][1] - 1;
			int pos2 = a[i][2] - 1;
			int pos3 = a[i][3] - 1;

			if (buttons[pos1].getIcon() != null && buttons[pos1].getIcon().equals(buttons[pos2].getIcon())
					&& buttons[pos2].getIcon().equals(buttons[pos3].getIcon())) {
				// Winning combination found
				return true;
			}
		}
		return false; // No winning combination found
	}

	private boolean checkForTie() {
		// Check if all positions on the game board are filled
		for (JButton button : buttons) {
			if (button.getIcon() == null) {
				// Empty position found, game is not tied
				return false;
			}
		}
		// All positions are filled, game is tied
		return true;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}

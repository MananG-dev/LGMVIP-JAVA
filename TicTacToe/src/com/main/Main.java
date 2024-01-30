package com.main;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TicTacToe game = new TicTacToe();
			game.setVisible(true);
		});
	}
}

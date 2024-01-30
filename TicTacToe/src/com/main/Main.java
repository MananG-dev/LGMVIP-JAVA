package com.main;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			T1 game = new T1();
			game.setVisible(true);
		});
	}
}

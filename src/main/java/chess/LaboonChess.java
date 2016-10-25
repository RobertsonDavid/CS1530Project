package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LaboonChess {

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		JFrame game = new GUI(board);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.pack();
  	game.setResizable(true);
  	game.setLocationRelativeTo(null);
  	game.setVisible(true);
	}

}

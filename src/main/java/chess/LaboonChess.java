package chess;

public class LaboonChess {
	
	private static GUI gui;

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		gui = new GUI(board);
	}

}

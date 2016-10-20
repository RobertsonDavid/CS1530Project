package chess;

public class ChessPiece {


	protected String type;
	protected boolean side; //true if white, false if black
	protected boolean firstMove;
	protected boolean topOfBoard;
	protected String pieceValue;
	protected int row;
	protected int column;
	protected int[] position = new int[2]; //this will only store the position of an individual piece in the form [row, column]

	public String getType() {
		return this.type;
	}

	public boolean getSide() {
		return this.side;
	}

	public ChessPiece(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
		this.type = type;
		this.side = side;
		this.firstMove = firstMove;
		this.topOfBoard = topOfBoard;
		this.row = row;
		this.column = column;
		this.position[0] = row;
		this.position[1] = column;
	}

	public void updateCoord(int newrow, int newcolumn) {
		this.row = newrow;
		this.column = newcolumn;
		this.position[0] = newrow;
		this.position[1] = newcolumn;
	}

	//checks to see if space is open
	public boolean checkMove(int [][] board,int x, int y) {
		//change 0 to null when object entered
		if (board[y][x] == 0){
			return true;
		}
		else
			return false;
	}

}

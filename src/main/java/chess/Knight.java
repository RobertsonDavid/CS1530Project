package chess;

public class Knight implements ChessPiece {

  protected String type;
	protected boolean side; //true if top, false if bottom
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

  public boolean getFirstMove() {
    return this.firstMove;
  }

  public boolean getTopOfBoard() {
    return this.topOfBoard;
  }

  public Knight(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
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

  public boolean checkMove(ChessBoard board,int x, int y) {
		//change 0 to null when object entered
		if (board.getPieceAt(x, y) == null){
			return true;
		}
		else
			return false;
	}

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(ChessBoard board, int newRow, int newCol) {

  	  if(checkMove(board, newRow, newCol) == false){
      	return this.position;
      }

      if(Math.abs(newRow - this.row) == 2 && Math.abs(newCol - this.column) == 1){
        updateCoord(newRow, newCol);
        return this.position;
      }

      if(Math.abs(newRow - this.row) == 1 && Math.abs(newCol - this.column) == 2){
        updateCoord(newRow, newCol);
        return this.position;
      }

      return this.position;
    }

}

package chess;

public class King implements ChessPiece {

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

  public King(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
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

    //moving past one move to the left or right
    if(Math.abs(newRow - this.row) > 1 || Math.abs(newCol - this.column) > 1) {
      //trying to move more than one space (castling) when king has already moved
      if(this.firstMove == false){
        return this.position;
      }

      //Castling Kingside
      if(newCol - this.column == 2 && this.row == newRow){
        if(board.getPieceAt(newRow, (this.column + 1)) != null || board.getPieceAt(newRow, (this.column + 2)) != null){
          //can't castle pieces in the way
          return this.position;
        }
        else{
          //move rook and then move king
          Rook r = new Rook("rook", this.side, true, this.topOfBoard, this.row, this.column+3);
          r.move(board, newRow, newCol-2);

          this.firstMove = false;
          updateCoord(newRow, newCol);
          return this.position;
        }
      }
      //Castling Queenside
      else if(this.column - newCol == 2 && this.row == newRow){
        if(board.getPieceAt(newRow, (this.column - 1)) != null || board.getPieceAt(newRow, (this.column - 2)) != null || 
              board.getPieceAt(newRow, (this.column - 3)) != null){
          //cant castle pieces in the way
          return this.position;
        }
        else{
          //move rook and then move king
          Rook r = new Rook("rook", this.side, true, this.topOfBoard, this.row, this.column-4);
          r.move(board, newRow, newCol-2);

          this.firstMove = false;
          updateCoord(newRow, newCol);
          return this.position;
        }
      }

      else{
        return this.position;
      }
    }
    //normal move
    else{
      this.firstMove = false;
      updateCoord(newRow, newCol);
      return this.position;
    }
  }

}

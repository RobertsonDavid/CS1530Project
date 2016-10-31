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

      //moving one to the left or right
    if(Math.abs(newRow - this.row) > 1 || Math.abs(newCol - this.column) > 1) {
      
      if(this.firstMove){
        return this.position;
      }
      //Castling logic
      if(newCol - this.column == 2 && this.row == newRow){
        //Castle kingside
        if(board.getPieceAt(newRow, (this.column + 1)) != null || board.getPieceAt(newRow, (this.column + 2)) != null){
          //cant castle pieces in the way
          return this.position;
        }
        else{
          this.firstMove = false;
          updateCoord(newRow, newCol);
          return this.position;
        }
      }
      else if(this.column - newCol == 3 && this.column == newRow){
        //castling queenside
        if(board.getPieceAt(newRow, (this.column - 1)) != null || board.getPieceAt(newRow, (this.column - 2)) != null || board.getPieceAt(newRow, (this.column -3)) != null){
          //cant castle pieces in the way
          return this.position;
        }
        else{
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

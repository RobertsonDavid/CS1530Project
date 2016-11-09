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

  public boolean checkMove(ChessBoard board, int x, int y) {
    //check if move is on board
    if (x<8 && x>-1 && y>-1 && y< 8){
      return true;
    }
    return false;
  }

  //checks to see if the same to move is the same 
  public boolean checkSameTeam(ChessBoard board, int newRow, int newColumn, int row, int column){
    //the location trying to move to
    ChessPiece spaceTryingToMove = board.getPieceAt(row, column);
    //the location currently at
    ChessPiece movingPiece =  board.getPieceAt(newRow, newColumn);
    //if no one at the spot return 
    if(spaceTryingToMove == null){
      return false;
    }
    //if the same team return true
    if (spaceTryingToMove.getSide()==movingPiece.getSide()){
      return true;
    }
    return false;
  }


  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(ChessBoard board, int newRow, int newCol) {

  	//if not in bounds return old position
  	if(checkMove(board, newRow, newCol)==false){
      	return this.position;
    }
    //if trying to take same team return old position
    if(checkSameTeam(board, newRow, newCol, this.row, this.column)){
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

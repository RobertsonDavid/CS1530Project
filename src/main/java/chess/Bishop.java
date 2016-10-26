package chess;

public class Bishop implements ChessPiece {

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

  public boolean getFirstMove() {
    return this.firstMove;
  }

  public boolean getTopOfBoard() {
    return this.topOfBoard;
  }


  public Bishop(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
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

  public boolean checkMove(int [][] board,int x, int y) {
		//change 0 to null when object entered
		if (board[y][x] == 0){
			return true;
		}
		else
			return false;
	}


  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(int board[][], int newRow, int newCol) {

    if(this.row == newRow || this.column == newCol){
      //Didn't move diagonally
      return this.position;
    }

    if(Math.abs(newRow - this.row) != Math.abs(newCol - this.column)){
      return this.position;
    }

    int rowOffset, colOffset;

    if(this.row < newRow){
      rowOffset = 1;
    }
    else{
      rowOffset = -1;
    }

    if(this.column < newCol){
      colOffset = 1;
    }
    else{
      colOffset = -1;
    }

    int y = this.column + colOffset;
    for(int x = this.row + rowOffset; x != newRow; x += rowOffset){

      if(board[x][y] != 0){
        return this.position;
      }

      y += colOffset;
    }
    updateCoord(newRow, newCol);
    return this.position;
  }

}

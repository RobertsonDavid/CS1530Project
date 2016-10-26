package chess;

public class Rook implements ChessPiece {

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

  public Rook(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
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
    //rook can move up down left right
    if(this.row != newRow && this.column != newCol){
      //Did not move along one rank/file
      return this.position;
    }

    int offset;
    //moving the row
    if(this.row != newRow){
      if(this.row < newRow){
        offset = 1;
      }
      else{
        offset = -1;
      }

      for(int x = this.row + offset; x != newRow; x += offset){
        //Go from currentRow to newRow, and check every space
        //change 0 to null when object entered
        if(board[x][this.column] != 0){
          //blocked by a piece
          return this.position;
        }
      }
      updateCoord(newRow, newCol);
      return this.position;
    }

    //Now do the same for columns
    if(this.column != newCol){

      if(this.column < newCol){
        offset = 1;
      }
      else{
        offset = -1;
      }

      for(int x = this.column + offset; x != newCol; x += offset){
        //Go from currentCol to newCol, and check every space
        //change 0 to null when object entered
        if(board[this.row][x] != 0){
          //blocked by a piece
          return this.position;
        }
      }
      updateCoord(newRow, newCol);
      return this.position;
    }
    return this.position;
  }

}

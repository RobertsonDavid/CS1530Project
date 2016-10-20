package chess;

public class Bishop extends ChessPiece {

  public Bishop(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
    super(type, color, firstMove, topOfBoard, row, column);
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
    super.updateCoord(newRow, newCol);
    //board[piece.getRow()][piece.getColumn()]=piece;
    return this.position;
  }

}

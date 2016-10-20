package chess;

public class Rook extends ChessPiece {

  public Rook(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
    super(type, color, firstMove, topOfBoard, row, column);
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
      super.updateCoord(newRow, newCol);
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
      super.updateCoord(newRow, newCol);
      return this.position;
    }
    return this.position;
  }

}

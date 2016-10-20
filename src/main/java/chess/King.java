package chess;

public class King extends ChessPiece {

  public King(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
    super(type, color, firstMove, topOfBoard, row, column);
  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(int board[][], int newRow, int newCol) {

      //moving one to the left or right
    if(Math.abs(newRow - this.row) > 1 || Math.abs(newCol - this.column) > 1) {
      //Castling logic
      if(newCol - this.column == 2 && this.row == newRow){
        //Castle kingside
        if(board[newRow][this.column + 1] != 0 || board[newRow][this.column + 2] != 0){
          //cant castle pieces in the way
          return this.position;
        }
        else{
          super.updateCoord(newRow, newCol);
          return this.position;
        }
      }
      else if(this.column - newCol == 3 && this.column == newRow){
        //castling queenside
        if(board[newRow][this.column - 1] != 0 || board[newRow][this.column - 2] != 0|| board[newRow][this.column - 3] != 0){
          //cant castle pieces in the way
          return this.position;
        }
        else{
          super.updateCoord(newRow, newCol);
          //board[piece.getRow()][piece.getColumn()]=piece;
          return this.position;
        }
      }
      //normal move
      else{
        super.updateCoord(newRow, newCol);
        //board[piece.getRow()][piece.getColumn()]=piece;
        return this.position;
      }
    }
    return this.position;
  }

}

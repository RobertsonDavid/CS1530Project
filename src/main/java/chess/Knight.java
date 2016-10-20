package chess;

public class Knight extends ChessPiece {

  public Knight(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
    super(type, color, firstMove, topOfBoard, row, column);
  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(int board[][], int newRow, int newCol) {

      if(Math.abs(newRow - this.row) == 2 && Math.abs(newCol - this.column) == 1){
        super.updateCoord(newRow, newCol);
        return this.position;
      }

      if(Math.abs(newRow - this.row) == 1 && Math.abs(newCol - this.column) == 2){
        super.updateCoord(newRow, newCol);
        return this.position;
      }

      return this.position;
    }

}

package chess;

public class Pawn extends ChessPiece {

  public Pawn(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
    super(type, color, firstMove, topOfBoard, row, column);
  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(int board[][], int newRow, int newCol) {
    if (super.checkMove(board, newRow, newCol)){
      //moving Pawn by rows [a][b]... a=row b=column
      // if they want to move 2 spaces and first move and top piece
      if ((newRow+2 == this.row) && this.firstMove && !this.topOfBoard){
        super.updateCoord(newRow, newCol);
        return this.position;
      }
      //if they want to move 2 spaces and first move and bottom piece
      else if ((newRow-2 == this.row)&& this.firstMove && this.topOfBoard)
      {
        super.updateCoord(newRow, newCol);
        return this.position;
      }
      //move 1 and top
      else if ((newRow+1 == this.row) && !this.topOfBoard){
        super.updateCoord(newRow, newCol);
        return this.position;
      }
      else if ((newRow-1 == this.row) && this.topOfBoard){
        super.updateCoord(newRow, newCol);
        return this.position;
      }
    }
    return this.position;
  }

}

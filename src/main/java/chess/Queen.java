package chess;

public class Queen implements ChessPiece {

 protected String type;
 protected boolean side; //true if top, false if bottom
 protected boolean oldSide; //opposite of side. bug fix
 protected boolean firstMove;
 protected boolean topOfBoard;
 protected String pieceValue;
 protected int row;
 protected int column;
  protected boolean enpassant;
 protected int[] position = new int[4]; //this will only store the position of an individual piece in the form [row, column]

  public String getType() {
  return this.type;
 }

 public boolean getSide() {
  return this.side;
 }
 
 public boolean oldGetSide() {
   return this.oldSide;
 }

  public boolean getFirstMove() {
    return this.firstMove;
  }

  public boolean getTopOfBoard() {
    return this.topOfBoard;
  }

  public boolean getEnPassant(){
    return this.enpassant;
  }

 public int getRow() {
    return this.row;
  }

 public int getColumn() {
    return this.column;
  }

  public Queen(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
    this.type = type;
  this.side = side;
  this.firstMove = firstMove;
  this.topOfBoard = topOfBoard;
  this.row = row;
  this.column = column;
  this.position[0] = row;
  this.position[1] = column;
  this.position[2] = -1;
  this.position[3] = -1;
  if(side==true)
    oldSide=false;
  if(side==false)
    oldSide=true;
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
    ChessPiece spaceTryingToMove = board.getPieceAt(newRow, newColumn);
    //the location currently at
    ChessPiece movingPiece =  board.getPieceAt(row, column);
    //if no one at the spot return
    if(spaceTryingToMove == null){
      System.out.println("FALSE");
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

    if(checkMove(board, newRow, newCol)==false){
      return this.position;
    }
    if(checkSameTeam(board, newRow, newCol, this.row, this.column))
    {
      return this.position;
    }
    //run Rook code if board changed =1 then do not run bishop code
    if(this.row != newRow && this.column != newCol){
      //Did not move along one rank/file so run bishop code
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
        if(board.getPieceAt(x, y) != null){
          return this.position;
        }
        y += colOffset;
      }

      updateCoord(newRow, newCol);
      return this.position;
    }

    int offset;
    //moving the row
    if(this.row != newRow){
      if(this.row< newRow){
        offset = 1;
      }
      else{
        offset = -1;
      }

      for(int x = this.row + offset; x != newRow; x += offset){
        //Go from currentRow to newRow, and check every space
        //change 0 to null when object entered
        if(board.getPieceAt(x, this.column) != null){
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
        if(board.getPieceAt(this.column, x) != null){
          //blocked by a piece
          continue;
        }
      }
      updateCoord(newRow, newCol);
      return this.position;
    }
    return this.position;
  }

}

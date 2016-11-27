package chess;

import java.io.Serializable;

public class King implements ChessPiece, Serializable {

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
  private static final long serialVersionUID = 4L;

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

  public King(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
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
      return false;
    }
    //if the same team return true
    if (spaceTryingToMove.getSide()==movingPiece.getSide()){
      return true;
    }
    return false;
  }

  //Use this method if a player is in check
  //It will make the board as if the move was made, so that we can see if
  //the player is still in check afterward.
  //Returns true if the player will STILL be in check.
  public boolean testMove(ChessBoard board, int rowOrigin, int columnOrigin, int rowDest, int columnDest) {
    ChessBoard newBoard = (ChessBoard)board.deepClone();
    newBoard.update(rowOrigin, columnOrigin, rowDest, columnDest);
    if(newBoard.isChecked(side)) {
      return true;
    } else {
      return false;
    }
  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(ChessBoard board, int newRow, int newCol) {

    if(board.isChecked(side)) {
      System.out.println("They were in check....");
      if(testMove(board, row, column, newRow, newCol)) {
        System.out.println("...and they're still in check!");
        return this.position;
      }
    }

    //if not in bounds return old position
    if(checkMove(board, newRow, newCol)==false){
      return this.position;
    }
    //if trying to take same team return old position
    if(checkSameTeam(board, newRow, newCol, this.row, this.column))
    {
      return this.position;
    }
    //moving past one move to the left or right
    if(Math.abs(newRow - this.row) > 1 || Math.abs(newCol - this.column) > 1) {
      //trying to move more than one space (castling) when king has already moved
      if(this.firstMove == false){
        return this.position;
      }

      //Castling Kingside
      if(newCol - this.column == 2 && this.row == newRow){
        if(board.getPieceAt(newRow, (this.column + 1)) != null || board.getPieceAt(newRow, (this.column + 2)) != null){
          //can't castle pieces in the way
          return this.position;
        }
        else{
          //move rook and then move king
          Rook r = new Rook("rook", this.side, true, this.topOfBoard, this.row, this.column+3);
          r.move(board, newRow, newCol-2);

          this.firstMove = false;
          updateCoord(newRow, newCol);
          return this.position;
        }
      }
      //Castling Queenside
      else if(this.column - newCol == 2 && this.row == newRow){
        if(board.getPieceAt(newRow, (this.column - 1)) != null || board.getPieceAt(newRow, (this.column - 2)) != null ||
              board.getPieceAt(newRow, (this.column - 3)) != null){
          //cant castle pieces in the way
          return this.position;
        }
        else{
          //move rook and then move king
          Rook r = new Rook("rook", this.side, true, this.topOfBoard, this.row, this.column-4);
          r.move(board, newRow, newCol-2);

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

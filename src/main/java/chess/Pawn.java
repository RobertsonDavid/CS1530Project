package chess;
import java.lang.*;

public class Pawn implements ChessPiece {

	protected String type;
	protected boolean side; //true if top, false if bottom
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

  public Pawn(String type, boolean side, boolean firstMove, boolean topOfBoard, boolean enpassant, int row, int column) {
    this.type = type;
		this.side = side;
		this.firstMove = firstMove;
		this.topOfBoard = topOfBoard;
		this.row = row;
		this.column = column;
    this.enpassant = enpassant;
		this.position[0] = row;
		this.position[1] = column;
  }

  public void updateCoord(int newrow, int newcolumn) {
		this.row = newrow;
		this.column = newcolumn;
		this.position[0] = newrow;
		this.position[1] = newcolumn;
	}

  public boolean checkifEmpty(ChessBoard board,int x, int y) {
    //change 0 to null when object entered
    if (board.getPieceAt(x, y) == null){
      return true;
    }
    else
      return false;
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

  public boolean checkIfBlocked(ChessBoard board, int newRow, int newCol, int row, int column){
    //side true =top
    //if a piece is below the pawn cant move
    //return true if a piece is blocking infront
    if (this.getSide() == false){
      if(newCol == column && newRow-1 == row && board.getPieceAt(newRow, newCol)!= null){
        return true;
      }
    }
    //side false = bottom
    //if a piece is above the pawn cant move
    //return true if a piece is blocking infront
    else if (this.getSide() == true){
      if(newCol == column && newRow+1 == row && board.getPieceAt(newRow, newCol)!= null){
        return true;
      }
    }
    return false;
  }

  public boolean checkEnPassant(ChessBoard board,int x, int y) {
    //if a pawn is to the east
    if (x>-1 && x<8 && y<8 && y>-1){
      ChessPiece piece = board.getPieceAt(x,y);
      if (piece== null){
        return false;
      }
      if (piece.getType().equals("pawn") && piece.getEnPassant() == true){
        return true;
      }
    }
    return false;

  }

  public boolean isAttacking (int newRow, int newColumn)
  {
    if (Math.abs(this.column - newColumn)==1 && Math.abs(this.row - newRow) ==1){
      return true;
    }
    return false;
  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(ChessBoard board, int newRow, int newCol) {
		this.position[2] = -1;
		this.position[3] = -1;
    //empty space
    if (checkMove(board, newRow, newCol) && isAttacking(newRow,newCol)==false){
			if((checkifEmpty(board, newRow, newCol) == true) && newRow < 8 && newRow > -1 && newCol > -1 && newCol < 8) {

			}
      //if trying to take same team return old position
      if(checkSameTeam(board, newRow, newCol, this.row, this.column)){
        return this.position;
      }
      //if a piece is in front of the pawn
      if(checkIfBlocked(board, newRow, newCol, this.row, this.column)){
        return this.position;
      }
      //moving Pawn by rows [a][b]... a=row b=column
      // if they want to move 2 spaces and first move and top piece
      if ((newRow-2 == this.row) && this.firstMove && this.topOfBoard && newCol ==this.column){
				if(checkIfBlocked(board, newRow - 1, newCol, this.row, this.column)) {
					return this.position;
				}
        updateCoord(newRow, newCol);
        this.firstMove = false;
        this.enpassant = true;
        return this.position;
      }
      //if they want to move 2 spaces and first move and bottom piece
      else if ((newRow+2 == this.row)&& this.firstMove && !this.topOfBoard && newCol ==this.column) {
				if(checkIfBlocked(board, newRow+1, newCol, this.row, this.column)) {
					return this.position;
				}
        this.firstMove = false;
        this.enpassant = true;
        updateCoord(newRow, newCol);
        return this.position;
      }
      //move 1 and top
      else if ((newRow-1 == this.row) && this.topOfBoard && newCol ==this.column) {
        updateCoord(newRow, newCol);
        this.firstMove = false;
        this.enpassant = false;
        return this.position;
      }
      //move 1 and bottom
      else if ((newRow+1 == this.row) && !this.topOfBoard && newCol ==this.column) {
        updateCoord(newRow, newCol);
        this.firstMove = false;
        this.enpassant = false;
        return this.position;
      }
    }
		//en passant attack
		else if((checkifEmpty(board, newRow, newCol) == true) && newRow < 8 && newRow > 1 && newCol > -1 && newCol < 8) {
			//if the postion to the left is not off the board
      if((this.column -1 > -1) && (newCol == this.column - 1)){
        //[enpassant left top]
        //if a piece to the west act like it didnt move two spaces and attack it
        if(checkEnPassant(board, this.row, this.column-1) && this.topOfBoard && (this.row==newRow-1 && this.column == newCol+1)){
					this.position[2] = this.row;
					this.position[3] = this.column - 1;
          updateCoord(newRow, newCol);
          this.enpassant = false;

          return this.position;
        }
        //[enpassant left bottom]
        //if a piece to the west act like it didnt move two spaces and attack it
        else if(checkEnPassant(board, this.row, this.column-1) && this.topOfBoard==false && (this.row==newRow+1 && this.column == newCol+1)){
					this.position[2] = this.row;
					this.position[3] = this.column - 1;
          updateCoord(newRow, newCol);
          this.enpassant = false;

          return this.position;
        }
        return this.position;
      }
      //if the postion to the right is not off the board
      if((this.column +1 < 8) && (newCol == this.column + 1)){
        //[enpassant right top]
        //if a piece to the west act like it didnt move two spaces and attack it
        if(checkEnPassant(board, this.row, this.column+1) && this.topOfBoard && (this.row==newRow-1 && this.column == newCol-1)){
					System.out.println("7");
					this.position[2] = this.row;
					this.position[3] = this.column + 1;
          updateCoord(newRow, newCol);
          this.enpassant = false;

          return this.position;
        }
        //[enpassant right bottom]
        //if a piece to the west act like it didnt move two spaces and attack it
        else if(checkEnPassant(board, this.row, this.column+1) && this.topOfBoard== false && (this.row==newRow+1 && this.column == newCol-1)){
					this.position[2] = this.row;
					this.position[3] = this.column + 1;
          updateCoord(newRow, newCol);
          this.enpassant = false;

          return this.position;
        }
        return this.position;
			}
		}
    //taking a piece
    else if((checkifEmpty(board, newRow, newCol) == false) && newRow < 8 && newRow > -1 && newCol > -1 && newCol < 8)
    {
      //top piece attacking
      if(this.topOfBoard && ((this.row==newRow-1) && (this.column == newCol+1)) || ((this.row ==newRow-1) && (this.column ==newCol-1))){
          updateCoord(newRow, newCol);
          this.enpassant = false;
          return this.position;
      }

      //bottom piece attacking
      else if((this.topOfBoard==false) && ((this.row == newRow+1) && (this.column ==newCol+1)) || ((this.row== newRow+1) && (this.column==newCol-1))){
          updateCoord(newRow, newCol);
          this.enpassant = false;
          return this.position;
      }

    }

    return this.position;
  }
}

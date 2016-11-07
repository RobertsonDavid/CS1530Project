package chess;

public class Pawn implements ChessPiece {

  protected String type;
	protected boolean side; //true if top, false if bottom
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

  public Pawn(String type, boolean side, boolean firstMove, boolean topOfBoard, int row, int column) {
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

  public boolean checkMove(ChessBoard board,int x, int y) {
		//change 0 to null when object entered
		if (board.getPieceAt(x, y) == null){
			return true;
		}
		else
			return false;
	}

  public boolean checkEnPassant(ChessBoard board,int x, int y) {
    //if a pawn is to the east
    if (x>-1 && x<8 && y<8 && y>-1){
      ChessPiece piece = board.getPieceAt(x,y);
      if (piece== null){
        return false;
      }
      if (piece.getType().equals("pawn")){
        return true;
      } 
    }  
    return false;

  }

  //Move methods return the new position of the piece on this board. The update of the board array will be handled by the caller.
  public int[] move(ChessBoard board, int newRow, int newCol) {

    //empty space
    if (checkMove(board, newRow, newCol)){
      //moving Pawn by rows [a][b]... a=row b=column
      // if they want to move 2 spaces and first move and top piece
      if ((newRow+2 == this.row) && this.firstMove && !this.topOfBoard){
        updateCoord(newRow, newCol);
        return this.position;
      }
      //if they want to move 2 spaces and first move and bottom piece
      else if ((newRow-2 == this.row)&& this.firstMove && this.topOfBoard) {
        updateCoord(newRow, newCol);
        return this.position;
      }
      //move 1 and top
      else if ((newRow+1 == this.row) && !this.topOfBoard) {
        updateCoord(newRow, newCol);
        return this.position;
      }
      //move 1 and bottom
      else if ((newRow-1 == this.row) && this.topOfBoard) {
        updateCoord(newRow, newCol);
        return this.position;
      }
      //if the postion to the left is not off the board
      if(this.column -1 > -1){
        //[enpassant left top] 
        //if a piece to the west act like it didnt move two spaces and attack it
        if(checkEnPassant(board, this.row, this.column-1)){
          System.out.println("1");
          if(this.topOfBoard && (this.row==newRow-1 && this.column == newCol+1)){
            updateCoord(newRow, newCol);
            return this.position;
          }
        }
        //[enpassant left bottom]
        //if a piece to the west act like it didnt move two spaces and attack it
        else if(checkEnPassant(board, this.row, this.column-1)){
          System.out.println("3");
          if(this.topOfBoard==false && (this.row==newRow+1 && this.column == newCol+1)){
            updateCoord(newRow, newCol);
            return this.position;
          }
        }
        return this.position;
      }
      //if the postion to the right is not off the board  
      if(this.column +1 < 8)  
        //[enpassant right top]
        //if a piece to the west act like it didnt move two spaces and attack it
        if(checkEnPassant(board, this.row, this.column+1)){
          System.out.println("2");
          if(this.topOfBoard && (this.row==newRow-1 && this.column == newCol-1)){
            updateCoord(newRow, newCol);
            return this.position;
          }  
        }
        //[enpassant right bottom]
        //if a piece to the west act like it didnt move two spaces and attack it
        else if(checkEnPassant(board, this.row, this.column+1)){
          System.out.println("4");
          if(this.topOfBoard== false && (this.row==newRow+1 && this.column == newCol-1)){
            updateCoord(newRow, newCol);
            return this.position;
          }
        }
        return this.position;
    }
    //taking a piece
    else if((checkMove(board, newRow, newCol) == false) && newRow < 8 && newRow > -1 && newCol > -1 && newCol < 8)
    {
      //top piece attacking
      if(this.topOfBoard && ((this.row==newRow-1) && (this.column == newCol+1)) || ((this.row ==newRow-1) && (this.column ==newCol-1))){
          updateCoord(newRow, newCol);
          return this.position;
      }

      //bottom piece attacking
      else if((this.topOfBoard==false) && ((this.row == newRow+1) && (this.column ==newCol+1)) || ((this.row== newRow+1) && (this.column==newCol-1))){
          updateCoord(newRow, newCol);
          return this.position;
      }

    }

    return this.position;
  }
}

package chess;

public class Pawn implements ChessPiece {

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

  public Pawn(String type, boolean color, boolean firstMove, boolean topOfBoard, int row, int column) {
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
      else if ((newRow-1 == this.row) && this.topOfBoard) {
        updateCoord(newRow, newCol);
        return this.position;
      }
    }
    return this.position;
  }

}

package chess;

public interface ChessPiece {



 public String getType();

 public boolean getSide();

 public boolean oldGetSide(); //bug fix. will always be opposite of getSide

 public boolean getTopOfBoard();

 public boolean getFirstMove();

 public boolean getEnPassant();

 public int getRow();

 public int getColumn();

 /*
 * Updates the current coordinates of the ChessPiece.
 */
 public void updateCoord(int newrow, int newcolumn);

 //checks to see if space is open
 public boolean checkMove(ChessBoard board,int x, int y);

 public int[] move(ChessBoard board, int newRow, int newCol);

 public void setFirstMove(boolean fMove);

 public void setType(String type);

}

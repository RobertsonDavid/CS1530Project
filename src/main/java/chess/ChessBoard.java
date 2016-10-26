package chess;

public class ChessBoard {

 private ChessPiece[][] b;

 public ChessBoard() {

  b = new ChessPiece[8][8];

  //white pieces
  b[0][0] = b[7][0] = new Rook("rook", true, true, false, 7, 0); //top rook
  b[1][0] = b[6][0] = new Knight("knight", true, true, false, 6, 0); //top knight
  b[2][0] = b[5][0] = new Bishop("bishop", true, true, false, 5, 0); //top bishop
  b[3][0] = new Queen("queen", true, true, false, 3, 0); //top queen
  b[4][0] = new King("king", true, true, false, 4, 0); //top king
  for(int i = 0; i < 8; i++)
   b[i][1] = new Pawn("pawn", true, true, false, i, 1); //top pawn

  //black pieces
  b[0][7] = b[7][7] = new Rook("rook", false, true, true, 7, 7); //bottom rook
  b[1][7] = b[6][7] = new Knight("knight", false, true, true, 6, 7); //bottom knight
  b[2][7] = b[5][7] = new Bishop("bishop", false, true, true, 5, 7); //bottom bishop
  b[3][7] = new Queen("queen", false, true, true, 3, 7); //bottom queen
  b[4][7] = new King("king", false, true, true, 4, 7); //bottom king
  for(int i = 0; i < 8; i++)
   b[i][6] = new Pawn("pawn", false, true, true, i, 6); //bottom pawn
 }

//This returns the piece at the given coordinates
 public ChessPiece getPieceAt(int r, int c) {
  return b[r][c];
 }

 //return updated board to refresh GUI and test purpose
 public ChessPiece[][] update(int rOrigin, int cOrigin, int rDest, int cDest) {

  if(b[rOrigin][cOrigin]!=null) {
   b[rDest][cDest] = b[rOrigin][cOrigin];
   b[rOrigin][cOrigin] = null;
  }

  return b;
 }

 //clear pieces by resetting the array
 public void clearBoard()
 {
   ChessPiece[][] clearedArray= new ChessPiece[8][8];
   b= clearedArray;
 }

 //add a piece to the chessPiece array
 //needed for loads
 public void addPiece(int x, int y, String type, boolean side, boolean firstMove, boolean topOfBoard)
 {
   if(type.equals("rook")){
     b[x][y]= new Rook(type, side, firstMove, topOfBoard, x, y);
   }
   else if(type.equals("bishop")){
     b[x][y]= new Bishop(type, side, firstMove, topOfBoard, x, y);
   }
   else if(type.equals("knight")){
     b[x][y]= new Knight(type, side, firstMove, topOfBoard, x, y);
   }
   else if(type.equals("king")){
     b[x][y]= new King(type, side, firstMove, topOfBoard, x, y);
   }
   else if(type.equals("queen")){
     b[x][y]= new Queen(type, side, firstMove, topOfBoard, x, y);
   }
   else if(type.equals("pawn")){
     b[x][y]= new Pawn(type, side, firstMove, topOfBoard, x, y);
   }

 }
}

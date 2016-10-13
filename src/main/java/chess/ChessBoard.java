package chess;

public class ChessBoard {

 private ChessPiece[][] b;

 public ChessBoard() {

  b = new ChessPiece[8][8];

  //white pieces
  b[0][0] = b[7][0] = new ChessPiece("rock", true); //white rock
  b[1][0] = b[6][0] = new ChessPiece("knight", true); //white knight
  b[2][0] = b[5][0] = new ChessPiece("bishop", true); //white bishop
  b[3][0] = new ChessPiece("queen", true); //white queen
  b[4][0] = new ChessPiece("king", true); //white king
  for(int i = 0; i < 8; i++)
   b[i][1] = new ChessPiece("pawn", true); //white pawn

  //black pieces
  b[0][7] = b[7][7] = new ChessPiece("rook", false); //black rock
  b[1][7] = b[6][7] = new ChessPiece("knight", false); //black knight
  b[2][7] = b[5][7] = new ChessPiece("bishop", false); //black bishop
  b[3][7] = new ChessPiece("queen", false); //black queen
  b[4][7] = new ChessPiece("king", false); //black king
  for(int i = 0; i < 8; i++)
   b[i][6] = new ChessPiece("pawn", false); //black pawn
 }

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

 //clear pieces
 public void clearBoard()
 {
   ChessPiece[][] clearedArray= new ChessPiece[8][8];
   b= clearedArray;
 }

 //add a piece to the chessPiece array
 //needed for loads
 public void addPiece(int x, int y, String type, boolean color)
 {
   b[x][y]= new ChessPiece(type,color);
 }
}

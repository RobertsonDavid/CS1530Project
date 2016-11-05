package chess;

public class ChessBoard {

 private ChessPiece[][] b;

 public ChessBoard() {

  b = new ChessPiece[8][8];

  //white pieces
  b[0][0] = new Rook("rook", true, true, false, 0, 0); //top left rook
  b[0][7] = new Rook("rook", true, true, false, 0, 7); //top right rook
  b[0][1] = new Knight("knight", true, true, false, 0, 1); //top left knight
  b[0][6] = new Knight("knight", true, true, false, 0, 6); //top right knight
  b[0][2] = new Bishop("bishop", true, true, false, 0, 2); //top left bishop
  b[0][5] = new Bishop("bishop", true, true, false, 0, 5); //top rght bishop
  b[0][3] = new Queen("queen", true, true, false, 0, 3); //top queen
  b[0][4] = new King("king", true, true, false, 0, 4); //top king
  for(int i = 0; i < 8; i++)
   b[1][i] = new Pawn("pawn", true, true, false, 1, i); //top pawn

  //black pieces
  b[7][0] = new Rook("rook", false, true, true, 7, 0); //bottom left rook
  b[7][7] = new Rook("rook", false, true, true, 7, 7); //bottom right rook
  b[7][1] = new Knight("knight", false, true, true, 7, 1); //bottom left knight
  b[7][6] = new Knight("knight", false, true, true, 7, 6); //bottom right knight
  b[7][2] = new Bishop("bishop", false, true, true, 7, 2); //bottom left bishop
  b[7][5] = new Bishop("bishop", false, true, true, 7, 5); //bottom right bishop
  b[7][3] = new Queen("queen", false, true, true, 7, 3); //bottom queen
  b[7][4] = new King("king", false, true, true, 7, 4); //bottom king
  for(int i = 0; i < 8; i++)
   b[6][i] = new Pawn("pawn", false, true, true, 6, i); //bottom pawn
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
 public void clearBoard() {
   ChessPiece[][] clearedArray= new ChessPiece[8][8];
   b= clearedArray;
 }

 //add a piece to the chessPiece array
 //needed for loads
 public void addPiece(int x, int y, String type, boolean side, boolean firstMove, boolean topOfBoard) {
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

//Flips board by simply swapping the piece positions, using modular division to find the appropriate coordinates
 public void flipBoard() {
   ChessPiece[][] temp = new ChessPiece[8][8];
   int newX, newY;

   for(int i = 0; i < 8; i++) {
     if(i == 0) newX = 7;
     else newX = i;
     for(int j = 0; j < 8; j++) {
       if(j == 0) newY = 7;
       else newY = j;
       if(b[i][j] != null) {
         temp[7%newX][7%newY] = b[i][j];
       }
     }
   }
   b = temp;
 }

}

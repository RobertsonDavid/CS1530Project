package chess;

import java.io.*;

public class ChessBoard implements Serializable{
 private static final long serialVersionUID = 1L;
 private ChessPiece[][] b;
 private int fullCount=1;
 private int halfCount=0;
 private String enPassant= "-";

 public ChessBoard() {

  b = new ChessPiece[8][8];

  //opponent pieces
  b[0][0] = new Rook("rook", false, true, true, 0, 0); //top left rook
  b[0][7] = new Rook("rook", false, true, true, 0, 7); //top right rook
  b[0][1] = new Knight("knight", false, true, true, 0, 1); //top left knight
  b[0][6] = new Knight("knight", false, true, true, 0, 6); //top right knight
  b[0][2] = new Bishop("bishop", false, true, true, 0, 2); //top left bishop
  b[0][5] = new Bishop("bishop", false, true, true, 0, 5); //top right bishop
  b[0][3] = new Queen("queen", false, true, true, 0, 3); //top queen
  b[0][4] = new King("king", false, true, true, 0, 4); //top king
  for(int i = 0; i < 8; i++)
   b[1][i] = new Pawn("pawn", false, true, true,false, 1, i); //top pawn

  //player pieces
  b[7][0] = new Rook("rook", true, true, false, 7, 0); //bottom left rook
  b[7][7] = new Rook("rook", true, true, false, 7, 7); //bottom right rook
  b[7][1] = new Knight("knight", true, true, false, 7, 1); //bottom left knight
  b[7][6] = new Knight("knight", true, true, false, 7, 6); //bottom right knight
  b[7][2] = new Bishop("bishop", true, true, false, 7, 2); //bottom left bishop
  b[7][5] = new Bishop("bishop", true, true, false, 7, 5); //bottom right bishop
  b[7][3] = new Queen("queen", true, true, false, 7, 3); //bottom queen
  b[7][4] = new King("king", true, true, false, 7, 4); //bottom king
  for(int i = 0; i < 8; i++)
   b[6][i] = new Pawn("pawn", true, true, false,false, 6, i); //bottom pawn
 }

//This returns the piece at the given coordinates
 public ChessPiece getPieceAt(int r, int c) {
  return b[r][c];
 }

//Used for removing pieces for en passant moves
 public void removePiece(int row, int col) {
   b[row][col] = null;
 }

 //return updated board to refresh GUI and test purpose
 public ChessPiece[][] update(int rOrigin, int cOrigin, int rDest, int cDest) {
   int captureHelp= piecesLeft();

   if(b[rOrigin][cOrigin]!=null) {
     b[rDest][cDest] = b[rOrigin][cOrigin];
     b[rOrigin][cOrigin] = null;

     enPassant="-";
     //check for en passant
     if(b[rDest][cDest].getType().equals("pawn"))
     {
       if( (rDest-rOrigin)==2 || (rOrigin-rDest)==2 )
       {
         if(b[rDest][cDest].getSide()==false)
         {
           if(cDest==0)
             enPassant= "a6";
           if(cDest==1)
             enPassant= "b6";
           if(cDest==2)
             enPassant= "c6";
           if(cDest==3)
             enPassant= "d6";
           if(cDest==4)
             enPassant= "e6";
           if(cDest==5)
             enPassant= "f6";
           if(cDest==6)
             enPassant= "g6";
           if(cDest==7)
             enPassant= "h6";
         }
         else
         {
           if(cDest==0)
             enPassant= "a3";
           if(cDest==1)
             enPassant= "b3";
           if(cDest==2)
             enPassant= "c3";
           if(cDest==3)
             enPassant= "d3";
           if(cDest==4)
             enPassant= "e3";
           if(cDest==5)
             enPassant= "f3";
           if(cDest==6)
             enPassant= "g3";
           if(cDest==7)
             enPassant= "h3";
         }
       }
     }

     //update the halfmove clock
     halfCount++;
     if(piecesLeft() < captureHelp || b[rDest][cDest].getType().equals("pawn"))
     {
       halfCount=0;
     }

     //if the black piece moves, increment the fullCount
     if(b[rDest][cDest].getSide()==false)
     {
       fullCount++;
     }
   }

   return b;
 }

 //used to determine piece captures
 private int piecesLeft()
 {
   int pLeft=0;
   for(int r=0; r<=7; r++)
   {
     for(int c=0; c<=7; c++)
     {
       if(getPieceAt(r,c)!=null)
       {
         pLeft++;
       }
     }
   }
   return pLeft;
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
     b[x][y]= new Pawn(type, side, firstMove, topOfBoard,false, x, y);
   }
 }

//Flips board by simply swapping the piece positions, using subtraction to find the new positions
 public void flipBoard() {
   ChessPiece[][] temp = new ChessPiece[8][8];
   int newX, newY;

   for(int i = 0; i < 8; i++) {
     newX = 7-i;
     for(int j = 0; j < 8; j++) {
       newY = 7-j;
       if(b[i][j] != null) {
         temp[newX][newY] = b[i][j];
       }
     }
   }
   b = temp;
 }

 //generate the Fen String Notation for use with StockFish
 //the representation of White pieces for purposes of notation will always be the bottom side of the board
 //this means that a rook starting on the bottom would be designated 'R', and starting on top would be 'r'
 //currently always assumes that if it is called, it is the turn of the opponent at the top of the board
 public String generateFEN()
 {
   StringBuilder sb = new StringBuilder();
   int sumNumbers = 0;

   for(int r=0; r<=7; r++)
   {
     for(int c=0; c<=7; c++)
     {
       if(getPieceAt(r,c)!=null)
       {
         sumNumbers++;
       }
       else
       {
         if(sumNumbers !=0)
         {
           sb.append(Integer.toString(sumNumbers));
         }
         sumNumbers=0;
         ChessPiece pieceIter= getPieceAt(r,c);
         Boolean side= pieceIter.oldGetSide(); //oldside==true for top, false for bottom
         char type= pieceIter.getType().charAt(0);
         if(pieceIter.getType().equals("knight")) //knights appear as 'n'
         {
           type='n';
         }
         if(side=true)
         {
           Character.toLowerCase(type);
         }
         else
         {
           Character.toUpperCase(type);
         }
         sb.append(type);
       }
     }
     if(sumNumbers!=0)
     {
       sb.append(Integer.toString(sumNumbers));
       sumNumbers=0;
     }
     sb.append("/");
   }
   sb.append(" " + 'b');    //this will need updated based on whose turn it is

   sb.append(" -");  //castling currently set to false
   sb.append(" " + enPassant); //en passant
   sb.append(" " +halfCount); //halfmove Clock
   sb.append(" " +fullCount);

   return sb.toString();
 }

 //see if a king of a certain color is in check
 //pass in the side value. true for player, false for opponent
 public boolean isChecked(boolean side)
 {
   //find king
   int row=-1;
   int column=-1;
   for(int r=0; r<=7; r++)
   {
     for(int c=0; c<=7; c++)
     {
       if(getPieceAt(r,c)!=null && getPieceAt(r,c).getType().equals("king") && getPieceAt(r,c).getSide() == side)
       {
         row=r;
         column=c;
         break;
       }
     }
   }

   //check for pawn attacks from white pieces if black
   boolean oldSide;
   if(side==true)
     oldSide=false;
   else
     oldSide=true;

   if(oldSide==true) //true == black;  starting at [0][0]
   {
     ChessPiece piece;
     if(legalSquare((row+1),(column-1)) && getPieceAt(row+1,column-1)!=null)
     {
       piece = getPieceAt(row+1, column-1);
       if(piece.oldGetSide()==false && piece.getType().equals("pawn")) //if enemy pawn
       {
         return true;
       }
     }

     if(legalSquare((row+1),(column+1)) && getPieceAt(row+1,column+1)!=null)
     {
       piece = getPieceAt(row+1, column+1);
       if(piece.oldGetSide()==false && piece.getType().equals("pawn")) //if enemy pawn
       {
         return true;
       }
     }
   }

   //check for pawn attacks from black pieces if white
   if(oldSide==false) //false == white;  starting at [7][0]
   {
     ChessPiece piece;
     if(legalSquare((row-1),(column-1)) && getPieceAt(row-1,column-1)!=null)
     {
       piece = getPieceAt(row-1, column-1);
       if(piece.oldGetSide()==true && piece.getType().equals("pawn")) //if enemy pawn
       {
         return true;
       }
     }

     if(legalSquare((row-1),(column+1)) && getPieceAt(row-1,column+1)!=null)
     {
       piece = getPieceAt(row-1, column+1);
       if(piece.oldGetSide()==true && piece.getType().equals("pawn")) //if enemy pawn
       {
         return true;
       }
     }
   }

   //check for attacks by rooks as well as straight attacks by queens
   ChessPiece piece;
   int rowIter=row;
   int columnIter=column;
   //check attacks coming from the left side
   columnIter--;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("rook") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("rook") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter--;
   }

   rowIter=row;
   columnIter=column;
   //check attacks coming from the right side
   columnIter++;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("rook") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("rook") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter++;
   }

   rowIter=row;
   columnIter=column;
   //check attacks coming from the black side
   rowIter--;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("rook") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("rook") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     rowIter--;
   }

   rowIter=row;
   columnIter=column;
   //check attacks coming from the white side
   rowIter++;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("rook") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("rook") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     rowIter++;
   }

   //check for diagonal attacks by the queen or rook
   piece=null;
   rowIter=row;
   columnIter=column;

   //check attacks coming from top left
   columnIter--;
   rowIter--;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("bishop") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("bishop") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter--;
     rowIter--;
   }

   piece=null;
   rowIter=row;
   columnIter=column;
   //check attacks coming from top right
   columnIter++;
   rowIter--;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("bishop") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("bishop") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter++;
     rowIter--;
   }

   piece=null;
   rowIter=row;
   columnIter=column;
   //check attacks coming from bottom left
   columnIter--;
   rowIter++;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("bishop") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("bishop") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter--;
     rowIter++;
   }

   piece=null;
   rowIter=row;
   columnIter=column;
   //check attacks coming from bottom right
   columnIter++;
   rowIter++;
   while(legalSquare(rowIter, columnIter))
   {
     piece =getPieceAt(rowIter,columnIter);
     //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
     if(piece!=null)
     {
       //break if there is either a piece found that is of the same side, or if the piece found is not a queen or rook
       if(piece.getSide()==side)
       {
         break;
       }
       if(!piece.getType().equals("bishop") && !piece.getType().equals("queen"))
       {
         break;
       }
       //return true if an unobstructed (done implicitly), rook or queen of the opposite side can reach the king's square
       if(piece.getType().equals("bishop") || piece.getType().equals("queen"))
       {
         return true;
       }
     }
     columnIter++;
     rowIter++;
   }

   //check for attacks coming from knights (8 logically possible attack spaces)
   piece=null;
   rowIter=row;
   columnIter=column;

   rowIter=row+2;
   columnIter= column-1;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!=null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row+1;
   columnIter= column-2;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!=null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row-2;
   columnIter= column-1;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!=null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row-1;
   columnIter= column-2;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!=null &&piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row+2;
   columnIter= column+1;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!= null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row+1;
   columnIter= column+2;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!= null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row-2;
   columnIter= column+1;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!= null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   rowIter=row-1;
   columnIter= column+2;
   if(legalSquare(rowIter, columnIter))
   {
     piece=getPieceAt(rowIter, columnIter);
     if(piece!=null && piece.getType().equals("knight") && piece.getSide()!=side)
     {
       return true;
     }
   }

   //no attacks found
   return false;
 }

 //return true if the passed in coordinate is a legal space on the board
 public boolean legalSquare(int r, int c)
 {
   if(r>=0 && r<=7)
   {
     if(c>=0 && c<=7)
     {
       return true;
     }
   }
   return false;
 }

 /**
 * This method makes a deep clone
 * Adapted from: http://alvinalexander.com/java/java-deep-clone-example-source-code
 */
 public Object deepClone() {
   try {
     ByteArrayOutputStream baos = new ByteArrayOutputStream();
     ObjectOutputStream oos = new ObjectOutputStream(baos);
     oos.writeObject(this);
     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
     ObjectInputStream ois = new ObjectInputStream(bais);
     return ois.readObject();
   }
   catch (Exception e) {
     e.printStackTrace();
     return null;
   }
 }

}

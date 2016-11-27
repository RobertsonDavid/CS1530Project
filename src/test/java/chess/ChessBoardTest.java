package chess;

import static org.junit.Assert.*;
import org.mockito.*;
import org.junit.Test;

public class ChessBoardTest {

 //test constructor works
 @Test
 public void testConstructor() {
  assertNotNull(new ChessBoard());
 }

 //test if the array broad is correctly initialized
 //the 0,0 index of board should be a top side rock
 @Test
 public void testGetPieceAt() {
  ChessBoard b = new ChessBoard();
  ChessPiece p = b.getPieceAt(0, 0);
  boolean pass = false;
  
  System.out.println(p.getSide());
  System.out.println(p.getType());
  
  if(p.getSide() == false && p.getType().equals("rook"))
   pass = true;
  
  assertTrue(pass);
 }

 //test if there is no piece on a empty square
 //the getPieceAt() should return null on 4,5 which is an empty square
 @Test
 public void testNoPiece() {
  ChessBoard b = new ChessBoard();
  assertNull(b.getPieceAt(4, 5));
 }

 //test if a piece is moved to destination index
 @Test
 public void testMovePieceToDest() {
  ChessBoard testBoard = new ChessBoard();
  ChessPiece testPawn = testBoard.getPieceAt(0, 1);
  testBoard.update(0, 1, 0, 2);
  assertEquals(testPawn, testBoard.getPieceAt(0, 2));
 }

 //test if after a piece is moved the origin place is empty
 @Test
 public void testOriginAfterMovePiece() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.update(0, 1, 0, 2);
  assertNull(testBoard.getPieceAt(0, 1));
 }
 
 //test flip board actually flip chess pieces in the array
 @Test
 public void testFlipBoard() {
  ChessBoard testBoard = new ChessBoard();
  ChessPiece p = testBoard.getPieceAt(1, 4);
  testBoard.flipBoard();
  assertEquals(p, testBoard.getPieceAt(7-1, 7-4));
 }
 
 //test the FEN for the start of the game
 @Test
 public void testStartFEN() {
   ChessBoard testBoard = new ChessBoard();
   String correctFEN= "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
   String generated= testBoard.generateFEN(true);
   System.out.println("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
   System.out.println(generated);
   assertEquals(generated, correctFEN);
 }
}

package chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class isCheckedTests
{
 //test isChecked for white king not checked
 @Test
 public void testPawnCheck1() {
  ChessBoard testBoard = new ChessBoard();
  assertFalse(testBoard.isChecked(true));
 }
 
 //Test isChecked for black king not checked
 @Test
 public void testPawnCheck2() {
  ChessBoard testBoard = new ChessBoard();
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test isChecked method for black pawn checking white king from the right diagonal
 @Test
 public void testPawnCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", true, false, false);
  testBoard.addPiece(6, 1, "pawn", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test isChecked method for black pawn checking white king from the left diagonal
 @Test
 public void testPawnCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 7, "king", true, false, false);
  testBoard.addPiece(0, 6, "pawn", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test isChecked method for white pawn checking black king from the right diagonal
 @Test
 public void testPawnCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", false, false, false);
  testBoard.addPiece(1, 1, "pawn", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test isChecked method for white pawn checking black king from the left diagonal
 @Test
 public void testPawnCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 7, "king", false, false, false);
  testBoard.addPiece(1, 6, "pawn", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test isChecked method for white pawn behind black king
 @Test
 public void testPawnCheck7() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 1, "king", false, false, false);
  testBoard.addPiece(0, 0, "pawn", true, false, false);
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test isChecked method for black pawn behind white king
 @Test
 public void testPawnCheck8() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", true, false, false);
  testBoard.addPiece(1, 1, "pawn", false, false, false);
  assertFalse(testBoard.isChecked(true));
 }
}
 
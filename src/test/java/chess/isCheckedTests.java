package chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class isCheckedTests
{
 //test isChecked for white king not checked
 @Test
 public void testNotCheckWhite() {
  ChessBoard testBoard = new ChessBoard();
  assertFalse(testBoard.isChecked(true));
 }
 
 //Test isChecked for black king not checked
 @Test
 public void testNotCheckBlack() {
  ChessBoard testBoard = new ChessBoard();
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test isChecked method for black pawn checking white king from the right diagonal
 @Test
 public void testPawnCheck1() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", true, false, false);
  testBoard.addPiece(6, 1, "pawn", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test isChecked method for black pawn checking white king from the left diagonal
 @Test
 public void testPawnCheck2() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 7, "king", true, false, false);
  testBoard.addPiece(0, 6, "pawn", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test isChecked method for white pawn checking black king from the right diagonal
 @Test
 public void testPawnCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", false, false, false);
  testBoard.addPiece(1, 1, "pawn", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test isChecked method for white pawn checking black king from the left diagonal
 @Test
 public void testPawnCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 7, "king", false, false, false);
  testBoard.addPiece(1, 6, "pawn", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test isChecked method for white pawn behind black king
 @Test
 public void testPawnCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 1, "king", false, false, false);
  testBoard.addPiece(0, 0, "pawn", true, false, false);
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test isChecked method for black pawn behind white king
 @Test
 public void testPawnCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", true, false, false);
  testBoard.addPiece(1, 1, "pawn", false, false, false);
  assertFalse(testBoard.isChecked(true));
 }
 
 //Test for black Queen below white king
 @Test
 public void testQueenCheck1() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 3, "king", true, false, false);
  testBoard.addPiece(5, 3, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen left of white king
 @Test
 public void testQueenCheck2() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 7, "king", true, false, false);
  testBoard.addPiece(0, 0, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen above white king
 @Test
 public void testQueenCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 4, "king", true, false, false);
  testBoard.addPiece(1, 4, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen right of white king
 @Test
 public void testQueenCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", true, false, false);
  testBoard.addPiece(7, 6, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen right bottom diagonal of white king
 @Test
 public void testQueenCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 1, "king", true, false, false);
  testBoard.addPiece(7, 7, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen top left diagonal of white king
 @Test
 public void testQueenCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(6, 6, "king", true, false, false);
  testBoard.addPiece(1, 1, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen right top diagonal of white king
 @Test
 public void testQueenCheck7() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 0, "king", true, false, false);
  testBoard.addPiece(1, 1, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen left bottom diagonal of white king
 @Test
 public void testQueenCheck8() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 4, "king", true, false, false);
  testBoard.addPiece(3, 2, "queen", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black Queen blocked by pawn from white king
 @Test
 public void testQueenCheck9() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", true, false, false);
  testBoard.addPiece(7, 7, "queen", false, false, false);
  testBoard.addPiece(2, 2, "pawn", false, false, false);
  assertFalse(testBoard.isChecked(true));
 }
 
 //Test for white Queen below black king
 @Test
 public void testQueenCheck10() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 3, "king", false, false, false);
  testBoard.addPiece(5, 3, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen left of black king
 @Test
 public void testQueenCheck11() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 7, "king", false, false, false);
  testBoard.addPiece(0, 0, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen above black king
 @Test
 public void testQueenCheck12() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 4, "king", false, false, false);
  testBoard.addPiece(1, 4, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen right of black king
 @Test
 public void testQueenCheck13() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", false, false, false);
  testBoard.addPiece(7, 6, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen right bottom diagonal of black king
 @Test
 public void testQueenCheck14() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 1, "king", false, false, false);
  testBoard.addPiece(7, 7, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen top left diagonal of black king
 @Test
 public void testQueenCheck15() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(6, 6, "king", false, false, false);
  testBoard.addPiece(1, 1, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen right top diagonal of black king
 @Test
 public void testQueenCheck16() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 0, "king", false, false, false);
  testBoard.addPiece(1, 1, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen left bottom diagonal of black king
 @Test
 public void testQueenCheck17() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 4, "king", false, false, false);
  testBoard.addPiece(3, 2, "queen", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white Queen blocked by pawn from black king
 @Test
 public void testQueenCheck18() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", false, false, false);
  testBoard.addPiece(7, 7, "queen", true, false, false);
  testBoard.addPiece(2, 2, "pawn", true, false, false);
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test for white knight above and one left of black king
 @Test
 public void testKnightCheck1() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", false, false, false);
  testBoard.addPiece(1, 3, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight above and two left of black king
 @Test
 public void testKnightCheck2() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", false, false, false);
  testBoard.addPiece(2, 2, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight above and one right of black king
 @Test
 public void testKnightCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(4, 3, "king", false, false, false);
  testBoard.addPiece(2, 4, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight above and two right of black king
 @Test
 public void testKnightCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(4, 3, "king", false, false, false);
  testBoard.addPiece(3, 5, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight below and two left of black king
 @Test
 public void testKnightCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 3, "king", false, false, false);
  testBoard.addPiece(3, 1, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight below and one right of black king
 @Test
 public void testKnightCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", false, false, false);
  testBoard.addPiece(5, 5, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight below and one left of black king
 @Test
 public void testKnightCheck7() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 4, "king", false, false, false);
  testBoard.addPiece(4, 3, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white knight below and two right of black king
 @Test
 public void testKnightCheck8() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 3, "king", false, false, false);
  testBoard.addPiece(2, 5, "knight", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
  //Test for black knight above and one left of white king
 @Test
 public void testKnightCheck9() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", true, false, false);
  testBoard.addPiece(1, 3, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight above and two left of white king
 @Test
 public void testKnightCheck10() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", true, false, false);
  testBoard.addPiece(2, 2, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight above and one right of white king
 @Test
 public void testKnightCheck11() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(4, 3, "king", true, false, false);
  testBoard.addPiece(2, 4, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight above and two right of white king
 @Test
 public void testKnightCheck12() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(4, 3, "king", true, false, false);
  testBoard.addPiece(3, 5, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight below and two left of white king
 @Test
 public void testKnightCheck13() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 3, "king", true, false, false);
  testBoard.addPiece(3, 1, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight below and one right of white king
 @Test
 public void testKnightCheck14() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 4, "king", true, false, false);
  testBoard.addPiece(5, 5, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight below and one left of white king
 @Test
 public void testKnightCheck15() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(2, 4, "king", true, false, false);
  testBoard.addPiece(4, 3, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black knight below and two right of white king
 @Test
 public void testKnightCheck16() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(1, 3, "king", true, false, false);
  testBoard.addPiece(2, 5, "knight", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for white bishop bottom right diagonal of black king
 @Test
 public void testBishopCheck1() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", false, false, false);
  testBoard.addPiece(7, 7, "bishop", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white bishop bottom left diagonal of black king
 @Test
 public void testBishopCheck2() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", false, false, false);
  testBoard.addPiece(0, 7, "bishop", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white bishop top right diagonal of black king
 @Test
 public void testBishopCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", false, false, false);
  testBoard.addPiece(0, 7, "bishop", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white bishop top left diagonal of black king
 @Test
 public void testBishopCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 7, "king", false, false, false);
  testBoard.addPiece(0, 0, "bishop", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white bishop blocked by pawn from black king
 @Test
 public void testBishopCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", false, false, false);
  testBoard.addPiece(2, 2, "bishop", true, false, false);
  testBoard.addPiece(1, 1, "pawn", false, false, false);
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test for black bishop bottom right diagonal of white king
 @Test
 public void testBishopCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", true, false, false);
  testBoard.addPiece(7, 7, "bishop", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black bishop bottom left diagonal of white king
 @Test
 public void testBishopCheck7() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", true, false, false);
  testBoard.addPiece(0, 7, "bishop", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black bishop top right diagonal of white king
 @Test
 public void testBishopCheck8() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 0, "king", true, false, false);
  testBoard.addPiece(0, 7, "bishop", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black bishop top left diagonal of white king
 @Test
 public void testBishopCheck9() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 7, "king", true, false, false);
  testBoard.addPiece(0, 0, "bishop", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black bishop blocked by pawn from white king
 @Test
 public void testBishopCheck10() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 0, "king", true, false, false);
  testBoard.addPiece(2, 2, "bishop", false, false, false);
  testBoard.addPiece(1, 1, "pawn", true, false, false);
  assertFalse(testBoard.isChecked(true));
 }
 
 //Test for white rook below black king
 @Test
 public void testRookCheck1() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 3, "king", false, false, false);
  testBoard.addPiece(7, 3, "rook", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white rook above black king
 @Test
 public void testRookCheck2() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 6, "king", false, false, false);
  testBoard.addPiece(0, 6, "rook", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white rook right of black king
 @Test
 public void testRookCheck3() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 0, "king", false, false, false);
  testBoard.addPiece(3, 7, "rook", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white rook left of black king
 @Test
 public void testRookCheck4() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 7, "king", false, false, false);
  testBoard.addPiece(3, 0, "rook", true, false, false);
  assertTrue(testBoard.isChecked(false));
 }
 
 //Test for white rook blocked by pawn from black king
 @Test
 public void testRookCheck5() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 2, "king", false, false, false);
  testBoard.addPiece(3, 1, "pawn", true, false, false);
  testBoard.addPiece(3, 0, "rook", true, false, false);
  assertFalse(testBoard.isChecked(false));
 }
 
 //Test for black rook below white king
 @Test
 public void testRookCheck6() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(0, 3, "king", true, false, false);
  testBoard.addPiece(7, 3, "rook", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black rook above white king
 @Test
 public void testRookCheck7() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(7, 6, "king", true, false, false);
  testBoard.addPiece(0, 6, "rook", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black rook right of white king
 @Test
 public void testRookCheck8() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 0, "king", true, false, false);
  testBoard.addPiece(3, 7, "rook", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black rook left of white king
 @Test
 public void testRookCheck9() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 7, "king", true, false, false);
  testBoard.addPiece(3, 0, "rook", false, false, false);
  assertTrue(testBoard.isChecked(true));
 }
 
 //Test for black rook blocked by pawn from white king
 @Test
 public void testRookCheck10() {
  ChessBoard testBoard = new ChessBoard();
  testBoard.clearBoard();
  testBoard.addPiece(3, 2, "king", true, false, false);
  testBoard.addPiece(3, 1, "pawn", false, false, false);
  testBoard.addPiece(3, 0, "rook", false, false, false);
  assertFalse(testBoard.isChecked(true));
 }
}
 
package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KingTest {


	private King k = new King("king", true, true, false, 0, 4);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	Rook r = Mockito.mock(Rook.class);
	
	//test if normal move for a king is correct, horizontally
	//int array contains 0, 5 is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(0, 5)).thenReturn(null);
		int[] expected = {0, 5};
		assertArrayEquals(expected, k.move(board, 0, 5));	
	}
	
	//test if normal move for a king is correct, vertically
	//int array contains 5, 0 is expected
	@Test
	public void testMoveVertically() {
		Mockito.when(board.getPieceAt(1, 4)).thenReturn(null);
		int[] expected = {1, 4};
		assertArrayEquals(expected, k.move(board, 1, 4));
	}
	
	//test if normal move for a king is correct, diagonally
	//int array contains 1, 5 is expected
	@Test
	public void testMoveDiagonally() {
		Mockito.when(board.getPieceAt(1, 5)).thenReturn(null);
		int[] expected = {1, 5};
		assertArrayEquals(expected, k.move(board, 1, 5));	
	}
	
	//test if illegal move is not taken, the destination is 2 squares away horizontally
	//int array contains 4, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(4, 2)).thenReturn(null);
		int[] expected = {4, 0};
		assertArrayEquals(expected, k.move(board, 4, 3));	
	}

	//testing Queen side castling logic. if the queen bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testQueenSideCastlingTop(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 1)).thenReturn(null);
		int[] expected = {0, 2};
		assertArrayEquals(expected, king.move(board,0,2));
	}

	//testing Queen side castling logic. if the queen bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testQueenSideCastlingBottom(){
		King king = new King("king", true, true, false, 7, 4);
		Mockito.when(board.getPieceAt(7, 3)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 2)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 1)).thenReturn(null);
		int[] expected = {7, 2};
		assertArrayEquals(expected, king.move(board,7,2));
	}

	//testing King side castling logic. if the bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testKingSideCastlingTop(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 5)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 6)).thenReturn(null);
		int[] expected = {0, 6};
		assertArrayEquals(expected, king.move(board,0,6));
	}

	//testing King side castling logic. if the bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testKingSideCastlingBottom(){
		King king = new King("king", true, true, false, 7, 4);
		Mockito.when(board.getPieceAt(7, 5)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 6)).thenReturn(null);
		int[] expected = {7, 6};
		assertArrayEquals(expected, king.move(board,7,6));
	}

	//testing Queen side castling logic. if a piece in the way stay where you are
	@Test
	public void testQueenSideCastlingPieceInWay(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(r);
		int[] expected = {0, 4};
		assertArrayEquals(expected, king.move(board,0,2));
	}

	//testing King side castling logic. if a piece in the way stay where you are
	@Test
	public void testKingSideCastlingPieceInWay(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 5)).thenReturn(r);
		int[] expected = {0, 4};
		assertArrayEquals(expected, king.move(board,0,6));
	}
	
	//test if king can move out of bounds when the move is legal (one square vertically)
	//int array contains 4, 0 (origin) is expected
	@Test
	public void testMoveOutOfBounds() {
		Mockito.when(board.getPieceAt(-1, 4)).thenReturn(null);
		int[] expected = {0, 4};
		assertArrayEquals(expected, k.move(board, -1, 4));
	}
	
	//test if move() blocks move of the king when destination is occupied by a friendly pawn
	//getSide() of pawn returns true (the same as king created above), and destination is legal
	//int array contains 0, 4 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(p.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(1, 4)).thenReturn(p);
		int[] expected = {0, 4};
		assertArrayEquals(expected, k.move(board, 1, 4));
	}
		
	//test if move() can move the king when destination is occupied by an enemy pawn
	//getSide() of pawn returns false (the opposite as king created above), and destination is legal
	//int array contains 1, 4 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(p.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(1, 4)).thenReturn(p);
		int[] expected = {1, 4};
		assertArrayEquals(expected, k.move(board, 1, 4));
	}
	
	//add future tests for castling
}

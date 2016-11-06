package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KingTest {

	private King k = new King("king", true, true, false, 0, 4);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	
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
		Mockito.when(board.getPieceAt(2, 4)).thenReturn(null);
		int[] expected = {2, 4};
		assertArrayEquals(expected, k.move(board, 2, 4));	
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

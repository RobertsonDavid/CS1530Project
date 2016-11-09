package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class QueenTest {

	//create queen at position 0, 3
	private Queen q = new Queen("queen", true, true, false, 0, 3);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	
	//test if normal move is correct, test horizontal move
	//int array contains 0, 6 is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(0, 6)).thenReturn(null);
		int[] expected = {0, 6};
		assertArrayEquals(expected, q.move(board, 0, 6));
	}
	
	//test if normal move is correct, test vertical move
	//int array 3, 3 is expected
	@Test
	public void testMoveVertically() {
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(null);
		int[] expected = {3, 3};
		assertArrayEquals(expected, q.move(board, 3, 3));
	}
	
	//test if normal move is correct, test diagonal move
	//int array contains 3, 6 is expected
	@Test
	public void testMoveDiagonally() {
		Mockito.when(board.getPieceAt(3, 6)).thenReturn(null);
		int[] expected = {3, 6};
		assertArrayEquals(expected, q.move(board, 3, 6));
	}
	
	//test if illegal move is not taken, the destination 2, 6
	//int array contains 0, 3 (origin) is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(2, 6)).thenReturn(null);
		int[] expected = {0, 3};
		assertArrayEquals(expected, q.move(board, 2, 6));	
	}
	
	//test if move() blocks the move of queen when destination is occupied by a friendly pawn
	//getSide() of pawn returns true (the same as queen created above), and destination is legal
	//int array contains 0, 3 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(p.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(p);
		Mockito.when(board.getPieceAt(0,3)).thenReturn(q);
		int[] expected = {0, 3};
		assertArrayEquals(expected, q.move(board, 3, 3));
	}
		
	//test if move() can move the queen when destination is occupied by an enemy pawn
	//getSide() of pawn returns false (the opposite as queen created above), and destination is legal
	//int array contains 3, 3 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(p.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(p);
		Mockito.when(board.getPieceAt(0,3)).thenReturn(q);
		int[] expected = {3, 3};
		assertArrayEquals(expected, q.move(board, 3, 3));
	}
	
	//test if queen can move out of bounds when the move is legal
	//int array contains 0, 3 (origin) is expected
	@Test
	public void testMoveOutOfBounds() {
		Mockito.when(board.getPieceAt(-1, 3)).thenReturn(null);
		int[] expected = {0, 3};
		assertArrayEquals(expected, q.move(board, -1, 3));
	}
	
	

}

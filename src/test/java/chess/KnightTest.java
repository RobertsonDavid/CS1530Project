package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KnightTest {

	private Knight k = new Knight("knight", true, true, false, 0, 1);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	
	//test if normal move is successful
	//int array contains 2, 2 is expected
	@Test
	public void testMove() {
		Mockito.when(board.getPieceAt(2, 2)).thenReturn(null);
		int[] expected = {2, 2, -1, -1};
		assertArrayEquals(expected, k.move(board, 2, 2));
	}
	
	//test if illegal move is not taken, the destination is 1 squares away horizontally
	//int array contains 0, 1 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(null);
		int[] expected = {0, 1, -1, -1};
		assertArrayEquals(expected, k.move(board, 0, 1));	
	}

	//test if move() blocks the move of knight when destination is occupied by a friendly pawn
	//getSide() of pawn returns true (the same as knight created above), and destination is legal
	//int array contains 0, 1 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(p.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(0, 1)).thenReturn(k);
		Mockito.when(board.getPieceAt(2, 2)).thenReturn(p);
		assertTrue(k.checkSameTeam(board,2,2,0,1));
	}
		
	//test if move() can move the knight when destination is occupied by an enemy pawn
	//getSide() of pawn returns false (the opposite as knight created above), and destination is legal
	//int array contains 2, 2 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(p.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(2, 2)).thenReturn(p);
		Mockito.when(board.getPieceAt(0, 1)).thenReturn(k);
		int[] expected = {2, 2, -1, -1};
		assertArrayEquals(expected, k.move(board, 2, 2));
	}
	
	//test if knight can move out of bounds when the move is legal
	//int array contains 0, 1 (origin) is expected
	@Test
	public void testMoveOutOfBounds() {
		Mockito.when(board.getPieceAt(-2, 2)).thenReturn(null);
		int[] expected = {0, 1, -1, -1};
		assertArrayEquals(expected, k.move(board, -2, 2));
	}
	
}

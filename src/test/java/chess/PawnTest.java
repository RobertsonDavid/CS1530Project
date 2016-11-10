package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class PawnTest {

	Pawn p = new Pawn("pawn", false, true, false, false, 1, 0);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Knight k = Mockito.mock(Knight.class);
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move one square horizontally
	//int array contains 2, 0 is expected
	@Test
	public void testFirstMoveOneSquareVertically() {
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(null);
		int[] expected = {2, 0};
		assertArrayEquals(expected, p.move(board, 2, 0));
	}
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move two square horizontally
	//int array contains 3, 0 is expected
	@Test
	public void testFirstMoveTwoSquaresVertically() {
		Mockito.when(board.getPieceAt(3, 0)).thenReturn(null);
		int[] expected = {3, 0};
		assertArrayEquals(expected, p.move(board, 3, 0));
	}
	
	//test the illegal backwards move is not taken
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMoveBack() {
		Mockito.when(board.getPieceAt(0, 0)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 0, 0));
	}
	
	//test the illegal diagonal move is not taken
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMoveDiagonally() {
		Mockito.when(board.getPieceAt(2, 1)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 2, 1));
	}
	
	//test the illegal horizontal move is not taken
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(1, 1)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 1, 1));
	}
	
	//test if getFirstMove() returns false when the pawn is moved
	@Test
	public void testGetFirstMove() {
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(null);
		p.move(board, 2, 0);
		assertFalse(p.getFirstMove());
	}
	
	//test if move two squares vertically when first move is false is blocked
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMoveTwoSquaresVerticallyNotFirst() {
		Pawn pawn = new Pawn("pawn", true, false, false,false, 1, 0);
		Mockito.when(board.getPieceAt(3, 0)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, pawn.move(board, 3, 0));
	}
	
	//test if move() blocks the move of pawn when destination is occupied by a friendly knight
	//getSide() of knight returns true (the same as pawn created above), and destination is legal
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(k.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(1, 0)).thenReturn(p);
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(k);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 2, 0));
	}
	
	//test if move() blocks move of pawn when destination is occupied by an enemy knightw
	//getSide() of knight returns false (the opposite as pawn created above), and destination is legal
	//int array contains 1, 0 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(k.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(k);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 1, 0));
	}
	
	//test if move() blocks the two-square move of pawn when path is blocked by a friendly knight 
	//getSide() of knight returns true (the same as pawn created above), and destination is legal
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMovePathBlockedByFriendly() {
		Mockito.when(k.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(k);
		Mockito.when(board.getPieceAt(3, 0)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 3, 0));
	}
	
	//test if move() blocks the two-square move of pawn when path is blocked by a friendly knight 
	//getSide() of knight returns true (the same as pawn created above), and destination is legal
	//int array contains 1, 0 (origin) is expected
	@Test
	public void testMovePathBlockedByEnemy() {
		Mockito.when(k.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(k);
		Mockito.when(board.getPieceAt(3, 0)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 3, 0));
	}
	
	//test if move() can move pawn diagonally for one square when capturing an enemy knight
	//getSide() of knight returns true (the same as pawn created above), and destination is legal
	//int array contains 2, 1 (origin) is expected
	@Test
	public void testMoveDiagonallyWhenCapturingEnemy() {
		Pawn pw = new Pawn("pawn", true, true, true,false, 1, 0);
		Mockito.when(k.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(1, 0)).thenReturn(p);
		Mockito.when(board.getPieceAt(2, 1)).thenReturn(k);
		int[] expected = {2, 1};
		assertArrayEquals(expected, pw.move(board, 2, 1));
	}
	
	//test if pawn can move out of bounds when the move is legal
	//int array contains 5 (origin) is expected
	@Test
	public void testMoveOutOfBounds() {
		Pawn pawn = new Pawn("pawn", true, true, false,false, 7, 0);
		Mockito.when(board.getPieceAt(8, 0)).thenReturn(null);
		int[] expected = {7, 0};
		assertArrayEquals(expected, pawn.move(board, 8, 0));
	}

	//test if not first move and attempts to move two
	//old location should be retained
	@Test
	public void testMoveTwoSquaresVerticallyNotFirstMove() {
		Pawn pawn = new Pawn("pawn", true, false, true,false, 1, 3);
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(null);
		int[] expected = {1, 3};
		assertArrayEquals(expected, pawn.move(board, 3, 3));	
	}

	
	//add future test for en passant capture
	//add future test for promotion
}

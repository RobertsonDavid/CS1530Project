package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class RookTest {

	//create queen at position 3, 0
	private Rook r = new Rook("rook", true, true, false, 0, 7);
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	
	//test if normal move is correct, test horizontal move
	//int array contains 0, 4  is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(0, 4)).thenReturn(null);
		int[] expected = {0, 4};
		assertArrayEquals(expected, r.move(board, 0, 4));
	}
	
	//test if normal move is correct, test vertical move
	//int array 5, 7 is expected
	@Test
	public void testMoveVertically() {
		Mockito.when(board.getPieceAt(5, 7)).thenReturn(null);
		int[] expected = {5, 7};
		assertArrayEquals(expected, r.move(board, 5, 7));
	}
	
	//test if illegal move is not taken, test diagonal move which is not allowed for rook
	//int array contains 7, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(1, 6)).thenReturn(null);
		int[] expected = {0, 7};
		assertArrayEquals(expected, r.move(board, 1, 6));	
	}
	

	//test if move() block moves of rook when destination is occupied by a friendly pawn
	//getSide() of pawn returns true (the same as rook created above), and destination is legal
	//int array contains 0, 1 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(p.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(5, 7)).thenReturn(p);
		int[] expected = {0, 7};
		assertArrayEquals(expected, r.move(board, 0, 7));
	}
		
	//test if move() can move the rook when destination is occupied by an enemy pawn
	//getSide() of pawn returns false (the opposite as rook created above), and destination is legal
	//int array contains 5, 7 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(p.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(5, 7)).thenReturn(p);
		int[] expected = {5, 7};
		assertArrayEquals(expected, r.move(board, 5, 7));
	}
	
	//test if knight can move out of bounds when the move is legal
	//int array contains 0, 1 (origin) is expected
	@Test
	public void testMoveOutOfBounds() {
		Mockito.when(board.getPieceAt(0, 8)).thenReturn(null);
		int[] expected = {0, 7};
		assertArrayEquals(expected, r.move(board, 0, 8));
	}

}

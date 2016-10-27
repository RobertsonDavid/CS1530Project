package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KnightTest {

	Knight k = new Knight("knight", true, true, false, 6, 0);
	ChessBoard board = Mockito.mock(ChessBoard.class);
	
	//test if normal move is successful
	//int array contains 5, 2 is expected
	@Test
	public void testMove() {
		Mockito.when(board.getPieceAt(5, 2)).thenReturn(null);
		int[] expected = {5, 2};
		assertArrayEquals(expected, k.move(board, 5, 2));
	}
	
	
	//test if illegal move is not taken, the destination is 1 squares away horizontally
	//int array contains 6, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(6, 1)).thenReturn(null);
		int[] expected = {6, 0};
		assertArrayEquals(expected, k.move(board, 6, 1));	
	}

	//test if the move is not taken when destination is occupied by a pawn
	//int array contains 6, 0 is expected
	@Test
	public void testMoveOccupied() {
		Pawn p = Mockito.mock(Pawn.class);
		Mockito.when(board.getPieceAt(5, 2)).thenReturn(p);
		int[] expected = {6, 0};
		assertArrayEquals(expected, k.move(board, 5, 2));	
	}
}

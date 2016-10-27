package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KingTest {

	King k = new King("king", true, true, false, 4, 0);
	ChessBoard board = Mockito.mock(ChessBoard.class);
	
	//test if normal move for a king is correct, horizontally
	//int array contains 4, 1 is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(5, 0)).thenReturn(null);
		int[] expected = {5, 0};
		assertArrayEquals(expected, k.move(board, 5, 0));	
	}
	
	//test if normal move for a king is correct, vertically
	//int array contains 5, 0 is expected
	@Test
	public void testMoveVertically() {
		Mockito.when(board.getPieceAt(5, 0)).thenReturn(null);
		int[] expected = {4, 1};
		assertArrayEquals(expected, k.move(board, 4, 1));	
	}
	
	//test if normal move for a king is correct, diagonally
	//int array contains 5, 1 is expected
	@Test
	public void testMoveDiagonally() {
		Mockito.when(board.getPieceAt(5, 1)).thenReturn(null);
		int[] expected = {5, 1};
		assertArrayEquals(expected, k.move(board, 5, 1));	
	}
	
	//test if illegal move is not taken, the destination is 2 squares away horizontally
	//int array contains 4, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(4, 2)).thenReturn(null);
		int[] expected = {4, 0};
		assertArrayEquals(expected, k.move(board, 4, 2));	
	}
	
}

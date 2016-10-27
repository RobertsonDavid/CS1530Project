package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class QueenTest {

	//create queen at position 3, 0
	Queen q = new Queen("queen", true, true, false, 3, 0);
	ChessBoard board = Mockito.mock(ChessBoard.class);
	
	//test if normal move is correct, test horizontal move
	//int array contains 3, 3 is expected
	@Test
	public void testMoveHorizontally() {
		Mockito.when(board.getPieceAt(6, 0)).thenReturn(null);
		int[] expected = {6, 0};
		assertArrayEquals(expected, q.move(board, 6, 0));
	}
	
	//test if normal move is correct, test vertical move
	//int array 6, 0 is expected
	@Test
	public void testMoveVertically() {
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(null);
		int[] expected = {3, 3};
		assertArrayEquals(expected, q.move(board, 3, 3));
	}
	
	//test if normal move is correct, test diagonal move
	//int array contains 6, 3
	@Test
	public void testMoveDiagonally() {
		Mockito.when(board.getPieceAt(6, 3)).thenReturn(null);
		int[] expected = {6, 3};
		assertArrayEquals(expected, q.move(board, 6, 3));
	}
	
	//test if illegal move is not taken, the destination 6, 2 while origin is 3, 0
	//int array contains 3, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(6, 2)).thenReturn(null);
		int[] expected = {3, 0};
		assertArrayEquals(expected, q.move(board, 6, 2));	
	}

}

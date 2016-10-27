package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class RookTest {

	//create queen at position 3, 0
		Rook r = new Rook("rook", true, true, false, 7, 0);
		ChessBoard board = Mockito.mock(ChessBoard.class);
		
		//test if normal move is correct, test horizontal move
		//int array contains 4, 0  is expected
		@Test
		public void testMoveHorizontally() {
			Mockito.when(board.getPieceAt(4, 0)).thenReturn(null);
			int[] expected = {4, 0};
			assertArrayEquals(expected, r.move(board, 4, 0));
		}
		
		//test if normal move is correct, test vertical move
		//int array 7, 5 is expected
		@Test
		public void testMoveVertically() {
			Mockito.when(board.getPieceAt(7, 5)).thenReturn(null);
			int[] expected = {7, 5};
			assertArrayEquals(expected, r.move(board, 7, 5));
		}
		
		//test if illegal move is not taken, test diagonal move which is not allowed for rook
		//int array contains 7, 0 is expected
		@Test
		public void testIllegalMove() {
			Mockito.when(board.getPieceAt(6, 1)).thenReturn(null);
			int[] expected = {7, 0};
			assertArrayEquals(expected, r.move(board, 6, 1));	
		}

}

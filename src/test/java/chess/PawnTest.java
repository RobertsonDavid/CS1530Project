package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class PawnTest {

	ChessBoard board = Mockito.mock(ChessBoard.class);
	Pawn p = new Pawn("pawn", true, true, false, 0, 1);
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move one square horizontally
	//int array contains 0, 2 is expected
	@Test
	public void testFirstMoveOneSquareVertically() {
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(null);
		int[] expected = {0, 2};
		assertArrayEquals(expected, p.move(board, 0, 2));
	}
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move two square horizontally
	//int array 0, 3
	@Test
	public void testFirstMoveTwoSquaresVertically() {
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(null);
		int[] expected = {0, 3};
		assertArrayEquals(expected, p.move(board, 0, 3));
	}
	
	//test if illegal move is not taken, the destination is 1 square away horizontally
	//int array contains 4, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(1, 1)).thenReturn(null);
		int[] expected = {0, 1};
		assertArrayEquals(expected, p.move(board, 1, 1));	
	}
	
}

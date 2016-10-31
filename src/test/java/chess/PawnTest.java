package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class PawnTest {

	ChessBoard board = Mockito.mock(ChessBoard.class);
	Pawn p = new Pawn("pawn", true, true, true, 1, 0);
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move one square horizontally
	//Top of board
	//int array contains 0, 2 is expected
	@Test
	public void testFirstMoveOneSquareVerticallyTop() {
		Mockito.when(board.getPieceAt(2, 0)).thenReturn(null);
		int[] expected = {2, 0};
		assertArrayEquals(expected, p.move(board, 2, 0));
	}

	//test if normal move for a pawn is correct, the pawn has not been moved and move one square horizontally
	//Bottom of board
	//int array contains 0, 2 is expected
	@Test
	public void testFirstMoveOneSquareVerticallyBottom() {
		Pawn pawn = new Pawn("pawn", true, true, false, 6, 0);
		Mockito.when(board.getPieceAt(5, 0)).thenReturn(null);
		int[] expected = {5, 0};
		assertArrayEquals(expected, pawn.move(board, 5, 0));
	}
	
	//test if normal move for a pawn is correct, the pawn has not been moved and move two square horizontally
	//int array 0, 3
	//Top of board
	@Test
	public void testFirstMoveTwoSquaresVerticallyTop() {
		Mockito.when(board.getPieceAt(3, 0)).thenReturn(null);
		int[] expected = {3, 0};
		assertArrayEquals(expected, p.move(board, 3, 0));
	}

	//test if normal move for a pawn is correct, the pawn has not been moved and move two square horizontally
	//int array 0, 3
	//Bottom of board
	@Test
	public void testFirstMoveTwoSquaresVerticallyBottom() {
		Pawn pawn = new Pawn("pawn", true, true, false, 6, 0);
		Mockito.when(board.getPieceAt(4, 0)).thenReturn(null);
		int[] expected = {4, 0};
		assertArrayEquals(expected, pawn.move(board, 4, 0));
	}
	
	//test if illegal move is not taken, the destination is 1 square away horizontally
	//int array contains 4, 0 is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(1, 1)).thenReturn(null);
		int[] expected = {1, 0};
		assertArrayEquals(expected, p.move(board, 1, 1));	
	}

	//test if not first move and attempts to move two
	//old location should be retained
	@Test
	public void testMoveTwoSquaresVerticallyNotFirstMove() {
		Pawn pawn = new Pawn("pawn", true, false, true, 1, 3);
		Mockito.when(board.getPieceAt(3, 3)).thenReturn(null);
		int[] expected = {1, 3};
		assertArrayEquals(expected, pawn.move(board, 3, 3));	
	}

	
}

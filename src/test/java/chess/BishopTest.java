package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class BishopTest {

	//initialize bishop at position (5,0)
	Bishop b = new Bishop("bishop", true, true, false, 5, 0);
	ChessBoard board = Mockito.mock(ChessBoard.class);
	//test if checkmove() returns true if giving an unoccupied square
	//assume (6, 1) is unoccupied
	@Test
	public void testCheckMove() {
		Mockito.when(board.getPieceAt(6, 1)).thenReturn(null);
		assertTrue(b.checkMove(board, 6, 1));
	}
	
	//test if checkmove() returns true if giving an unoccupied square
	//assume (6, 1) is occupied by a pawn
	@Test
	public void testCheckMoveOccupied() {
		ChessBoard board = Mockito.mock(ChessBoard.class);
		Mockito.when(board.getPieceAt(6, 1)).thenReturn(Mockito.mock(Pawn.class));
		assertFalse(b.checkMove(board, 6, 1));
	}

	//test if move() can move the bishop successfully when it is a legal move (diagonally) 
	//assume position 6,1 is unoccupied and bishop is moving from 5, 0
	//int array contains 6, 1 is expected
	@Test
	public void testMove() {
		Mockito.when(board.getPieceAt(6, 1)).thenReturn(null);
		int[] expected = {6, 1};
		assertArrayEquals(expected, b.move(board, 6, 1));
	}
	
	//test if move() will be unsuccessful when move is illegal (not diagonally)
	//assume position 5,1 is unoccupied and bishop is moving from 5, 0
	//int array contains 5, 0 (original position) is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(5, 1)).thenReturn(null);
		int[] expected = {5, 0};
		assertArrayEquals(expected, b.move(board, 5, 1));
	}

	//test if move() can move the bishop when move is legal (diagonally) but the destination square is out of board
	//bishop is created at position 0, 0
	//int array contains 0, 0 is expected
	@Test
	public void testMoveOutOfBounds() {
		Bishop bishop = new Bishop("bishop", true, true, false, 0, 0);
		Mockito.when(board.getPieceAt(-1, -1)).thenReturn(null);
		int[] expected = {0, 0};
		assertArrayEquals(expected, b.move(board, 0, 0));
	}
}

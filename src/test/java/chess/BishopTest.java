package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class BishopTest {

	//initialize a TOP bishop at position (0, 2)
	private Bishop b = new Bishop("bishop", true, true, false, 0, 2);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	
	//initialize
	//test if checkmove() returns true if giving an unoccupied square
	//assume (1, 3) is unoccupied
	@Test
	public void testCheckMove() {
		Mockito.when(board.getPieceAt(1, 3)).thenReturn(null);
		assertTrue(b.checkMove(board, 1, 3));
	}
	
	//test if checkmove() returns false if giving an unoccupied square
	//assume (1, 3) is occupied by a pawn
	@Test
	public void testCheckMoveOccupied() {
		ChessBoard board = Mockito.mock(ChessBoard.class);
		Mockito.when(board.getPieceAt(1, 3)).thenReturn(p);
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(b);
		Mockito.when(p.getSide()).thenReturn(true);
		assertTrue(b.checkSameTeam(board, 1, 3, 0, 2));
	}

	//test if move() can move the bishop successfully when it is a legal move (diagonally) 
	//assume position 2, 4 is unoccupied and bishop is moving from 5, 0
	//int array contains 2, 4 is expected
	@Test
	public void testMoveDiagnoally() {
		Mockito.when(board.getPieceAt(2, 4)).thenReturn(null);
		int[] expected = {2, 4};
		assertArrayEquals(expected, b.move(board, 2, 4));
	}
	
	//test if move() will be unsuccessful when move is illegal (not diagonally)
	//assume position 0, 3 is unoccupied and bishop is moving from 5, 0
	//int array contains 0, 2 (original position) is expected
	@Test
	public void testIllegalMove() {
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(null);
		int[] expected = {0, 2};
		assertArrayEquals(expected, b.move(board, 0, 3));
	}

	//test if move() can move the bishop when move is legal (diagonally) but the destination square is out of board
	//destination is -1, 1
	//int array contains 0, 2 is expected
	@Test
	public void testMoveOutOfBounds() {
		Mockito.when(board.getPieceAt(-1, 1)).thenReturn(null);
		int[] expected = {0, 2};
		assertArrayEquals(expected, b.move(board, 0, 2));
	}
	
	//test if move() blocks move of the bishop when destination is occupied by a friendly pawn
	//getSide() of pawn returns true (the same as bishop created above), and destination is legal
	//int array contains 0, 2 (origin) is expected
	@Test
	public void testMoveOccupiedByFriendly() {
		Mockito.when(p.getSide()).thenReturn(true);
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(b);
		Mockito.when(board.getPieceAt(2, 4)).thenReturn(p);
		assertTrue(b.checkSameTeam(board,2,4,0,2));
	}
	
	//test if move() can move the bishop when destination is occupied by an enemy pawn
	//getSide() of pawn returns false (the opposite as bishop created above), and destination is legal
	//int array contains 2, 4 is expected
	@Test
	public void testMoveOccupiedByEnemy() {
		Mockito.when(p.getSide()).thenReturn(false);
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(b);
		Mockito.when(board.getPieceAt(2, 4)).thenReturn(p);
		int[] expected = {2, 4};
		assertArrayEquals(expected, b.move(board, 2, 4));
	}
}

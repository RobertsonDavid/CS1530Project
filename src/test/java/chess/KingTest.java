package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KingTest {

	King k = new King("king", true, true, true, 4, 0);
	ChessBoard board = Mockito.mock(ChessBoard.class);
	Rook r = Mockito.mock(Rook.class);
	
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
		assertArrayEquals(expected, k.move(board, 4, 3));	
	}

	//testing Queen side castling logic. if the queen bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testQueenSideCastlingTop(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 2)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 1)).thenReturn(null);
		int[] expected = {0, 2};
		assertArrayEquals(expected, king.move(board,0,2));
	}

	//testing Queen side castling logic. if the queen bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testQueenSideCastlingBottom(){
		King king = new King("king", true, true, false, 7, 4);
		Mockito.when(board.getPieceAt(7, 3)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 2)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 1)).thenReturn(null);
		int[] expected = {7, 2};
		assertArrayEquals(expected, king.move(board,7,2));
	}

	//testing King side castling logic. if the bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testKingSideCastlingTop(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 5)).thenReturn(null);
		Mockito.when(board.getPieceAt(0, 6)).thenReturn(null);
		int[] expected = {0, 6};
		assertArrayEquals(expected, king.move(board,0,6));
	}

	//testing King side castling logic. if the bishop and knight are not in
	//the way of the rook and king will switch places
	@Test
	public void testKingSideCastlingBottom(){
		King king = new King("king", true, true, false, 7, 4);
		Mockito.when(board.getPieceAt(7, 5)).thenReturn(null);
		Mockito.when(board.getPieceAt(7, 6)).thenReturn(null);
		int[] expected = {7, 6};
		assertArrayEquals(expected, king.move(board,7,6));
	}

	//testing Queen side castling logic. if a piece in the way stay where you are
	@Test
	public void testQueenSideCastlingPieceInWay(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 3)).thenReturn(r);
		int[] expected = {0, 4};
		assertArrayEquals(expected, king.move(board,0,2));
	}

	//testing King side castling logic. if a piece in the way stay where you are
	@Test
	public void testKingSideCastlingPieceInWay(){
		King king = new King("king", true, true, true, 0, 4);
		Mockito.when(board.getPieceAt(0, 5)).thenReturn(r);
		int[] expected = {0, 4};
		assertArrayEquals(expected, king.move(board,0,6));
	}
	
}

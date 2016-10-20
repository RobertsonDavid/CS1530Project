package chess;

import static org.junit.Assert.*;
import org.mockito.*;
import org.junit.Test;

public class ChessBoardTest {
/*
	//test constructor works
	@Test
	public void testConstructor() {
		assertNotNull(new ChessBoard());
	}

	//test if the array broad is correctly initialized
	//the 0,0 index of board should be a white rock
	@Test
	public void testGetPieceAt() {
		ChessBoard b = new ChessBoard();
		ChessPiece p = Mockito.mock(ChessPiece.class);
		Mockito.when(p.getType()).thenReturn("rock");
		Mockito.when(p.getSide()).thenReturn(true);
		assertEquals(p.getType(), b.getPieceAt(0, 0).getType());
		assertEquals(p.getSide(), b.getPieceAt(0, 0).getSide());
	}

	//test if there is no piece on a empty square
	//the getPieceAt() should return null on 4,5 which is an empty square
	@Test
	public void testNoPiece() {
		ChessBoard b = new ChessBoard();
		assertNull(b.getPieceAt(4, 5));
	}

	//test if a piece is moved to destination index
	@Test
	public void testMovePieceToDest() {
		ChessBoard testBoard = new ChessBoard();
		ChessPiece testPawn = testBoard.getPieceAt(0, 1);
		testBoard.update(0, 1, 0, 2);
		assertEquals(testPawn, testBoard.getPieceAt(0, 2));
	}

	//test if after a piece is moved the origin place is empty
	@Test
	public void testOriginAfterMovePiece() {
		ChessBoard testBoard = new ChessBoard();
		testBoard.update(0, 1, 0, 2);
		assertNull(testBoard.getPieceAt(0, 1));
	}
	*/
}

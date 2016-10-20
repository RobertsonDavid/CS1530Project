package chess;

import static org.junit.Assert.*;
//import org.mockito.*;
import org.junit.Test;

public class ChessPieceTests {
/*
	//test constructor works
	@Test
	public void testConstructor() {
		assertNotNull(new ChessPiece(false,false,"Pawn", 6,0));
	}

	//test move top pawn
	@Test
	public void testTopPawnMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempPawn = new ChessPieces(false, true,"Pawn",1,0);
		tempPawn.movePiece(tempPawn, board,2,0);
		assertEquals(tempPawn.getRow(), 2);

	}

	//test move bottom pawn
	@Test
	public void testBottomPawnMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempPawn = new ChessPieces(false, false,"Pawn",6,0);
		tempPawn.movePiece(tempPawn, board,5,0);
		assertEquals(tempPawn.getRow(), 5);

	}

	//test moving a pawn two on its first move
	@Test
	public void testFirstMoveDoublePawnMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempPawn = new ChessPieces(true, false,"Pawn",6,0);
		tempPawn.movePiece(tempPawn, board,4,0);
		assertEquals(tempPawn.getRow(), 4);

	}

	//test trying to move a pawn two but not first move
	@Test
	public void testFirstMoveFalseDoublePawnMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempPawn = new ChessPieces(false, false,"Pawn",6,0);
		tempPawn.movePiece(tempPawn, board,4,0);
		assertEquals(tempPawn.getRow(), 6);

	}

	//test a valid Rook move
	@Test
	public void testValidRookMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempRook = new ChessPieces(false, false,"Rook",6,0);
		tempRook.movePiece(tempRook, board,3,0);
		assertEquals(tempRook.getRow(), 3);
		assertEquals(tempRook.getColumn(), 0);

	}

	//test invalid Rook movement
	@Test
	public void testInValidRookMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempRook = new ChessPieces(false, false,"Rook",6,0);
		tempRook.movePiece(tempRook, board,3,4);
		assertEquals(tempRook.getRow(), 6);
		assertEquals(tempRook.getColumn(), 0);

	}

	//Test a valid Bishop Movement
	@Test
	public void testValidBishopMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempBishop = new ChessPieces(false, false,"Bishop",4,4);
		tempBishop.movePiece(tempBishop, board,2,6);
		assertEquals(tempBishop.getRow(), 2);
		assertEquals(tempBishop.getColumn(), 6);

	}

	//Test a Invalid Bishop Movement
	@Test
	public void testInValidBishopMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempBishop = new ChessPieces(false, false,"Bishop",4,4);
		tempBishop.movePiece(tempBishop, board,4,6);
		assertEquals(tempBishop.getRow(), 4);
		assertEquals(tempBishop.getColumn(), 4);

	}

	//Test a valid Knight Movement
	@Test
	public void testValidKnightMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempKnight = new ChessPieces(false, false,"Knight",4,4);
		tempKnight.movePiece(tempKnight, board,2,5);
		assertEquals(tempKnight.getRow(), 2);
		assertEquals(tempKnight.getColumn(), 5);

	}

	//Test a Invalid Knight Movement
	@Test
	public void testInValidKnightMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempKnight = new ChessPieces(false, false,"Knight",4,4);
		tempKnight.movePiece(tempKnight, board,4,7);
		assertEquals(tempKnight.getRow(), 4);
		assertEquals(tempKnight.getColumn(), 4);

	}


	//Test a valid Queen Movement Bishop first
	@Test
	public void testValidQueenMovementOne()
	{
		int [][] board = new int[8][8];
		ChessPieces tempQueen = new ChessPieces(false, false,"Queen",4,4);
		tempQueen.movePiece(tempQueen, board,2,6);
		assertEquals(tempQueen.getRow(), 2);
		assertEquals(tempQueen.getColumn(), 6);

	}

	//Test a valid Queen Movement Rook
	@Test
	public void testValidQueenMovementTwo()
	{
		int [][] board = new int[8][8];
		ChessPieces tempQueen = new ChessPieces(false, false,"Queen",4,4);
		tempQueen.movePiece(tempQueen, board,4,7);
		assertEquals(tempQueen.getRow(), 4);
		assertEquals(tempQueen.getColumn(), 7);

	}

	//Test a valid Queen Movement Rook
	@Test
	public void testInValidQueenMovement()
	{
		int [][] board = new int[8][8];
		ChessPieces tempQueen = new ChessPieces(false, false,"Queen",4,4);
		tempQueen.movePiece(tempQueen, board,2,5);
		assertEquals(tempQueen.getRow(), 4);
		assertEquals(tempQueen.getColumn(), 4);

	}

	//Test a valid King Movement Rook
	// @Test
	// public void testValidKingMovementOne()
	// {
	// 	int [][] board = new int[8][8];
	// 	ChessPiece tempKing = new ChessPiece(false, false,"King",4,4);
	// 	tempKing.movePiece(tempKing, board,3,5);
	// 	assertEquals(tempKing.getRow(), 3);
	// 	assertEquals(tempKing.getColumn(), 5);

	// } */
}

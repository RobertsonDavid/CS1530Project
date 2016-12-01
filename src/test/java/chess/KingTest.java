package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class KingTest {


	private King k = new King("king", true, true, false, 0, 4);
	//mocked objects for test purpose
	private ChessBoard board = Mockito.mock(ChessBoard.class);
	private Pawn p = Mockito.mock(Pawn.class);
	private Rook r = Mockito.mock(Rook.class);



	//add future tests for castling
}

package chess;

public interface ChessPiece {



	public String getType();

	public boolean getSide();

	public boolean getTopOfBoard();

	public boolean getFirstMove();

	/*
	*	Updates the current coordinates of the ChessPiece.
	*/
	public void updateCoord(int newrow, int newcolumn);

	//checks to see if space is open
	public boolean checkMove(int [][] board,int x, int y);

	public int[] move(int board[][], int newRow, int newCol);

}

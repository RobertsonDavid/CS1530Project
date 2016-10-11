package chess;

public class ChessPiece {

	private boolean firstMove;
	private boolean topOfBoard;
	private String pieceValue;
	private int row;
	private int column;

	public ChessPiece(boolean firstMove, boolean topOfBoard, String pieceName, int row, int column){
    	
    	this.pieceValue = pieceName;
    	this.topOfBoard = topOfBoard;
    	this.pieceValue = pieceName;
    	this.row = row;
    	this.column = column;

    }

    public void updateCord(int newrow, int newcolumn){
    	this.row = newrow;
    	this.column = newcolumn;
    }

    //checks to see if space is open
    public boolean checkMove(int [][] board,int x, int y){
    	//change 0 to null when object entered
    	if (board[y][x] == 0){
    		return true;
    	}
    	else
    		return false;
    }
    //will change board with chessboard object 
    public int[][] movePiece(ChessPiece piece, int board[][], int newRow, int newCol){
    	//checking if space is available
    	if (checkMove(board, piece.row, piece.column))
    	{	
	    	//moving Pawn by rows [a][b]... a=row b=column
	    	if(piece.pieceValue == "Pawn"){

	    		// if they want to move 2 spaces and first move and top piece
	    		if ((newCol == piece.row-2) && piece.firstMove && piece.topOfBoard){

	    			piece.updateCord(piece.row-2, piece.column);
	    			//board[piece.row][piece.column] = piece;
	    			return board;
	    		}

	    		//if they want to move 2 spaces and first move and bottom piece
	    		else if ((newCol == piece.row+2)&& piece.firstMove && (piece.topOfBoard == false))
	    		{
	    			piece.updateCord(piece.row+2, piece.column);
	    			//board[piece.row][piece.column] = piece;
	    			return board;
	    		}
	    		//move 1 and top 
	    		else if ((newCol == piece.row--) && piece.topOfBoard){
	    			piece.updateCord(piece.row--, piece.column);
	    			//board[piece.row][piece.column] = piece;
	    			return board;
	    		}
	    		else if ((newCol == piece.row++) && piece.topOfBoard){
	    			piece.updateCord(piece.row++, piece.column);
	    			//board[piece.row][piece.column] = piece;
	    			return board;
	    		}

	    	}

	    	//rook can move up down left right
	    	if(piece.pieceValue == "Rook"){

	    		if(piece.row != newRow && piece.column != newCol){
					//Did not move along one rank/file
					return board;
				}

	    		int offset;
	    		//moving the row
	    		if(piece.row != newRow){
					if(piece.row< newRow){
						offset = 1;
					}
					else{
						offset = -1;
					}
				
					for(int x = piece.row + offset; x != newRow; x += offset){
						//Go from currentRow to newRow, and check every space
						//change 0 to null when object entered
						if(board[x][piece.column] != 0){
							//blocked by a piece 
							return board;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.row][piece.column]=piece;
					return board;
				}	
			
				//Now do the same for columns
				if(piece.column != newCol){

					if(piece.column < newCol){
						offset = 1;
					}
					else{
						offset = -1;
					}
					
					for(int x = piece.column + offset; x != newCol; x += offset){
						//Go from currentCol to newCol, and check every space
						//change 0 to null when object entered
						if(board[piece.row][x] != 0){
							//blocked by a piece 
							return board;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.row][piece.column]=piece;
					return board;
				}
	    	}
		}
		return board;
    }
}

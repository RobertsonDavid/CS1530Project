package chess;

public class ChessPiece {

	private boolean firstMove;
	private boolean topOfBoard;
	private String pieceValue;
	private int row;
	private int column;

	public ChessPiece(boolean firstMove, boolean topOfBoard, String pieceName, int row, int column){
    	
    	this.firstMove = firstMove;
    	this.topOfBoard = topOfBoard;
    	this.pieceValue = pieceName;
    	this.row = row;
    	this.column = column;

    }

    public void updateCord(int newrow, int newcolumn){
    	this.row = newrow;
    	this.column = newcolumn;
    }

    public int getRow()
    {
    	return this.row;
    }

    public int getColumn()
    {
    	return this.column;
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
    	if (checkMove(board, newRow, newCol))
    	{	
	    	//moving Pawn by rows [a][b]... a=row b=column
	    	if(piece.pieceValue == "Pawn"){
	    		// if they want to move 2 spaces and first move and top piece
	    		if ((newRow+2 == piece.getRow()) && piece.firstMove && piece.topOfBoard==false){

	    			piece.updateCord(newRow, newCol);
	    			//board[piece.getRow()][piece.getColumn()] = piece;
	    			return board;
	    		}

	    		//if they want to move 2 spaces and first move and bottom piece
	    		else if ((newRow-2 == piece.getRow())&& piece.firstMove && piece.topOfBoard)
	    		{
	    			piece.updateCord(newRow, newCol);
	    			//board[piece.getRow()][piece.getColumn()] = piece;
	    			return board;
	    		}
	    		//move 1 and top 
	    		else if ((newRow+1 == piece.getRow()) && piece.topOfBoard == false){

	    			piece.updateCord(newRow, newCol);
	    			//board[piece.getRow()][piece.getColumn()] = piece;
	    			return board;
	    		}
	    		else if ((newRow-1 == piece.getRow()) && piece.topOfBoard){

	    			piece.updateCord(newRow, newCol);
	    			//board[piece.getRow()][piece.getColumn()] = piece;
	    			return board;
	    		}

	    	}

	    	//rook can move up down left right
	    	if(piece.pieceValue == "Rook"){

	    		if(piece.getRow() != newRow && piece.getColumn() != newCol){
					//Did not move along one rank/file
					return board;
				}

	    		int offset;
	    		//moving the row
	    		if(piece.getRow() != newRow){
					if(piece.getRow()< newRow){
						offset = 1;
					}
					else{
						offset = -1;
					}
				
					for(int x = piece.getRow() + offset; x != newRow; x += offset){
						//Go from currentRow to newRow, and check every space
						//change 0 to null when object entered
						if(board[x][piece.getColumn()] != 0){
							//blocked by a piece 
							return board;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}	
			
				//Now do the same for columns
				if(piece.getColumn() != newCol){

					if(piece.getColumn() < newCol){
						offset = 1;
					}
					else{
						offset = -1;
					}
					
					for(int x = piece.getColumn() + offset; x != newCol; x += offset){
						//Go from currentCol to newCol, and check every space
						//change 0 to null when object entered
						if(board[piece.getRow()][x] != 0){
							//blocked by a piece 
							return board;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}
	    	}

	    	//move the bishop
	    	if(piece.pieceValue == "Bishop"){

	    		if(piece.getRow() == newRow || piece.getColumn() == newCol){
					//Didn't move diagonally
					return board;
				}
				
				if(Math.abs(newRow - piece.getRow()) != Math.abs(newCol - piece.getColumn())){
					return board;
				}
				
				int rowOffset, colOffset;
				
				if(piece.getRow() < newRow){
					rowOffset = 1;
				}
				else{
					rowOffset = -1;
				}
				
				if(piece.getColumn() < newCol){
					colOffset = 1;
				}
				else{
					colOffset = -1;
				}
				
				int y = piece.getColumn() + colOffset;
				for(int x = piece.getRow() + rowOffset; x != newRow; x += rowOffset){
					
					if(board[x][y] != 0){
						return board;
					}
					
					y += colOffset;
				}
				piece.updateCord(newRow, newCol);
				//board[piece.getRow()][piece.getColumn()]=piece;
				return board;
			}
			
			if(piece.pieceValue == "Knight"){

				if(Math.abs(newRow - piece.getRow()) == 2 && Math.abs(newCol - piece.getColumn()) == 1){
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}
		
				if(Math.abs(newRow - piece.getRow()) == 1 && Math.abs(newCol - piece.getColumn()) == 2){
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}
		
				return board;
			}	

			//Move Queen which has the same moves as a Rook or Bishop
			if(piece.pieceValue == "Queen"){

				//run Rook code if board changed =1 then do not run bishop code
				if(piece.getRow() != newRow && piece.getColumn() != newCol){
					//Did not move along one rank/file so run bishop code
					if(piece.getRow() == newRow || piece.getColumn() == newCol){
						//Didn't move diagonally
						return board;
					}
				
					if(Math.abs(newRow - piece.getRow()) != Math.abs(newCol - piece.getColumn())){
						return board;
					}
				
					int rowOffset, colOffset;
				
					if(piece.getRow() < newRow){
						rowOffset = 1;
					}
					else{
						rowOffset = -1;
					}
				
					if(piece.getColumn() < newCol){
						colOffset = 1;
					}
					else{
						colOffset = -1;
					}
				
					int y = piece.getColumn() + colOffset;
					for(int x = piece.getRow() + rowOffset; x != newRow; x += rowOffset){
					
						if(board[x][y] != 0){
							return board;
						}
					
						y += colOffset;
					}

					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}

	    		int offset;
	    		//moving the row
	    		if(piece.getRow() != newRow){
					if(piece.getRow()< newRow){
						offset = 1;
					}
					else{
						offset = -1;
					}
				
					for(int x = piece.getRow() + offset; x != newRow; x += offset){
						//Go from currentRow to newRow, and check every space
						//change 0 to null when object entered
						if(board[x][piece.getColumn()] != 0){
							//blocked by a piece 
							return board;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}	
			
				//Now do the same for columns
				if(piece.getColumn() != newCol){

					if(piece.getColumn() < newCol){
						offset = 1;
					}
					else{
						offset = -1;
					}
					
					for(int x = piece.getColumn() + offset; x != newCol; x += offset){
						//Go from currentCol to newCol, and check every space
						//change 0 to null when object entered
						if(board[piece.getRow()][x] != 0){
							//blocked by a piece 
							continue;
						}
					}
					piece.updateCord(newRow, newCol);
					//board[piece.getRow()][piece.getColumn()]=piece;
					return board;
				}
			}

			if(piece.pieceValue == "King"){

				//moving one to the left or right
				if(Math.abs(newRow - piece.getRow()) > 1 || Math.abs(newCol - piece.getColumn()) > 1){
			
			
					//Castling logic
					if(newCol - piece.getColumn() == 2 && piece.getRow() == newRow){
						//Castle kingside
						if(board[newRow][piece.getColumn() + 1] != 0 || board[newRow][piece.getColumn() + 2] != 0){
							//cant castle pieces in the way
							return board;
						}
						else{
							piece.updateCord(newRow, newCol);
							//board[piece.getRow()][piece.getColumn()]=piece;
							return board;
						}
					}
					else if(piece.getColumn() - newCol == 3 && piece.getRow() == newRow){
						//castling queenside
						if(board[newRow][piece.getColumn() - 1] != 0 || board[newRow][piece.getColumn() - 2] != 0|| board[newRow][piece.getColumn() - 3] != 0){
							//cant castle pieces in the way
							return board;
						}
						else{
							piece.updateCord(newRow, newCol);
							//board[piece.getRow()][piece.getColumn()]=piece;
							return board;
						}
					
					}
					//normal move
					else{
						piece.updateCord(newRow, newCol);
						//board[piece.getRow()][piece.getColumn()]=piece;
						return board;
					}	
			
				}
				return board;

			}
		}
		return board;
	}	
}

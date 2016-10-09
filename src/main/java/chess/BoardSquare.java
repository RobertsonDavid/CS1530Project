package chess;

import javax.swing.JButton;

public class BoardSquare extends JButton {
	
	private int row;
    private int column;
    private static final long serialVersionUID = 1L;
    
    public BoardSquare(int row, int column)
    {
    	super();
    	this.row = row;
    	this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}

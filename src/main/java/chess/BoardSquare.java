package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BoardSquare extends JPanel {

	private int row;
    private int column;
    private static final long serialVersionUID = 1L;

		/*
		* Constructor for board square - calls super for JPanel constructor
		*/
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

		public void setRow(int newRow) {
			this.row = newRow;
		}

		public void setColumn(int newColumn) {
			this.column = newColumn;
		}

}

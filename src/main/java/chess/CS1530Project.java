package chess;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;

public class CS1530Project {

	private JFrame frame;
	private JPanel gui;
	private JPanel board;
	private JLabel[][] squares;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CS1530Project window = new CS1530Project();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CS1530Project() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color color;
		boolean white = true;
		String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		frame = new JFrame("Laboon Chess");
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gui = new JPanel();
		
		JToolBar toolbar = new JToolBar();
	    toolbar.setFloatable(false);
	    toolbar.add(new JButton("New Game"));	//When they click this, bring up the same window that appears normally (choose color)
	    toolbar.add(new JButton("Save Game"));	//When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
	    toolbar.add(new JButton("Load Game")); 	//When they click this, bring up a window tha lets them choose a new game
	    gui.add(toolbar);
	    
	    board = new JPanel(new GridLayout(9, 9));	//9 rows and 9 columns
	    board.setBorder(new LineBorder(Color.BLACK));	//put a border around the board
	    gui.add(board);
	    
	    squares = new JLabel[8][8];	//array of board squares
	    
	    //This fills the array of squares, alternating between black and white. 
	    for(int i = 0; i < 8; i++){
	    	for(int j = 0; j < 8; j++){
	    		JLabel square = new JLabel();
	    		if(white == true){
	    			color = Color.WHITE;
	    			square.setBackground(color);
	    			square.setName("(" + columnLabels[i] + ", " + j + ")");	//Sets the name of the label to its position on the board, (B, 4) for example (thats exactly how it will be formatted)
	    			white = false;
	    		}
	    		else{
	    			color = Color.BLACK;
	    			square.setBackground(color);
	    			white = true;
	    		}
	    		squares[i][j] = square;
	    	}
	    }
	    
	    board.add(new JLabel(""));	//Empty label at (0,0)
	    
	    //First put down the column labels (A - H)
	    for(int i = 0; i < columnLabels.length; i++){
	    	board.add(new JLabel(columnLabels[i]));
	    }
	    for(int i = 0; i < 8; i++){
	    	for(int j = 0; j < 8; j++){
	    		if(j == 0){
	    			board.add(new JLabel(String.valueOf(i + 1)), SwingConstants.CENTER);	//if in first column, put the corresponding row number
	    		}
	    		else{
	    			board.add(squares[i][j]);
	    		}
	    	}
	    }
	    frame.add(gui);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
	}
}

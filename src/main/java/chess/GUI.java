package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

	private static JFrame window;
	private static JPanel boardPanel;
	private static BoardSquare[][] squares = new BoardSquare[8][8];
	private ChessBoard board; //Initalize this with an array of initial piece positions
	String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};
	//Include a board object here
	
	//Going to want to be able to pass a board object, initialize and reset board!
	public GUI(ChessBoard board) {
		this.board = board;	//This isn't used yet
		window = new JFrame("Laboon Chess");
        window.setSize(800, 700);
        window.setLayout(new BoxLayout(window, BoxLayout.X_AXIS));
        boardPanel = resetBoard(); //Pass it the array of ChessPieces so it can put them on the board
        
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
	    toolbar.add(new JButton("New Game"));	//When they click this, bring up the same window that appears normally (choose color)
	    toolbar.add(new JButton("Save Game"));	//When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
	    toolbar.add(new JButton("Load Game")); 	//When they click this, bring up a window that lets them choose a new game
        
        JPanel gameWindow = resetWindow(boardPanel, toolbar); //Pass it the chessboard panel
        window.setContentPane(gameWindow);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel resetWindow(JPanel boardPanel, JToolBar toolbar){
		JPanel gameWindow = new JPanel();
		//This is where we can initialize things like the side panel where captured pieces will go,
		//the timer, the light, etc. and add them to the game window. 
		gameWindow.add(toolbar);
		gameWindow.add(boardPanel);
		return gameWindow;
	}
	
	//Pass this an array of pieces to reset the board
	private JPanel resetBoard(){
		boardPanel = new JPanel();
		LayoutManager layout = new GridLayout(8, 8);
    	boardPanel.setLayout(layout);
    	boolean white;
    	for(int i = 7; i >= 0; i--)
		{
    		if(i % 2 == 0){
    			white = false;
    		}
    		else{
    			white = true;
    		}
			for(int j = 0; j < 8; j++)
			{
				//Put the piece images as an ImageIcon object on the button (button.setIcon())
				BoardSquare button = new BoardSquare(i, j);
				button.setSize(new Dimension(100, 100));
				button.setLayout(new GridBagLayout());
				if(white == true){
					button.setBackground(Color.WHITE);
					white = false;
				}
				else {
					button.setBackground(Color.BLACK);
					white = true;
				}
				button.setUI(new BasicButtonUI());
				button.addActionListener(this);
				button.setLabel("b");
				squares[i][j] = button;
				boardPanel.add(button);
			}
		}
		return boardPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

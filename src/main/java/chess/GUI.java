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
import javax.swing.ImageIcon;

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

    	ImageIcon WhitePawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_plt60.png");
    	ImageIcon BlackPawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_pdt60.png");
    	ImageIcon WhiteRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rlt60.png");
    	ImageIcon BlackRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rdt60.png");
    	ImageIcon WhiteKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_nlt60.png");
    	ImageIcon BlackKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_ndt60.png");
    	ImageIcon WhiteBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_blt60.png");
    	ImageIcon BlackBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_bdt60.png");
    	ImageIcon WhiteQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qlt60.png");
    	ImageIcon BlackKQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qdt60.png");
    	ImageIcon WhiteKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_klt60.png");
    	ImageIcon BlackKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_kdt60.png");



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
					button.setBackground(Color.GRAY);
					white = true;
				}
				button.setUI(new BasicButtonUI());
				button.addActionListener(this);
				button.setLabel("");

				if (i == 0){
					if (j == 0 || j == 7){
						button.setIcon(BlackRook);
					}
					if(j == 1 || j == 6){
						button.setIcon(BlackKnight);
					}
					if(j == 2 || j == 5){
						button.setIcon(BlackBishop);
					}
					if(j == 3){
						button.setIcon(BlackKQueen);
					}
					if(j == 4){
						button.setIcon(BlackKing);
					}
				}
				//creating black pawns
				else if (i == 1){
					button.setIcon(BlackPawn);
				}
				//creating white pawns
				else if (i == 6){
					button.setIcon(WhitePawn);
				}
				else if(i == 7)
				{
					if (j == 0 || j == 7){
						button.setIcon(WhiteRook);
					}
					if(j == 1 || j == 6){
						button.setIcon(WhiteKnight);
					}
					if(j == 2 || j == 5){
						button.setIcon(WhiteBishop);
					}
					if(j == 3){
						button.setIcon(WhiteQueen);
					}
					if(j == 4){
						button.setIcon(WhiteKing);
					}
				}	

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

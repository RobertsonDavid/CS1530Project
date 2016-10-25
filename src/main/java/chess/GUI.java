package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;


public class GUI extends JFrame implements MouseListener, MouseMotionListener {
 	private static final long serialVersionUID = 1L;
	private JPanel boardPanel;
	private JPanel gameWindow;
	private JLabel piece = null;
	private JLayeredPane layeredPane;

	private BoardSquare[][] squares = new BoardSquare[8][8];
	private ChessBoard board; //Initalize this with an array of initial piece positions
	String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};
	int deltaX, deltaY;
	//Include a board object here

	//Going to want to be able to pass a board object, initialize and reset board!
	public GUI(ChessBoard board) {
		layeredPane = new JLayeredPane();
		this.board = board;
		this.setTitle("Laboon Chess");
		getContentPane().add(layeredPane);
	  layeredPane.addMouseListener(this);
 		layeredPane.addMouseMotionListener(this);
		layeredPane.setPreferredSize(new Dimension(600, 600));
    this.setSize(800, 700);
    boardPanel = resetBoard();

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
	  toolbar.add(new JButton("New Game"));	//When they click this, bring up the same window that appears normally (choose color)
	  toolbar.add(new JButton("Save Game"));	//When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
	  toolbar.add(new JButton("Load Game")); 	//When they click this, bring up a window that lets them choose a new game

    gameWindow = resetWindow(boardPanel, toolbar); //Pass it the chessboard panel
		gameWindow.setPreferredSize(new Dimension(600, 600));
		gameWindow.setBounds(0, 0, 600, 600);
    layeredPane.add(gameWindow, JLayeredPane.DEFAULT_LAYER);
	}

	private JPanel resetWindow(JPanel boardPanel, JToolBar toolbar){
		gameWindow = new JPanel();
		//This is where we can initialize things like the side panel where captured pieces will go,
		//the timer, the light, etc. and add them to the game window.
		gameWindow.add(toolbar);
		gameWindow.add(boardPanel);
		return gameWindow;
	}

	private JPanel resetBoard(){
		boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(8, 8));
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
    ImageIcon BlackQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qdt60.png");
    ImageIcon WhiteKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_klt60.png");
    ImageIcon BlackKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_kdt60.png");

    for(int i = 7; i >= 0; i--) {
    	if(i % 2 == 0) {
    		white = false;
    	}
    	else {
    		white = true;
    	}
			for(int j = 0; j < 8; j++) {
				//Put the piece images as an ImageIcon object on the button (button.setIcon())
				BoardSquare square = new BoardSquare(i, j);
				JLabel pieceImage = null;
				square.setSize(new Dimension(100, 100));
				square.setLayout(new GridBagLayout());
				if(white == true){
					square.setBackground(Color.WHITE);
					white = false;
				}
				else {
					square.setBackground(Color.GRAY);
					white = true;
				}

				if (i == 0){
					if (j == 0 || j == 7){
						pieceImage = new JLabel(BlackRook);
					}
					if(j == 1 || j == 6){
						pieceImage = new JLabel(BlackKnight);
					}
					if(j == 2 || j == 5){
						pieceImage = new JLabel(BlackBishop);
					}
					if(j == 3){
						pieceImage = new JLabel(BlackQueen);
					}
					if(j == 4){
						pieceImage = new JLabel(BlackKing);
					}
					square.add(pieceImage);
				}
				//creating black pawns
				else if (i == 1){
					pieceImage = new JLabel(BlackPawn);
					square.add(pieceImage);
				}
				//creating white pawns
				else if (i == 6){
					pieceImage = new JLabel(WhitePawn);
					square.add(pieceImage);
				}
				else if(i == 7)
				{
					if (j == 0 || j == 7){
						pieceImage = new JLabel(WhiteRook);
					}
					if(j == 1 || j == 6){
						pieceImage = new JLabel(WhiteKnight);
					}
					if(j == 2 || j == 5){
						pieceImage = new JLabel(WhiteBishop);
					}
					if(j == 3){
						pieceImage = new JLabel(WhiteQueen);
					}
					if(j == 4){
						pieceImage = new JLabel(WhiteKing);
					}
					square.add(pieceImage);
				}

				squares[i][j] = square;
				boardPanel.add(square);
			}
		}
		return boardPanel;
	}

	public void mousePressed(MouseEvent e){
		piece = null;
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

		if (spotOnBoard instanceof JPanel) return;

		Point parentLocation = spotOnBoard.getParent().getLocation();
		deltaX = parentLocation.x - e.getX();
		deltaY = parentLocation.y - e.getY();
		piece = (JLabel)spotOnBoard;
		piece.setLocation(e.getX() + deltaX, e.getY() + deltaY);
		piece.setSize(piece.getWidth(), piece.getHeight());
		layeredPane.add(piece, JLayeredPane.DRAG_LAYER);
	}

	public void mouseDragged(MouseEvent e) {
		if (piece == null) return;
		piece.setLocation(e.getX() + deltaX, e.getY() + deltaY);
	}

	public void mouseReleased(MouseEvent e) {
		if(piece == null) return;

		piece.setVisible(false);
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

		if (spotOnBoard instanceof JLabel){
			Container parent = spotOnBoard.getParent();
			parent.remove(0);
			parent.add(piece);
		}
		else {
			Container parent = (Container)spotOnBoard;
			parent.add(piece);
		}

		piece.setVisible(true);
	}

 	public void mouseClicked(MouseEvent e) {

 	}

 	public void mouseMoved(MouseEvent e) {

 	}

 	public void mouseEntered(MouseEvent e){

 	}

 	public void mouseExited(MouseEvent e) {

 	}

}

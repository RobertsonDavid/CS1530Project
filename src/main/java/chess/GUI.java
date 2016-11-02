package chess;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonUI;


public class GUI extends JFrame implements MouseListener, MouseMotionListener {
  private ImageIcon WhitePawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_plt60.png");
  private ImageIcon BlackPawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_pdt60.png");
  private ImageIcon WhiteRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rlt60.png");
  private ImageIcon BlackRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rdt60.png");
  private ImageIcon WhiteKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_nlt60.png");
  private ImageIcon BlackKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_ndt60.png");
  private ImageIcon WhiteBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_blt60.png");
  private ImageIcon BlackBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_bdt60.png");
  private ImageIcon WhiteQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qlt60.png");
  private ImageIcon BlackQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qdt60.png");
  private ImageIcon WhiteKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_klt60.png");
  private ImageIcon BlackKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_kdt60.png");

 	private static final long serialVersionUID = 1L;

  //Panels and Pane for main window
	private JPanel boardPanel;
	private JPanel gameWindow;
	private JLayeredPane layeredPane;
  private String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};

  //Frame for choosing your color
	private static JFrame chooseColor;
  private String colorChoice = "White";

  //Timer
	private static JLabel timerLabel = new JLabel();
	JToolBar timers = new JToolBar();
  private static int seconds = 300;

  //For clicking and dragging pieces
	private JLabel space = null;
  private BoardSquare square = null;
  private BoardSquare square2 = null;
  private Container oldParent;
  int deltaX, deltaY;

  //For taking pieces
  private ChessPiece piece = null;
  private ChessPiece takenPiece = null;
  private int oldRow, oldCol, newRow, newCol;
  private int[] position = null;

  //Board
	private BoardSquare[][] squares = new BoardSquare[8][8]; //Array of BoardSquares which makes up the actual UI board
	private ChessBoard board;  //Backing ChessBoard object

  //Changing colors
  private JFrame changeColor;

  //This method constructs the entire GUI. It will reset the board, and the panel
  //that the board is on.
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

    //Creating a border for the timers
    Border blackline = BorderFactory.createLineBorder(Color.black);
    //Creating a ToolBar that holds both timers
    JToolBar timers = new JToolBar();

    	//creating a timer for the
    Timer timer = new Timer(1000, new ActionListener(){
  	  @Override
     	public void actionPerformed(ActionEvent e) {
      		seconds--;
      		long minute = TimeUnit.SECONDS.toMinutes(seconds)- (TimeUnit.SECONDS.toHours(seconds) * 60);
      	   long second = TimeUnit.SECONDS.toSeconds(seconds)- (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        	timerLabel.setText(minute + ":"+ second);
        	if (seconds == 0) {
        		System.exit(0);
      		}
    	}
	   });
     timer.start();

    timerLabel.setBorder(blackline);
	  timers.add(timerLabel);

    //Create toolbar
  	JToolBar toolbar = new JToolBar();
    JPopupMenu optionsMenu = new JPopupMenu();
    JButton dropDown = new JButton();
    dropDown.setText("Options");

    JMenuItem menuItemChangeColor = new JMenuItem("Change Color");
    optionsMenu.add(menuItemChangeColor);
    JMenuItem menuItemFlipBoard = new JMenuItem("Flip Board");
    optionsMenu.add(menuItemFlipBoard);

  	toolbar.setFloatable(false);
  	JButton newGame = new JButton("New Game");
  	toolbar.add(newGame);					//When they click this, bring up the same window that appears normally (choose color)
  	toolbar.add(new JButton("Save Game"));	//When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
  	toolbar.add(new JButton("Load Game")); 	//When they click this, bring up a window that lets them choose a new game
    toolbar.add(dropDown);  //Options menu for things like changing the color and flipping the board

    //if new Game is selected
  	newGame.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getColor();
  		}
  	});

    //positions the pop up menu for the tool bar item
    dropDown.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        optionsMenu.show(dropDown, dropDown.getBounds().x, dropDown.getBounds().y + dropDown.getBounds().height);
      }
    });

    menuItemChangeColor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        changeColors();
      }
    });

    //action for flip board menu option that allows you to flip board
    menuItemFlipBoard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        flipBoard();
      }
    });

  	gameWindow = resetWindow(boardPanel, toolbar, timers); //Pass it the chessboard panel
  	gameWindow.setPreferredSize(new Dimension(600, 600));
  	gameWindow.setBounds(0, 0, 600, 600);
  	layeredPane.add(gameWindow, JLayeredPane.DEFAULT_LAYER);
  }

  //Resets the panel that the board is on, along with the toolbar and the timer.
  //This will also reset any additional panels that we add.
	private JPanel resetWindow(JPanel boardPanel, JToolBar toolbar, JToolBar timers){
		gameWindow = new JPanel();
		//This is where we can initialize things like the side panel where captured pieces will go,
		//the timer, the light, etc. and add them to the game window.
		gameWindow.setLayout(new BoxLayout(gameWindow, BoxLayout.Y_AXIS));
		gameWindow.add(toolbar);
		gameWindow.add(timers);
		gameWindow.add(boardPanel);
		return gameWindow;
	}

  //Resets the board by resetting the ChessBoard object and the array of BoardSquares.
	private JPanel resetBoard(){
		boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(8, 8));
    boolean white;

	  for(int i = 7; i >= 0; i--) {
	  	if(i % 2 == 0) {
	  		white = false;
	  	}
	  	else {
	  		white = true;
	  	}
      //First, decides what
			for(int j = 0; j < 8; j++) {
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

        if(colorChoice.equals("White")) {

  				if (i == 0){
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
  				//creating black pawns
  				else if (i == 1){
  					pieceImage = new JLabel(WhitePawn);
  					square.add(pieceImage);
  				}
  				//creating white pawns
  				else if (i == 6){
  					pieceImage = new JLabel(BlackPawn);
  					square.add(pieceImage);
  				}
  				else if(i == 7)
  				{
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
        }
        else {
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
  				//creating white pawns
  				else if (i == 1){
  					pieceImage = new JLabel(BlackPawn);
  					square.add(pieceImage);
  				}
  				//creating black pawns
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
        }
			squares[i][j] = square;
			boardPanel.add(square);
			}
		}
    board = new ChessBoard(); //This resets the backing ChessBoard object, which contains the array of ChessPieces
		return boardPanel;
	}

  //Method that gets the color the user chooses
  private void getColor(){

    //Color window
    chooseColor = new JFrame("Choose color");
    chooseColor.setSize(700,300);
    chooseColor.setLayout(new GridLayout(3,1));

    //label that tells the user to choose a color
    JLabel headerLabel = new JLabel("", JLabel.CENTER);
    headerLabel.setText("Please select the color you wish to be");

    //displaying the radio choices black and white
    JRadioButton white = new JRadioButton("White", colorChoice.equals("White"));
    JRadioButton black = new JRadioButton("Black", colorChoice.equals("Black"));

    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    //creating the start game button and the cancel button
    JToolBar confirmation = new JToolBar();
    confirmation.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton startGame = new JButton("Start Game");
    JButton cancel = new JButton("Cancel");
    confirmation.add(startGame);
    confirmation.add(cancel);

    //if white is selected colorChoice is white
    white.addItemListener(new ItemListener(){
      public void itemStateChanged(ItemEvent e){
        colorChoice = "White";
      }
    });

    //if black is selected colorChoice is black
    black.addItemListener(new ItemListener(){
      public void itemStateChanged(ItemEvent e){
        colorChoice = "Black";
      }
    });

    //if start game is chosen remove window
    startGame.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          chooseColor.setVisible(false);
          chooseColor.dispose();
          gameWindow.remove(boardPanel);
          boardPanel = resetBoard();
          gameWindow.add(boardPanel);
          boardPanel.setVisible(true);
        }
    });

    //if cancel is chosen remove window
    cancel.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          chooseColor.setVisible(false);
        }
    });

    //grouping the buttons together
    ButtonGroup group = new ButtonGroup();
    group.add(white);
    group.add(black);

    controlPanel.add(white);
    controlPanel.add(black);

    //adding panels and displaying
    chooseColor.add(headerLabel);
    chooseColor.add(controlPanel);
    chooseColor.add(confirmation);
    chooseColor.setVisible(true);
  }

  //Flips board by simply swapping the square positions, using modular division to find the appropriate coordinates.
  //Calls the flipBoard() method of the ChessBoard object as well to flip the backing array of ChessPieces
  public void flipBoard(){
    BoardSquare[][] temp = new BoardSquare[8][8];

    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++) {
        temp[7%i][7%j] = squares[i][j];
      }
    }
    squares = temp;
    board.flipBoard();
  }

  public void changeColors(){
    changeColor = new JFrame("Change colors");
    changeColor.setSize(700,300);
    changeColor.setLayout(new GridLayout(3,1));

    JPanel changeColorPanel = new JPanel();
    changeColorPanel.setLayout(new FlowLayout());

    JToolBar buttons = new JToolBar();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    buttons.add(confirm);
    buttons.add(cancel);

    JLabel header = new JLabel("", JLabel.CENTER);
    header.setText("Please enter the RGB values (0 to 255) for the colors of both player's pieces.");

    JTextField red = new JTextField();
    JTextField green = new JTextField();
    JTextField blue = new JTextField();

    confirm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
    });


  }

  //Waits for mouse click, and then finds the JPanel representing the square so that we can pick up the piece.
	public void mousePressed(MouseEvent e){
		space = null;
    piece = null;
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

		if (spotOnBoard instanceof JPanel) return;

    oldParent = spotOnBoard.getParent();
    square = (BoardSquare)oldParent;

	  oldRow = square.getRow();
	  oldCol = square.getColumn();

	  piece = board.getPieceAt(oldRow, oldCol);

		Point parentLocation = spotOnBoard.getParent().getLocation();
		deltaX = parentLocation.x - e.getX();
		deltaY = parentLocation.y - e.getY();
		space = (JLabel)spotOnBoard;

		space.setLocation(e.getX() + deltaX, e.getY() + deltaY);
		space.setSize(space.getWidth(), space.getHeight());
		layeredPane.add(space, JLayeredPane.DRAG_LAYER);
	}

  //Waits for the mouse to be dragged, and displays the updated location.
	public void mouseDragged(MouseEvent e) {
		if (space == null) return;
		space.setLocation(e.getX() + deltaX, e.getY() + deltaY);
	}

  /*
  * Waits for the mouse to be released, and then sets the piece down at the current BoardSquare,
  * replacing the piece that was there if the move was legal.
  */
	public void mouseReleased(MouseEvent e) {
		if(space == null) return;

		space.setVisible(false);
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

		if (spotOnBoard instanceof JLabel){
			Container parent = spotOnBoard.getParent();
	    square2 = (BoardSquare)parent;

	    newRow = square2.getRow();
	    newCol = square2.getColumn();

      takenPiece = board.getPieceAt(newRow, newCol);  //piece that is potentially going to be taken

      parent.remove(0);
      parent.add(space);

		}
		else {
			Container parent = (Container)spotOnBoard;
      square2 = (BoardSquare)parent;

      newRow = square2.getRow();
      newCol = square2.getColumn();

      //This section will use a conditional which tests whether a call to move returns
      //a new position or the old position. It will position the piece that the user
      //is moving accordingly.

      parent.add(space);

		}

		space.setVisible(true);
	}

  //Not used but needed interface implementation
 	public void mouseClicked(MouseEvent e) {

 	}
  //Not used but needed interface implementation
 	public void mouseMoved(MouseEvent e) {

 	}
  //Not used but needed interface implementation
 	public void mouseEntered(MouseEvent e){

 	}
  //Not used but needed interface implementation
 	public void mouseExited(MouseEvent e) {

 	}

}

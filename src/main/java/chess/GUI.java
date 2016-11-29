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
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.border.LineBorder;


public class GUI extends JFrame implements MouseListener, MouseMotionListener {
  private ImageIcon whitePawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_plt60.png");
  private ImageIcon blackPawn = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_pdt60.png");
  private ImageIcon whiteRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rlt60.png");
  private ImageIcon blackRook = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_rdt60.png");
  private ImageIcon whiteKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_nlt60.png");
  private ImageIcon blackKnight = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_ndt60.png");
  private ImageIcon whiteBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_blt60.png");
  private ImageIcon blackBishop = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_bdt60.png");
  private ImageIcon whiteQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qlt60.png");
  private ImageIcon blackQueen = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_qdt60.png");
  private ImageIcon whiteKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_klt60.png");
  private ImageIcon blackKing = new ImageIcon("src/main/java/ChessPiecesPictures/Chess_kdt60.png");

  //Array of kibitzes
  private String[] kibitzes = new String[]{"The longest game of chess that is possible is of 5,949 moves.",
    "The longest game lasted for 269 moves and ended in a draw.",
    "The word 鈥渃heckmate鈥� comes from the Arabic word 鈥渟hah mat鈥� which translates to 鈥淭he king is dead鈥� in English.",
    "The new move where the pawn could move two steps instead of one was introduced in Spain in 1280.",
    "A German named Dr. Emanuel Lasker retained the Champion title for the most time: 26 years and 337 days!",
    "The modern chess board as we see it today appeared first in Europe in 1090.",
    "The first mechanical clock to be used instead of sand glass was invented by Thomas Wilson in 1883. The modern push button clock was introduced by Veenhoff in 1900.",
    "The folding Chess board was invented in 1125 by a Chess-playing priest. Since the Church forbids priests to play Chess, he hid his Chess board by making it to look like two books lying together.",
    "Players in their first year are called 鈥淩ookies鈥�. This name came up from the last pieces of chess to move into action called 鈥淩ooks鈥�.",
    "The second book to be ever printed in English language was about Chess.",
    "Alan Turing developed the first computer program for playing chess was developed in 1951. However, no computer was powerful enough to process it, so Turing tested it by doing the calculations himself and playing according to the results, taking several minutes per move.",
    "The first chess game between space and earth was in June, 1970 by the Soyez-9 crew. Though the game ended in draw, it sure did make headlines.",
    "A computer called Deep Thought became the first of its kind to beat an international maestro in November 1988, Long Beach, California.",
    "Are you aware of the fact that the number of possible ways of playing the first four moves for both sides in a game of chess is 318,979,564,000?",
    "Chess is also called the 鈥淕ame of Kings鈥� since for a very long time in the past, it was just played by the Nobel and Kings.",
    "The shortest number of moves to achieve checkmate is just two moves! One sequence is called 鈥淔ool鈥檚 mate runs鈥� Thus, 1. g4 e5 and 2. f4 Qh4 checkmate.",
    "The chess master to have won the World Championship in all three formats (knockout, tournament and match) is Vishwanathan Anand from India."};

 	private static final long serialVersionUID = 1L;

  //Panels and Pane for main window
	private JPanel boardPanel;
	private JPanel gameWindow;
	private JLayeredPane layeredPane;
	private String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};
	private JPanel capArea;
	private JPanel container;
	
	//captured area components
	private JLabel capTitle;
	private JList topCap;
	private JList botCap;
	private JScrollPane topScroller;
	private JScrollPane botScroller;
	private DefaultListModel<ImageIcon> topListModel;
	private DefaultListModel<ImageIcon> botListModel;
	private Map<String, ImageIcon> imageMap;
  
  
  //Frame for choosing your color
	private static JFrame chooseColor;
  private String colorChoice = "White";

  //Timer
	private JLabel timerLabel = new JLabel();
	JToolBar timers;
  private static int seconds = 300;
  Timer timer;
  ActionListener displayTime;

  //For clicking and dragging pieces
	private JLabel space = null;
  private BoardSquare square = null;
  private BoardSquare square2 = null;
  private Container oldParent;
  int deltaX, deltaY;

  //piece the computer will move
  private JLabel compSpace = null;

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
  private Color playerColor;
  private Color computerColor;
  private JPanel newBoardPanel;

  //Label where random kibitzes will be displayed
  private JLabel kibitz;

  //for turn taking
  private boolean bottomTurn = false;

  private Stockfish stockfish;
  boolean first = true;

  //This method constructs the entire GUI. It will reset the board, and the panel
  //that the board is on.
	public GUI(ChessBoard board) {
		layeredPane = new JLayeredPane();
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		this.board = board;
		this.setTitle("Laboon Chess");
		setContentPane(container);
		//getContentPane().add(layeredPane);
		layeredPane.addMouseListener(this);
 		layeredPane.addMouseMotionListener(this);
		layeredPane.setPreferredSize(new Dimension(600, 600));
		container.setPreferredSize(new Dimension(1000, 700));
		//this.setSize(800, 700);
    boardPanel = resetBoard();

    stockfish = new Stockfish();
    if(stockfish.startEngine() == false) {
      System.out.println("Error. Unable to start Stockfish engine.");
    } else {
      System.out.println("Stockfish is running!");
    }

    //Create kibitz label
    kibitz = new JLabel();
    kibitz.setAlignmentX(Component.CENTER_ALIGNMENT);
    kibitz.setMinimumSize(new Dimension(600, 100));
    kibitz.setPreferredSize(new Dimension(600, 100));
    kibitz.setMaximumSize(new Dimension(600, 100));

    //Creating a border for the timers
    Border blackline = BorderFactory.createLineBorder(Color.black);
    //Creating a ToolBar that holds both timers
    timers = new JToolBar();

    displayTime = new ActionListener() {
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
    };

    //creating a timer for the
    timer = new Timer(1000, displayTime);
    timer.start();

    timerLabel.setBorder(blackline);
	  timers.add(timerLabel);

    //Create toolbar
  	JToolBar toolbar = new JToolBar();

    //Popup menu
    JPopupMenu optionsMenu = new JPopupMenu();
    JButton dropDown = new JButton();
    dropDown.setText("Options");

    //menu items for dropdown inside "Options"
    JMenuItem menuItemChangeColor = new JMenuItem("Change Color");
    optionsMenu.add(menuItemChangeColor);
    JMenuItem menuItemFlipBoard = new JMenuItem("Flip Board");
    optionsMenu.add(menuItemFlipBoard);

  	toolbar.setFloatable(false);
  	JButton newGame = new JButton("New Game");
  	toolbar.add(newGame);					//When they click this, bring up the same window that appears normally (choose color)
  	//toolbar.add(new JButton("Save Game"));	//When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
  	//toolbar.add(new JButton("Load Game")); 	//When they click this, bring up a window that lets them choose a new game
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
        optionsMenu.show(dropDown, 0, dropDown.getBounds().y + dropDown.getBounds().height);
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
        gameWindow.remove(boardPanel);
        boardPanel = flipBoard();
        gameWindow.remove(kibitz);
        gameWindow.add(boardPanel);
        gameWindow.add(kibitz);
        gameWindow.revalidate();
        gameWindow.repaint();
        //flipCapArea();
      }
    });

  	gameWindow = resetWindow(boardPanel, toolbar, timers, kibitz); //Pass it the chessboard panel
  	gameWindow.setPreferredSize(new Dimension(600, 600));
  	gameWindow.setBounds(0, 0, 600, 600);
  	layeredPane.add(gameWindow, JLayeredPane.DEFAULT_LAYER);
  	
  	capArea = resetCapArea();
  	capArea.setPreferredSize(new Dimension(200, 500));
  	capArea.setBounds(700, 100, 200, 600);
  	
  	container.add(layeredPane);
  	container.add(capArea);

    //Threaded Kibitzer updates the kibitzer text box every 5-10 seconds with a random kibitz
    new Thread(new Runnable() {
      public void run() {
        while(true) {
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              //Formatted with html so that there are line breaks when it reaches the end of the label
              kibitz.setText("<html><p>" + kibitzes[ThreadLocalRandom.current().nextInt(0, kibitzes.length)] + "</p></html>");
            }
          });

          try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000)); //randomize sleep time between 5 and 10 seconds
          } catch(Exception e) {
            //It's all good, JVM. Relax.
          }
        }
      }
    }).start();

    if(colorChoice.equals("black")) {
      computerMove();
      bottomTurn = true;
    }
  }

  //Resets the panel that the board is on, along with the toolbar and the timer.
  //This will also reset any additional panels that we add.
	private JPanel resetWindow(JPanel boardPanel, JToolBar toolbar, JToolBar timers, JLabel kibitz){
		gameWindow = new JPanel();
		//This is where we can initialize things like the side panel where captured pieces will go,
		//the timer, the light, etc. and add them to the game window.
		gameWindow.setLayout(new BoxLayout(gameWindow, BoxLayout.Y_AXIS));
		gameWindow.add(toolbar);
		gameWindow.add(timers);
		gameWindow.add(boardPanel);
		gameWindow.add(kibitz);
		return gameWindow;
	}
	
	private JPanel resetCapArea(){
		capArea = new JPanel();
		capArea.setLayout(new BoxLayout(capArea, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Captured Pieces");
		title.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		topListModel = new DefaultListModel<ImageIcon>();
		
		topCap = new JList(topListModel);
		topCap.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		topCap.setVisibleRowCount(-1);
		topScroller = new JScrollPane(topCap);
		topScroller.setPreferredSize(new Dimension(200, 50));
		
		botListModel = new DefaultListModel<ImageIcon>();
		
		botCap = new JList(botListModel);
		botCap.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		botCap.setVisibleRowCount(-1);
		botScroller = new JScrollPane(botCap);
		botScroller.setPreferredSize(new Dimension(200, 50)); 
		botScroller.setAlignmentY(Component.BOTTOM_ALIGNMENT); 
		
		capArea.add(title);
		capArea.add(topScroller);
		capArea.add(botScroller);
		
		return capArea;
	}

  //Resets the board by resetting the ChessBoard object and the array of BoardSquares.
	private JPanel resetBoard(){
	boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(8, 8));
    boolean white;

	  for(int i = 0; i < 8; i++) {
	  	if(i % 2 == 0) {
	  		white = false;
	  	}
	  	else {
	  		white = true;
	  	}
      //Create the BoardSquares and initialize the chess piece images in their original spaces
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

        if(colorChoice.equals("Black")) {
          bottomTurn = false;
  				if (i == 0){
  					if (j == 0 || j == 7){
  						pieceImage = new JLabel(whiteRook);
  					}
  					if(j == 1 || j == 6){
  						pieceImage = new JLabel(whiteKnight);
  					}
  					if(j == 2 || j == 5){
  						pieceImage = new JLabel(whiteBishop);
  					}
  					if(j == 3){
  						pieceImage = new JLabel(whiteQueen);
  					}
  					if(j == 4){
  						pieceImage = new JLabel(whiteKing);
  					}
  					square.add(pieceImage);
  				}
  				//creating black pawns
  				else if (i == 1){
  					pieceImage = new JLabel(whitePawn);
  					square.add(pieceImage);
  				}
  				//creating white pawns
  				else if (i == 6){
  					pieceImage = new JLabel(blackPawn);
  					square.add(pieceImage);
  				}
  				else if(i == 7)
  				{
  					if (j == 0 || j == 7){
  						pieceImage = new JLabel(blackRook);
  					}
  					if(j == 1 || j == 6){
  						pieceImage = new JLabel(blackKnight);
  					}
  					if(j == 2 || j == 5){
  						pieceImage = new JLabel(blackBishop);
  					}
  					if(j == 3){
  						pieceImage = new JLabel(blackQueen);
  					}
  					if(j == 4){
  						pieceImage = new JLabel(blackKing);
  					}
  					square.add(pieceImage);
  				}
        }
        else {
          bottomTurn = true;
          if (i == 0){
  					if (j == 0 || j == 7){
  						pieceImage = new JLabel(blackRook);
  					}
  					if(j == 1 || j == 6){
  						pieceImage = new JLabel(blackKnight);
 					  }
  					if(j == 2 || j == 5){
  						pieceImage = new JLabel(blackBishop);
  					}
  					if(j == 3){
  						pieceImage = new JLabel(blackQueen);
  					}
  					if(j == 4){
  						pieceImage = new JLabel(blackKing);
  					}
  					square.add(pieceImage);
  				}
  				//creating white pawns
  				else if (i == 1){
  					pieceImage = new JLabel(blackPawn);
  					square.add(pieceImage);
  				}
  				//creating black pawns
  				else if (i == 6){
  					pieceImage = new JLabel(whitePawn);
  					square.add(pieceImage);
  				}
  				else if(i == 7)
  				{
  					if (j == 0 || j == 7){
  						pieceImage = new JLabel(whiteRook);
  					}
  					if(j == 1 || j == 6){
  						pieceImage = new JLabel(whiteKnight);
  					}
  					if(j == 2 || j == 5){
  						pieceImage = new JLabel(whiteBishop);
  					}
  					if(j == 3){
  						pieceImage = new JLabel(whiteQueen);
  					}
  					if(j == 4){
  						pieceImage = new JLabel(whiteKing);
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
        bottomTurn = false;
      }
    });

    //if black is selected colorChoice is black
    black.addItemListener(new ItemListener(){
      public void itemStateChanged(ItemEvent e){
        colorChoice = "Black";
        bottomTurn = true;
      }
    });

    //if start game is chosen remove window
    startGame.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          chooseColor.setVisible(false);
          chooseColor.dispose();
          gameWindow.remove(boardPanel);
          gameWindow.remove(kibitz);
          boardPanel = resetBoard();
          gameWindow.add(boardPanel);
          gameWindow.add(kibitz);
          boardPanel.setVisible(true);
          capArea = resetCapArea();
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

  //Flips board by simply swapping the square positions, using subtraction to find the appropriate coordinates.
  //Calls the flipBoard() method of the ChessBoard object as well to flip the backing array of ChessPieces
  public JPanel flipBoard(){
    JPanel newBoardPanel = new JPanel();
    newBoardPanel.setLayout(new GridLayout(8, 8));

    BoardSquare[][] temp = new BoardSquare[8][8];
    BoardSquare square;
    int oldX, oldY;

    for(int i = 0; i < 8; i++) {
      oldX = 7-i;
      for(int j = 0; j < 8; j++) {
        oldY = 7-j;
        //get the square object we want to move, update it's x and y coords, and then set it at the new position in temp array
        square = squares[oldX][oldY];
        //square.setRow(newX);
        //square.setColumn(newY);
        temp[i][j] = square;
        newBoardPanel.add(square);
      }
    }
    //set squares to the temp array
    squares = temp;
    //board.flipBoard();
    return newBoardPanel;
  }

  //Allows the user to change the colors of the pieces.
  //They must enter the RGB values for the colors.
  //Easy to test with these values:
  //Blue : 65,105,225
  //Gold : 218,165,32
  public void changeColors(){

    //First, convert all the images to BufferedImages so we can work with their properties
    BufferedImage newWhitePawn = toBufferedImage(whitePawn.getImage());
    BufferedImage newBlackPawn = toBufferedImage(whitePawn.getImage());
    BufferedImage newWhiteRook = toBufferedImage(whiteRook.getImage());
    BufferedImage newBlackRook = toBufferedImage(whiteRook.getImage());
    BufferedImage newWhiteKnight = toBufferedImage(whiteKnight.getImage());
    BufferedImage newBlackKnight = toBufferedImage(whiteKnight.getImage());
    BufferedImage newWhiteBishop = toBufferedImage(whiteBishop.getImage());
    BufferedImage newBlackBishop = toBufferedImage(whiteBishop.getImage());
    BufferedImage newWhiteQueen = toBufferedImage(whiteQueen.getImage());
    BufferedImage newBlackQueen = toBufferedImage(whiteQueen.getImage());
    BufferedImage newWhiteKing = toBufferedImage(whiteKing.getImage());
    BufferedImage newBlackKing = toBufferedImage(whiteKing.getImage());

    //The new panel we'll replace the original with
    newBoardPanel = new JPanel();
    newBoardPanel.setLayout(new GridLayout(8, 8));

    //The frame we'll use for the inputs
    changeColor = new JFrame("Change colors");
    changeColor.setSize(700,300);
    changeColor.setLayout(new GridLayout(4,1));

    //Panel with text fields for the RGB values of white pieces
    JPanel whitePanel = new JPanel();
    whitePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

    JLabel whitePieces = new JLabel("");
    whitePieces.setText("White piece color: ");

    JLabel r = new JLabel("R");
    JLabel g  = new JLabel("G");
    JLabel b = new JLabel("B");
    JTextField redWhite = new JTextField(5);
    JTextField greenWhite = new JTextField(5);
    JTextField blueWhite = new JTextField(5);

    whitePanel.add(whitePieces);
    whitePanel.add(r);
    whitePanel.add(redWhite);
    whitePanel.add(g);
    whitePanel.add(greenWhite);
    whitePanel.add(b);
    whitePanel.add(blueWhite);

    //Panel with text fields for the RGB values of black pieces
    JPanel blackPanel = new JPanel();
    blackPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

    JLabel blackPieces = new JLabel("");
    blackPieces.setText("Black piece color: ");

    JLabel r2 = new JLabel("R");
    JLabel g2  = new JLabel("G");
    JLabel b2 = new JLabel("B");
    JTextField redBlack = new JTextField(5);
    JTextField greenBlack = new JTextField(5);
    JTextField blueBlack = new JTextField(5);

    blackPanel.add(blackPieces);
    blackPanel.add(r2);
    blackPanel.add(redBlack);
    blackPanel.add(g2);
    blackPanel.add(greenBlack);
    blackPanel.add(b2);
    blackPanel.add(blueBlack);

    //toolbar that holds the 2 buttons, confirm and cancel
    JToolBar buttons = new JToolBar();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    buttons.add(confirm);
    buttons.add(cancel);

    JLabel header = new JLabel("", JLabel.CENTER);
    header.setText("Please enter the RGB values (0 to 255) for the colors of both player's pieces.");

    changeColor.add(header);
    changeColor.add(whitePanel);
    changeColor.add(blackPanel);
    changeColor.add(buttons);
    changeColor.setVisible(true);

    confirm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Make sure the user actually fills out the RGB values
        if(redWhite.getText().equals("") || greenWhite.getText().equals("") || blueWhite.getText().equals("") || redBlack.getText().equals("") || greenBlack.getText().equals("") || blueBlack.getText().equals("")) {
          JOptionPane.showMessageDialog(new JFrame(), "You must enter a value in each text box to change the colors.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
          playerColor = new Color(Integer.parseInt(redWhite.getText()), Integer.parseInt(greenWhite.getText()), Integer.parseInt(blueWhite.getText()));
          computerColor = new Color(Integer.parseInt(redBlack.getText()), Integer.parseInt(greenBlack.getText()), Integer.parseInt(blueBlack.getText()));

          BoardSquare square;
          ChessPiece piece;
          JLabel pieceImage = null;

          //If the player is playing as white, we need to change the pieces accordingly. Only uses white piece images so we can preserve the border and accents
          if(colorChoice.equals("Black")) {
            whitePawn = new ImageIcon(changeColor(newWhitePawn, playerColor));
            blackPawn = new ImageIcon(changeColor(newBlackPawn, computerColor));
            whiteRook = new ImageIcon(changeColor(newWhiteRook, playerColor));
            blackRook = new ImageIcon(changeColor(newBlackRook, computerColor));
            whiteKnight = new ImageIcon(changeColor(newWhiteKnight, playerColor));
            blackKnight = new ImageIcon(changeColor(newBlackKnight, computerColor));
            whiteBishop = new ImageIcon(changeColor(newWhiteBishop, playerColor));
            blackBishop = new ImageIcon(changeColor(newBlackBishop, computerColor));
            whiteQueen = new ImageIcon(changeColor(newWhiteQueen, playerColor));
            blackQueen = new ImageIcon(changeColor(newBlackQueen, computerColor));
            whiteKing = new ImageIcon(changeColor(newWhiteKing, playerColor));
            blackKing = new ImageIcon(changeColor(newBlackKing, computerColor));

            //Loop through array of BoardSquares, pull out the ChessPiece object
            //corresponding to that square, and set its new image accordingly
            for(int i = 0; i < 8; i++) {
              for(int j = 0; j < 8; j++) {
                square = squares[i][j];
                piece = board.getPieceAt(i, j);
                square.removeAll(); //remove old image
                if(piece != null) {
                  //opponent piece
                  if(piece.getSide() == true) {
                    if(piece.getType().equals("pawn"))
                      pieceImage = new JLabel(blackPawn);
                    else if(piece.getType().equals("rook"))
                      pieceImage = new JLabel(blackRook);
                    else if(piece.getType().equals("knight"))
                      pieceImage = new JLabel(blackKnight);
                    else if(piece.getType().equals("bishop"))
                      pieceImage = new JLabel(blackBishop);
                    else if(piece.getType().equals("queen"))
                      pieceImage = new JLabel(blackQueen);
                    else if(piece.getType().equals("king"))
                      pieceImage = new JLabel(blackKing);
                  }
                  else {
                    if(piece.getType().equals("pawn"))
                      pieceImage = new JLabel(whitePawn);
                    else if(piece.getType().equals("rook"))
                      pieceImage = new JLabel(whiteRook);
                    else if(piece.getType().equals("knight"))
                      pieceImage = new JLabel(whiteKnight);
                    else if(piece.getType().equals("bishop"))
                      pieceImage = new JLabel(whiteBishop);
                    else if(piece.getType().equals("queen"))
                      pieceImage = new JLabel(whiteQueen);
                    else if(piece.getType().equals("king"))
                      pieceImage = new JLabel(whiteKing);
                  }
                  square.add(pieceImage);
                }
                newBoardPanel.add(square);
              }
            }
          }
          //If the player is playing as black, we need to change the pieces accordingly
          else {
            whitePawn = new ImageIcon(changeColor(newWhitePawn, computerColor));
            blackPawn = new ImageIcon(changeColor(newBlackPawn, playerColor));
            whiteRook = new ImageIcon(changeColor(newWhiteRook, computerColor));
            blackRook = new ImageIcon(changeColor(newBlackRook, playerColor));
            whiteKnight = new ImageIcon(changeColor(newWhiteKnight, computerColor));
            blackKnight = new ImageIcon(changeColor(newBlackKnight, playerColor));
            whiteBishop = new ImageIcon(changeColor(newWhiteBishop, computerColor));
            blackBishop = new ImageIcon(changeColor(newBlackBishop, playerColor));
            whiteQueen = new ImageIcon(changeColor(newWhiteQueen, computerColor));
            blackQueen = new ImageIcon(changeColor(newBlackQueen, playerColor));
            whiteKing = new ImageIcon(changeColor(newWhiteKing, computerColor));
            blackKing = new ImageIcon(changeColor(newBlackKing, playerColor));

            //Loop through array of BoardSquares, pull out the ChessPiece object
            //corresponding to that square, and set its new image accordingly
            for(int i = 0; i < 8; i++) {
              for(int j = 0; j < 8; j++) {
                square = squares[i][j];
                piece = board.getPieceAt(i, j);
                square.removeAll(); //remove old image

                if(piece != null) {
                  //opponent piece
                  if(piece.getSide() == true) {
                    if(piece.getType().equals("pawn"))
                      pieceImage = new JLabel(whitePawn);
                    else if(piece.getType().equals("rook"))
                      pieceImage = new JLabel(whiteRook);
                    else if(piece.getType().equals("knight"))
                      pieceImage = new JLabel(whiteKnight);
                    else if(piece.getType().equals("bishop"))
                      pieceImage = new JLabel(whiteBishop);
                    else if(piece.getType().equals("queen"))
                      pieceImage = new JLabel(whiteQueen);
                    else if(piece.getType().equals("king"))
                      pieceImage = new JLabel(whiteKing);
                  }
                  else {
                    if(piece.getType().equals("pawn"))
                      pieceImage = new JLabel(blackPawn);
                    else if(piece.getType().equals("rook"))
                      pieceImage = new JLabel(blackRook);
                    else if(piece.getType().equals("knight"))
                      pieceImage = new JLabel(blackKnight);
                    else if(piece.getType().equals("bishop"))
                      pieceImage = new JLabel(blackBishop);
                    else if(piece.getType().equals("queen"))
                      pieceImage = new JLabel(blackQueen);
                    else if(piece.getType().equals("king"))
                      pieceImage = new JLabel(blackKing);
                  }
                  square.add(pieceImage);
                }
                newBoardPanel.add(square);
              }
            }
          }
          changeColor.dispose();
          gameWindow.remove(boardPanel);
          gameWindow.add(newBoardPanel);
        }
      }
    });

    //If the user presses cancel, we can just get rid of the frame.
    cancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        changeColor.dispose();
      }
    });
  }

  //Support method that changes the color of each pixel on the image
  private BufferedImage changeColor(BufferedImage image, Color color) {
    for(int y = 0; y < image.getHeight(); y++) {
        for(int x = 0; x < image.getWidth(); x++) {
          //Ignore transparent pixels
          if(((image.getRGB(x, y) >> 24) & 0xff) == 0) {
            continue;
          }
          //Ignore black pixels (the border and accents)
          else if((image.getRGB(x, y) & 0x00FFFFFF) == 0) {
            continue;
          }
          image.setRGB(x, y, color.getRGB());
        }
      }
      return image;
  }

  /*
  * Converts a given Image into a BufferedImage
  *
  * Found here: http://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
  *
  * @param img The Image to be converted
  * @return The converted BufferedImage
  */
 public static BufferedImage toBufferedImage(Image img) {
     if (img instanceof BufferedImage) {
         return (BufferedImage) img;
     }

     // Create a buffered image with transparency
     BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

     // Draw the image on to the buffered image
     Graphics2D bGr = bimage.createGraphics();
     bGr.drawImage(img, 0, 0, null);
     bGr.dispose();

     // Return the buffered image
     return bimage;
 }

  public void resetTimer() {
    timer.removeActionListener(displayTime);

    displayTime = new ActionListener() {
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
    };

    timer.addActionListener(displayTime);
    timer.start();
    revalidate();
    repaint();
  }

  //Waits for mouse click, and then finds the JPanel representing the square so that we can pick up the piece.
	public void mousePressed(MouseEvent e){
	
	try{
		
		space = null;
    piece = null;
    //Gets the component at the location the user clicked
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

    //if the clicked location is an empty square, return
		if (spotOnBoard instanceof JPanel) return;

    //Otherwise, the clicked locaiton contains a piece image, so we need to get its BoardSquare
    oldParent = spotOnBoard.getParent();
    square = (BoardSquare)oldParent;

    //Get the coords of the square
	  oldRow = square.getRow();
	  oldCol = square.getColumn();

    //Find the piece that is at this location
	  piece = board.getPieceAt(oldRow, oldCol);

    //If it is not your turn, return.
	  if(bottomTurn != piece.getSide())
		  return;

    //Get the location of the parent component
		Point parentLocation = spotOnBoard.getParent().getLocation();
		deltaX = parentLocation.x - e.getX();
		deltaY = parentLocation.y - e.getY();
		space = (JLabel)spotOnBoard;

    //Make it look like we are "picking up" the chess piece so the user knows the click did something
		space.setLocation(e.getX() + deltaX/2, e.getY() + deltaY/2);
		space.setSize(space.getWidth(), space.getHeight());
    //Put the piece in the drag layer so we drag it
		layeredPane.add(space, JLayeredPane.DRAG_LAYER);
	}
	catch(NullPointerException npe) {
		System.out.println("Wrong place to click!");
	}
	
	}

  //Waits for the mouse to be dragged, and displays the updated location.
	public void mouseDragged(MouseEvent e) {
		if (space == null) return;
    //Update the location as we drag the piece
		space.setLocation(e.getX() + deltaX/2, e.getY() + deltaY/2);
	}

  /*
  * Waits for the mouse to be released, and then sets the piece down at the current BoardSquare,
  * replacing the piece that was there if the move was legal.
  */
	public void mouseReleased(MouseEvent e) {
		if(space == null) return;

    int[] newPos;

		space.setVisible(false);

    //Find the component at the space we are moving to
		Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());

    //See if theres a piece there already
		if (spotOnBoard instanceof JLabel){
      //Get parent the chess piece (the BoardSquare)
			Container parent = spotOnBoard.getParent();
	    square2 = (BoardSquare)parent;

      //Get coords of new square
	    newRow = square2.getRow();
	    newCol = square2.getColumn();

      //If the new square is teh exact same locaiton as the old square, we haven't actually moved
      if((newRow == oldRow) && (newCol == oldCol)) {
        oldParent.add(space);
        space.setVisible(true);
        return;
      }

      //Find the piece thats located here
      takenPiece = board.getPieceAt(newRow, newCol);  //piece that is potentially going to be taken


      //See if this move is legal. newPos will be the location of teh new square if it was legal, or the location of the old square if not
      newPos = piece.move(board, newRow, newCol);

      //If the newPos is of the new square, the move was legal
      if((newPos[0] == newRow) && (newPos[1] == newCol)) {
        parent.remove(0); //Remove the piece here
        parent.add(space);  //Add the piece we moved here
        board.update(oldRow, oldCol, newRow, newCol); //Update the ChessBoard object accordingly

        //Update turn accordingly
        bottomTurn = false;

        //capture the piece when move is legal
        capture(takenPiece);
        
        resetTimer();
      }
      //If the newPos is the old position, the move was not legal
      else {
        oldParent.add(space); //Just put the piece back at its original location
      }
		}
    //Otherwise, the square is empty
		else {
      //Get the BoardSquare
			Container parent = (Container)spotOnBoard;
      square2 = (BoardSquare)parent;

      //Get the new coords
      newRow = square2.getRow();
      newCol = square2.getColumn();

      //If the new square is teh exact same locaiton as the old square, we haven't actually moved
      if((newRow == oldRow) && (newCol == oldCol)) {
        oldParent.add(space);
        space.setVisible(true);
        return;
      }

      //See if this move is legal. newPos will be the location of teh new square if it was legal, or the location of the old square if not
      newPos = piece.move(board, newRow, newCol);

      //If the newPos is of the new square, the move was legal
      if((newPos[0] == newRow) && (newPos[1] == newCol)) {
        parent.add(space); //Put the piece at this square
        board.update(oldRow, oldCol, newRow, newCol); //Update the ChessBoard object accordingly

        //If the move was an en passant, we need to remove the piece appropriately
        if(newPos[2] != -1) {
          BoardSquare enPassant = squares[newPos[2]][newPos[3]];

          takenPiece = board.getPieceAt(newPos[2], newPos[3]);
          enPassant.removeAll();
          enPassant.revalidate();
          enPassant.repaint();

          board.removePiece(newPos[2], newPos[3]); //remove the piece from the ChessBoard object
          capture(takenPiece);
        }

        
        
        //Update turn accordingly
        bottomTurn = false;
        resetTimer();
      }
      //If the newPos is the old position, the move was not legal
      else {
        oldParent.add(space); //Just put the piece back at its original location
      }

		}

		space.setVisible(true);//Make the piece visible

    if(bottomTurn == false) {
      computerMove();
      bottomTurn = true;
      resetTimer();
      board.printBoard();
    }
	}
	
	private void flipCapArea() {
		DefaultListModel tempList = new DefaultListModel();
		tempList = topListModel;
		topListModel = botListModel;
		botListModel = tempList;
		
	}
	
	//capture piece logic
	//todo: style it
	private void capture(ChessPiece p) {
		System.out.println(p.getType() + " is captured");
		
		switch (p.getType()) {
			case "pawn":
				if(p.getSide()) topListModel.addElement(resize(whitePawn));
				else botListModel.addElement(resize(blackPawn));
				break;
			case "rook":
				if(p.getSide()) topListModel.addElement(resize(whiteRook));
				else botListModel.addElement(resize(blackRook));
				break;
			case "knight":
				if(p.getSide()) topListModel.addElement(resize(whiteKnight));
				else botListModel.addElement(resize(blackKnight));
				break;
			case "bishop":
				if(p.getSide()) topListModel.addElement(resize(whiteBishop));
				else botListModel.addElement(resize(blackBishop));
				break;
			case "queen":
				if(p.getSide()) topListModel.addElement(resize(whiteQueen));
				else botListModel.addElement(resize(blackQueen));
				break;
			case "king":
				if(p.getSide()) topListModel.addElement(resize(whiteKing));
				else botListModel.addElement(resize(blackKing));
				break;
		}
		
	}
	
	//helper method resize image to fit capture area
	//the only reason to implement the process as a method is to reduce unnecessary code repeat
	private ImageIcon resize(ImageIcon icon) {
		Image img = icon.getImage();
		img = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
	
  public void computerMove() {
    String fen = board.generateFEN(false);

    String move = stockfish.getBestMove(fen, 100);
    int compOrigCol = (int)move.charAt(0) - (int)'a'; //Gets array position of the letter - for instance, 'a' becomes 0
    int compOrigRow = 8 - (Character.getNumericValue(move.charAt(1)) - 1) - 1;
    int compNewCol = (int)move.charAt(2) - (int)'a';
    int compNewRow = 8 - (Character.getNumericValue(move.charAt(3)) - 1) - 1;
    BoardSquare oldCompSquare = squares[compOrigRow][compOrigCol];
    BoardSquare compSquare = squares[compNewRow][compNewCol];
    JLabel pieceToMove = null;
    boolean hasLabel = false;

    System.out.println(compOrigRow + " " + compNewRow);

    for (Component jc : oldCompSquare.getComponents()) {
      if(jc instanceof JLabel) {
          compSpace = (JLabel)jc;
          break;
      }
    }

    for (Component jc : compSquare.getComponents()) {
      if(jc instanceof JLabel) {
        hasLabel = true;
        break;
      }
    }

    //Component spotOnBoard2 = gameWindow.findComponentAt(compSquare.getLocation().x, compSquare.getLocation().y);

    compSpace.setVisible(false);
    Container parent = (Container)compSquare;

    System.out.println(compOrigRow + " " + compOrigCol + " " + compNewRow + " " + compNewCol);

    if(hasLabel) {
      System.out.println("there was a piece there");
      parent.remove(0);
      parent.add(compSpace);
      board.update(compOrigRow, compOrigCol, compNewRow, compNewCol); //Update the ChessBoard object accordingly
    }
    else {
      System.out.println("it was empty");
      parent.add(compSpace);
      board.update(compOrigRow, compOrigCol, compNewRow, compNewCol); //Update the ChessBoard object accordingly
    }

    compSpace.setVisible(true);
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

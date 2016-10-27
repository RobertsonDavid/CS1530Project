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
import java.io.FileNotFoundException;


public class GUI extends JFrame implements MouseListener, MouseMotionListener {
  private static final long serialVersionUID = 1L;
  private JPanel boardPanel;
  private JPanel gameWindow;
  private JLayeredPane layeredPane;
  private static JFrame chooseColor;
  private static JLabel timerLabel = new JLabel();
  
  private JLabel space = null;
  private BoardSquare square = null;
  private ChessPiece piece = null;
  private int oldRow, oldCol, newRow, newCol;
  
  private BoardSquare[][] squares = new BoardSquare[8][8]; //Array of BoardSquares which makes up the actual UI board
  private ChessBoard board;  //Backing ChessBoard object
  String[] columnLabels = {"A", "B", "C", "D", "E", "F", "G", "H"};
  int deltaX, deltaY;
  //Holds the color choice
  public String colorChoice = "White";
  private static int seconds = 300;
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
    
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    JButton newGame = new JButton("New Game");
    toolbar.add(newGame);     //When they click this, bring up the same window that appears normally (choose color)
    JButton saveGame =new JButton("Save Game");
    JButton loadGame= new JButton("Load Game");
    toolbar.add(saveGame); //When they click this, bring up window that lets them name game save (or maybe just automatically name it like day/month/year/time
    toolbar.add(new JButton("Load Game"));  //When they click this, bring up a window that lets them choose a new game
    
    gameWindow = resetWindow(boardPanel, toolbar, timers); //Pass it the chessboard panel
    gameWindow.setPreferredSize(new Dimension(600, 600));
    gameWindow.setBounds(0, 0, 600, 600);
    layeredPane.add(gameWindow, JLayeredPane.DEFAULT_LAYER);
    
    //if new Game is selected
    newGame.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        getColor(); 
      } 
    } );
    
    //if save game is selected
    //need to add support for unique saving names
    saveGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        save();
      }
    });
    
    //if load game is selected
    //need to add support for selecting load file
    
    loadGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try{
          boardPanel= load();
          gameWindow.add(boardPanel);
        }
        catch (FileNotFoundException er){
          System.out.println("File not found!");
        }
      }
    });
      
    
  }
  //saves the backing chessboard array to a file
  private void save(){
    GameState saver= new GameState();
    saver.save(board);
  }
  
  //loads the saved file into the chessboard array
  //refresh the gui to reflect changes
  private JPanel load() throws FileNotFoundException{
    GameState loader= new GameState();
    board= loader.load();
    
    //reset the gui
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
      
      for(int j = 0; j < 8; j++) {
        //Put the piece images as an ImageIcon object on the button (button.setIcon())
        BoardSquare square = new BoardSquare(i, j);
        JLabel pieceImage = null;
        square.setSize(new Dimension(100, 100));
        square.setLayout(new GridBagLayout());
        
        ChessPiece piece= board.getPieceAt(i,j);
        
        if(i % 2 == 0) {
          white = false;
        }
        else {
          white = true;
        }
        
        if(white == true){
          square.setBackground(Color.WHITE);
          white = false;
        }
        else {
          square.setBackground(Color.GRAY);
          white = true;
        }
        
        //if piece should be loaded in square, update square
        if (piece!=null)
        {
          if(piece.getType().equals("rook"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhiteRook);  //put white rook in square
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackRook); //put black rook in square
          }
          if(piece.getType().equals("pawn"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhitePawn);  //put white pawn in square
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackPawn);  //put black pawn in square
          }
          if(piece.getType().equals("queen"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhiteQueen);  
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackQueen);
          }
          if(piece.getType().equals("king"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhiteKing);  
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackKing);
          }
          if(piece.getType().equals("bishop"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhiteBishop);
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackBishop);
          }
          if(piece.getType().equals("knight"))
          {
            if(piece.getSide()==true)
              pieceImage = new JLabel(WhiteKnight);
            
            if(piece.getSide()==false)
              pieceImage = new JLabel(BlackKnight);
          }
          
        }
        square.add(pieceImage);
        squares[i][j] = square;
        boardPanel.add(square);
      }
    }
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
    JRadioButton white = new JRadioButton("White", true);
    JRadioButton black = new JRadioButton("Black");
    
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
        System.out.println(colorChoice);
      }
    });
    
    //if black is selected colorChoice is black
    black.addItemListener(new ItemListener(){
      public void itemStateChanged(ItemEvent e){
        colorChoice = "Black";
        System.out.println(colorChoice);
      }
    });
    
    //if start game is chosen remove window
    startGame.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        chooseColor.setVisible(false);
      } 
    } );
    
    //if cancel is chosen remove window
    cancel.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        chooseColor.setVisible(false);
      } 
    } );
    
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
    board = new ChessBoard(); //This resets the backing ChessBoard object, which contains the array of ChessPieces
    
    return boardPanel;
  }
  
  //Waits for mouse click, and then finds the JPanel representing the square so that we can pick up the piece.
  public void mousePressed(MouseEvent e){
    space = null;
    piece = null;
    Component spotOnBoard =  gameWindow.findComponentAt(e.getX(), e.getY());
    
    if (spotOnBoard instanceof JPanel) return;
    
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
      parent.remove(0);
      parent.add(space);
    }
    else {
      Container parent = (Container)spotOnBoard;
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





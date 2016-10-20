package chess;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class GameState {
  ChessBoard game;
  public GameState() {
  }

  //returns true on success
  //add support for naming files after testing
  //needs support for showing who's turn it will be
  public void save(ChessBoard gameOut) {
    game=gameOut;
    ChessPiece savePiece;
    Writer writer = null;

    try {
      writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("filename.txt"), "utf-8"));

      for(int x=0; x<=7; x++) {
        for(int y=0; y<=7; y++) {
          savePiece=game.getPieceAt(x,y);
          if(savePiece!=null) {
            String type= savePiece.type;
            Boolean color= savePiece.side;
            Boolean firstMove = savePiece.firstMove;
            Boolean topOfBoard = savePiece.topOfBoard;
            writer.write(x+ " " +y+ " " +type+" "+color + " " +firstMove + " " +topOfBoard +",");
          }
        }
      }
      //writer.write("Something");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        writer.close();
      }
      catch (Exception e) {
        //not vital
      }
    }

  }

  //returns a new chessboard
  //need method for clearing chessboard
  //need method for adding pieces to empty board one by one
  // need to add support for multiple saves
  public ChessBoard load() throws FileNotFoundException {
    ChessBoard loadGame= new ChessBoard();
    //need method of clearing pieces from 2d array
    loadGame.clearBoard();
    Scanner scan=new Scanner(new File("filename.txt"));
    scan.useDelimiter(",");
    String type;
    boolean color;
    boolean firstMove;
    boolean topOfBoard;
    int xcoord;
    int ycoord;

    while(scan.hasNext()) {
      String pieceInfo=scan.next();
      String []split = pieceInfo.split(" ");
      //if the file is corrupted, return a new game
      try {
        xcoord= Integer.parseInt(split[0]);
        ycoord=Integer.parseInt(split[1]);
      }
      catch (NumberFormatException e) {
        System.out.println("Save File Corrupted!");
        return loadGame= new ChessBoard();
      }
      type=split[2];
      String[] typesPoss= new String[6];    //array of possible piece types
      typesPoss[0]= "pawn"; typesPoss[1]="rook"; typesPoss[2]="knight"; typesPoss[3]="bishop"; typesPoss[4]="queen"; typesPoss[5]="king";
      if(!type.equals(typesPoss[0]) && !type.equals(typesPoss[1]) && !type.equals(typesPoss[2]) && !type.equals(typesPoss[3]) && !type.equals(typesPoss[4]) && !type.equals(typesPoss[5])) {
        System.out.println("Save File Corrupted!");
        return loadGame= new ChessBoard();
      }

      if(split[3].equals("false"))
        color=false;
      else if(split[3].equals("true"))
        color=true;
      else {
        System.out.println("Save File Corrupted!");
        return loadGame= new ChessBoard();
      }

      if(split[4].equals("false"))
        firstMove = false;
      else if(split[4].equals("true"))
        firstMove = true;
      else {
        System.out.println("Save File Corrupted!");
        return loadGame = new ChessBoard();
      }

      if(split[5].equals("false"))
        topOfBoard = false;
      else if(split[5].equals("true"))
        topOfBoard = true;
      else {
        System.out.println("Save File Corrupted!");
        return loadGame = new ChessBoard();
      }
      ///needed method addPiece(int, int, String, boolean)
      loadGame.addPiece(xcoord, ycoord, type, color, firstMove, topOfBoard);
    }
    return loadGame;
  }
}

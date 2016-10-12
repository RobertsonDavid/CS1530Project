package chess;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class gameState
{
  ChessBoard game;
  public gameState()
  {
  }
  
  //returns true on success
  //add support for naming files after testing
  //needs support for showing who's turn it will be
  public void save(ChessBoard gameOut)
  {
    game=gameOut;
    ChessPiece savePiece;
    Writer writer = null;
    
    try 
    {
      writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("filename.txt"), "utf-8"));
      
      for(int x=0; x<=7; x++)
      {
        for(int y=0; y<=7; y++)
        {
          savePiece=game.getPieceAt(x,y);
          if(savePiece!=null)
          {
            String type= savePiece.getType();
            Boolean color= savePiece.getSide();
            writer.write(x+ " " +y+ " " +type+" "+color +",");
          }
        }
      }
      //writer.write("Something");
    }
    catch (IOException e) 
    {
      e.printStackTrace();
    } 
    finally 
    {
      try 
      {
        writer.close();
      } 
      catch (Exception e) 
      {//not vital
      }
    }
    
  }
  
  //returns a new chessboard
  //need method for clearing chessboard
  //need method for adding pieces to empty board one by one
  // need to add support for multiple saves
  public ChessBoard load()
  {
    ChessBoard loadGame= new ChessBoard();
    //need method of clearing pieces from 2d array
    loadGame.clear();
    Scanner scan=new Scanner(new File("filename.txt"));
    scan.useDelimiter(",");
    String type;
    Boolean color;
    int xcoord;
    int ycoord;
    
    while(scan.hasNext())
    {
      String pieceInfo=scan.next();
      String []split = pieceInfo.split(" ");
      xcoord= Integer.parseInt(split[0]);
      ycoord=Integer.parseInt(split[1]);
      type=split[2];
      if(split[3].equals("false"))
        color=false;
      else if(split[3].equals("true"))
        color=true;
      else
      {
        System.out.println("Error determining piece color!");
      }
      
      ///needed method addPiece(int, int, String, boolean)
      loadGame.addPiece(xcoord, ycoord, type, color); 
    }
    return loadGame;
  }
}
package chess;

public class LaboonChess {
  public static void main(String args[]){
    System.out.println("Hello World.");
  }

  public static int square(double value){
    Double s = Math.pow(value, 2);
    return s.intValue();
  }

  public static String reverse(String str){
    StringBuilder input = new StringBuilder();
    input.append(str);
    input = input.reverse();
    return input.toString();
  }

  public static int right_triangle_hypoteneuse(int side1, int side2){
    Double r = Math.pow(side1, 2) + Math.pow(side2, 2);
    return r.intValue();
  }
}

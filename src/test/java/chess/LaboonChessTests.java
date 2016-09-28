import org.junit.Test;
import static org.junit.Assert.*;

import chess.LaboonChess;

public class LaboonChessTests {

  @Test
  public void square1() {
    assertEquals(LaboonChess.square(2), 4);
  }

  @Test
  public void square2() {
    assertEquals(LaboonChess.square(3), 9);
  }

  @Test
  public void square3() {
    assertEquals(LaboonChess.square(4), 16);
  }

  @Test
  public void reverse1() {
    assertEquals(LaboonChess.reverse("Hello"), "olleH");
  }

  @Test
  public void reverse2() {
    assertEquals(LaboonChess.reverse("Test"), "tseT");
  }

  @Test
  public void reverse3() {
    assertEquals(LaboonChess.reverse("Reverse"), "esreveR");
  }

  @Test
  public void right_triangle_hypoteneuse1() {
    assertEquals(LaboonChess.right_triangle_hypoteneuse(3, 4), 25);
  }

  @Test
  public void right_triangle_hypoteneuse2() {
    assertEquals(LaboonChess.right_triangle_hypoteneuse(1, 2), 5);
  }

  @Test
  public void right_triangle_hypoteneuse3() {
    assertEquals(LaboonChess.right_triangle_hypoteneuse(2, 3), 13);
  }

}

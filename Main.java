import java.util.*;
import java.io.InputStream;

class Main {
  public static void main(String[] args) {
    Parser p = new Parser();
    List<List<Integer>> courses = p.listOfRosters();
    System.out.println(courses);
  }
}

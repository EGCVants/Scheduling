import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class GraphV {
  
  private HashMap<Integer, Set<Integer>> studentMap;
  private boolean [][] adjMatrix;
  private int rosterSize;
  
  public GraphV(){
      studentMap = new HashMap<>();
  }

  public void setUpMap(List<List<Integer>> roster){
    rosterSize = roster.size(); //Only have due to issue with CT class
    int counter;            //by the end, will represents total # courses
    for(counter = 0; counter < roster.size(); counter++){
        registerStudent(roster.get(counter), counter);
    }
    //After HashMap has been set up, we create a double boolean matrix
    setUpMatrix(counter);
  }
  public void registerStudent(List<Integer> students, int courseID){
    //Turning parameters into a mapping of students keys with sets of courses
    for(int student : students){ 
      //studCourses given the set mapped by student key
      Set<Integer> studCourses = studentMap.get(student); 
      // studCourses was mapped to null, student key was mapped to nothing
      if(studCourses == null){ 
        studCourses = new HashSet<>(); //Refreshes studCourses set
        studentMap.put(student, studCourses); //Maps student to new set
      }
      studCourses.add(courseID);//The set exists, so we add cID
    }
  }
  //Matrix will represent relation between courses & timeslots for CT class
  public void setUpMatrix(int i){
      adjMatrix = new boolean[i][i];
  }
  
  public  void checkMap(){
    //Traverse the Sets in the
    //If the Set has more that one value(course) in it, indicates a conflict
    //(conflict = share at least 1 student)
    //Traverse that Set, each course creates an edge with every
    //other course in that Set, excluding itself
    //By the end creates Matrix of which courses conflict with each other
    for(Set<Integer> thisSet : studentMap.values()){ 
      if(thisSet.size() > 1){
        System.out.println(thisSet);
        for(int course : thisSet){
          for(int otherCourse : thisSet){
            if(course != otherCourse){
              createEdge(course, otherCourse);
            }
          }
        }
      }
    }
  }
  
  public void createEdge(int i, int j){
    adjMatrix[i][j] = true;
  }
  
  public void printEdges(){
    System.out.println("Edge shared between nodes:"); //Just to help better display
    for(int i = 0; i < adjMatrix.length; i++){
      for(int j = 0; j < adjMatrix.length; j++){
        if(adjMatrix[i][j]){
          System.out.println(i + " " + j);
        }
      }
    }
  }

  //Used for ColorTime class
  public boolean[][] getMatrix(){
    return adjMatrix;
  }

  //Method used for ColorTime class, exists due to an issue
  public int getRosterSize(){
    return rosterSize;
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    GraphV graph = new GraphV();
    //Sets up the HashMap from Student --> Set<Courses>
    graph.setUpMap(p.listOfRosters()); 
    //Sees which courses have edges with each other
    graph.checkMap();
    graph.printEdges();
  }
  
} //Class Closing Bracket
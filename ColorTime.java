import java.util.HashSet;
import java.util.Set;

public class ColorTime{
  private Set [] timeslot;
  private boolean [][] matrix;
  
  public ColorTime(int size, boolean [][] gotMatrix){
    //initialize timeslots (Array of sets) to a size of "size"
    timeslot = new Set[size]; 
    matrix = gotMatrix; //place boolean matrix into
    //Initializes each Set in the array
    for(int i = 0; i < timeslot.length; i++){
      timeslot[i] = new HashSet();
    }
  }

  public void setColors(){
    //Traverse boolean matrix; i = Course, j = Course/Timeslot
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix.length;j++){
        //If slot[i][j] is True, indicates course i conflicts with course j 
        //or course i has no edges with other courses at timeslot/color j
        //If slot[i][j] is False, indicates valid for i to be put
        //in timeslot j 
        if(!matrix[i][j]){
          //In Array of Sets, we place course i in the timeslot/set j
          timeslot[j].add(i);
          //Once we add the course, we need to existed nested loop,
          //So we just set j to value of where j < matrix.length is false
          //System.out.println(i + " was added to set " + j);
          j = matrix.length;
        }
      }
    }
  }
  public void printClass(){
    int slotNumber = 0;
    for(Set set : timeslot){
      if(!set.isEmpty()){
        System.out.println("Time " + slotNumber +  ": " + set);
        slotNumber++;
      }
    }
  }

  public static void main(String[] args){
    Parser p = new Parser();
    GraphV aGraph = new GraphV();
    aGraph.setUpMap(p.listOfRosters());
    aGraph.checkMap();
    //aGraph.printEdges();
    //Above worked
    ColorTime schedule = new ColorTime(aGraph.getRosterSize(), aGraph.getMatrix());
    schedule.setColors();
    schedule.printClass();
  }
  /* Issue:IDKW, p.listOfRosters().size() has size of 0 after graph.setUpMap()
    Parser p = new Parser();
    //p.listOfRosters() has size 4 
    GraphV graph = new GraphV();
    graph.setUpMap(p.listOfRosters());
    //p.listOfRosters() has size 0
    graph.checkMap();
    graph.printEdges();

    //Issue: 
    ColorTime schedule = new ColorTime(p.listOfRosters().size(), graph.getMatrix());

    //p.listOfRosters().size() = number of lists of students
    //getMatrix retrieves boolean matrix we created in the graph class
    schedule.setColors();
    schedule.printClass();
    */
  
}
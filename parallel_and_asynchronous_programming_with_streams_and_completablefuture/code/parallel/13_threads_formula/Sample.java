import java.util.*;

class Sample {              
  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
                         
    /*
    //computation intensive.
    //Never give more threads than number of cores.

    //IO intensive.
    //More than number of cores?
    
    //Blocking Factor.
    
    //Ratio of time the task is waiting on IO
    
    //Blocking factor for computation intensive = 0
    //Blocking factor 0 <= BF < 1
                                                
                                  Core
    //Number of threads =  _____________________
                            1 - Blocking Factor
                            
    */
  }              
}











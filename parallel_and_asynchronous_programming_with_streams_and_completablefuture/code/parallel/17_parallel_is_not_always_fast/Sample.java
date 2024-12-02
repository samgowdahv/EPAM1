import java.util.*;
import java.util.stream.*;

class Sample {       
  public static boolean isEven(int number) {
    return number % 2 == 0;
  }                        
  
  public static int doubleIt(int number) {
    return number * 2;
  }                   

  public static void doWork(boolean parallel) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    Stream<Integer> stream = numbers.stream();
      
    if(parallel) stream = stream.parallel();
   
    System.out.println(
      stream.filter(Sample::isEven)
            .mapToInt(Sample::doubleIt)
            .sum());
  } 
  
  public static void main(String[] args) {
    doWork(false);
    doWork(true);
    
    Timeit.code(() -> doWork(false));
    Timeit.code(() -> doWork(true));
  }              
}











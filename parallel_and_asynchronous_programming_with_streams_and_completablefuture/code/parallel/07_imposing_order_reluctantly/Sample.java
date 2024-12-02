import java.util.*;
import java.util.stream.*;

class Sample {   
  public static void printIt(int number) {
    System.out.println("The value is " + number + 
      " in thread " + Thread.currentThread());
  }                                     
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                   
      numbers.stream()
             .forEachOrdered(Sample::printIt);

      System.out.println("------------");
      numbers.parallelStream()
             .forEachOrdered(Sample::printIt);
  }              
}











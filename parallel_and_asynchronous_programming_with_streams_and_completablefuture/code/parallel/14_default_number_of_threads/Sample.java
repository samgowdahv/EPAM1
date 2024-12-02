import java.util.*;

class Sample {       
  public static int doubleIt(int number) {
    System.out.println(number + ":" + Thread.currentThread());
    try { Thread.sleep(1000); } catch(Exception ex) {}
    return number * 2;
  }                   
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
                    
    System.out.println(
      numbers.parallelStream()
             .mapToInt(Sample::doubleIt)
             .sum());
  }              
}











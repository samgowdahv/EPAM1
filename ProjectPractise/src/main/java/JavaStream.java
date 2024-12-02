import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStream {

    public static void febnonCiSeries(int n){
        int currentValue=0 , nextValue=1;
        for (int i = 0 ; i <= n ; i++)
        {
            System.out.print(currentValue+ " ");
            int next = currentValue + nextValue;
            currentValue = nextValue;
            nextValue =next;
        }

    }
    public static void rotateArrRIght(int[] arr, int n){
        for (int i=0 ; i < n ; i++)
        {
            int j, last;
            last = arr[arr.length-1];

            for (j = arr.length -1 ; j > 0 ; j --){
//                2,4,5,3,8,9
                arr[j] = arr[j-1];
            }
            arr[0] =last; //9,8,3,5,4,2

        }
        Arrays.stream(arr).forEach(System.out::println);
    }



    public static void main(String[] args) {


    List<Integer> input = Arrays.asList(1,4,6,8,6,6,5,10,4);
    //Find second higest number in given list
   int output =  input.stream().sorted(Collections.reverseOrder()).distinct().skip(1).findFirst().get();
        System.out.println("The second higest number is " +output);
    //Find second lowest number in given list
        int output1 =  input.stream().sorted().distinct().skip(1).findFirst().get();
        System.out.println("The second lowest number is " +output1);
    //Find First non repeated string in the given string
        String input1 = "SzbsccriberEntity";
        Map.Entry<Character, Long> characterLongEntry = input1.chars().mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(a -> a.getValue() == 1L).findFirst().get();
        System.out.println(characterLongEntry);
      //Generate feb series on the given input
        //O/P  0 1 1 2 3 5 8
        input.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

        febnonCiSeries(9);
        int[] arr = {2,4,5,3,8,9};
        rotateArrRIght(arr,1);
        //Sort the list in descending order
        List<Integer> input2 = List.of(3,6,8,10,34,18);
        input2.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
         //Sort the list in ascending order
        input2.stream().sorted().forEach(System.out::println);
        //Generate Febseries
        Integer integer = Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(7)
                .map(fib -> fib[0]).skip(4).findFirst().get();
        System.out.println(integer);



    }
}

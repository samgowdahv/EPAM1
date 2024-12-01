package main.java.org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetcodeSolution {
    public static void main(String[] args) {
      //  System.out.println(sumOftheList(Arrays.asList(1,2,4,5,5,6)));
        countOfWord( "I am doing an interview at EPAM I work for EPAM ");
    }
// Merging the list using stream
    static List<Integer> mergeListUsingFlatMap(){
        List<Integer> firstList = Arrays.asList(1,3,4,5,6,7);
        List<Integer> secondList = Arrays.asList(8,9,10,11);
        List<Integer> collect = Stream.concat(firstList.stream(), secondList.stream()).collect(Collectors.toList());

        return collect;
    }
    // find sum of the numbers in the list
    static int sumOftheList(List<Integer> input){
        return input.stream().reduce( (a,b) -> a+b).get();

    }
    //Find the count of duplicate word and display

    static void countOfWord(String str){

        Map<String, Long> collect = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<String,Long> entry : collect.entrySet())
        {
            if ( entry.getValue() > 1 && entry.getKey().length() >1)
                System.out.println(entry.getKey());
        }

    }


}

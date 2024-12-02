package ProjectPractise.src.main.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args) {
// We need to use comparator if we want to implement the custom sorting.
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())
                return -1;
                else if (o1.length() < o2.length())
                    return 1;
                else return 0;
            }
        };

        List<String> input = Arrays.asList("Sampat","gowda","Atharva","Aksha");

        Collections.sort(input,com);
        input.forEach(System.out::println);


    }
}

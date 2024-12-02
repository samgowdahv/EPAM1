package ProjectPractise.src.main.java;

import java.util.List;

public class JavaFresherQuestion {

    public static void main(String[] args) {

        List<Integer> input = List.of(1,2,3,4,5);

        Integer reduce = input.stream().reduce(0, (a, b) -> a + b);

        System.out.println(reduce);

    }
}

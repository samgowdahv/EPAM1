package FunctionalInterface;

import data.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

//consumer consumes, and has no return type
public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> consumer = (s) ->
                System.out.println(s.toUpperCase());
        consumer.accept("abc");
        int reduce = IntStream.range(1, 11).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);


    }

    static Consumer<List<Student>> studentConsumer = (s) -> {
        s.forEach(System.out::println);
    };


    public static void StudentListConsumer(List<Student> students) {
        studentConsumer.accept(students);
    }
}

package FunctionalInterface;

import data.Student;
import data.StudentRepository;

import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {


    static Supplier<Student> supplier = () -> {
        return new Student("@","name", 1, List.of("cricket"), "a", 55);
    };
    static Supplier<List<Student>> supplyStudent = StudentRepository::getStudents;

    public static void main(String[] args) {
        System.out.println(supplier.get());
        ConsumerExample.StudentListConsumer(supplyStudent.get());
    }
}

package ProjectPractise.src.main.java;

import ProjectPractise.src.main.java.Car;

import java.util.*;


public class ComparableTest {
    public static void main(String[] args) {

        Car car1 = new Car("Volvo",1);
        Car car2 = new Car("BMW",2);
        Car car3 = new Car("Skoda",3);
        List<Car> car = new ArrayList<>();
        car.add(car1);
        car.add(car2);
        car.add(car3);
        Collections.sort(car);
        for (Car car11:car) {
            System.out.println(car11.getId());
        }



    }




}

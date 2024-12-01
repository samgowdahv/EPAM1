package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


public class AdidasInterview {

         public static void sum(){
             Map<Integer ,String> map = new ConcurrentHashMap<>();
             map.put(1,"Sampath");
             map.put(2,"Akshatha");
             map.put(3,"Atharva");
             Map<Integer ,String> map1 = new ConcurrentHashMap<>();
             map1.put(1,"Sampath");
             map1.put(2,"Akshatha");
             map1.put(3,"Atharva");
            map.entrySet().forEach(System.out::println);
            System.out.println(map.hashCode());
             map1.entrySet().forEach(System.out::println);
             System.out.println(map1.hashCode() + map1.size() + map.size());
             System.out.println("Size of map1 is "+ map1.size() + "The size of map is " + map.size());
         }


    public static void main(String[] args) {

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank","Atharva");
        List<String> sortedNames = names.stream()
                .sorted((name1, name2) -> name2.charAt(1) - name1.charAt(1))
                .collect(Collectors.toList());
        Collections.sort(sortedNames);
        System.out.println("Names sorted by second character: " + sortedNames);
        Collections.sort(sortedNames,Collections.reverseOrder());
        System.out.println("Names sorted by second character: " + sortedNames);
        sum();


    }

    }


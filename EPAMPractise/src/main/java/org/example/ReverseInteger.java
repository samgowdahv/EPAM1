package main.java.org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReverseInteger {
    public static void main(String[] args) {
       System.out.println(reverseInt(321));
        //Square of the number
      //  Arrays.asList(1, 2, 3).stream().map(a -> a * a).forEach(System.out::println);
        //System.out.println(subString("sampath"));
      //  nonRepeat();
      System.out.println(nonRepeatedCharacter("sampath"));
    }

    static int reverseInt(int x) {//321
        int reverse1 = 0;
        while (x != 0) {
            if (reverse1 > Integer.MAX_VALUE / 10 || reverse1 < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int digit = x % 10; //3
            reverse1 = (reverse1 * 10) + digit; //3
            x = x / 10;
        }
        return reverse1;
    }



    static String subString(String input) {
        String output = null;
        LinkedHashMap<Character, Long> map = new LinkedHashMap<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (nonRepeatedCharacter(input.substring(i, j)) & input.length() > output.length())
                output = input.substring(i, j);
            }

        }
        return output;
    }

    static boolean nonRepeatedCharacter(String input) {
        char[] car = input.toCharArray();
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        for (char car1 : car) {
            linkedHashMap.put(car1, linkedHashMap.getOrDefault(car1, 0) + 1);

        }
        for (Map.Entry<Character, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey()+ " "+entry.getValue());
            if (entry.getValue() != 1) {
                return false;
            }



        }

        return true;
    }

            static void nonRepeat () {
                String input1 = "SAMPATH";
                LinkedHashMap<Character, Long> collect = (LinkedHashMap<Character, Long>) input1.chars().mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                // .entrySet().stream().filter(s -> s.getValue() == 1).findFirst().get().getKey();

     /*   for (Map.Entry<Character,Long> entry : collect.entrySet())
        {

            System.out.println(entry.getKey() +" "+ entry.getValue());

        } */

                char[] chars = input1.toCharArray();
                Map<Character, Integer> map = new LinkedHashMap<>();
                for (char input2 : chars) {
                    map.putIfAbsent(input2, 1);

                }

                for (Map.Entry<Character, Integer> in : map.entrySet()) {
                    System.out.println(in.getKey() + " " + in.getValue());
                }

            }


        }




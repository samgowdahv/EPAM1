package ProjectPractise.src.main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class GeneralMethods{
    static String firstNonRepeativeCharacter(){
        String input2 = "SzbsccriberEntity";
        char[] arr = input2.toCharArray();
        Map<Character , Integer>  elements = new HashMap<>();
        for (char c:arr)
        {
            elements.put(c,elements.getOrDefault(c,0)+1);
        }
        for (Map.Entry<Character, Integer> input3 : elements.entrySet())
        {
            if(input3.getValue() == 1) {
                System.out.println("The first non repeated character is " + input3.getKey());
                return String.valueOf(input3.getKey());
            }
        }
        return null;
    }


    static boolean isPalindromic(){
        String input ="geeksskeeg";
        for (int i=0, j= input.length() -1 ; i < input.length() && j > 0 ; i++ ,j--)
        {
            if (input.charAt(i) != input.charAt(j))
                return false;

        }
            return true;
    }



}


public class SwapNumbers {
    public static void main(String[] args) {
        int input = 3;
        int input1 =2;
        System.out.println("Befoer swapping value of a is "+input);
        System.out.println("Befoer swapping value of b is "+input1);
        input =  input + input1; //5
        input1 = input - input1; //3
        input =  input - input1;//2
        System.out.println("Befoer swapping value of a is "+input);
        System.out.println("Befoer swapping value of b is "+input1);

        GeneralMethods.firstNonRepeativeCharacter();
       // Java code to find the sum of the given list is
       int sum = IntStream.range(1,100).reduce((a ,b) -> a+b).getAsInt();
        System.out.println("The sum of the number is "+sum);
       double average =  IntStream.range(1,500).average().getAsDouble();
        System.out.println("The avearage of the number is "+average);

        GeneralMethods.isPalindromic();


}}


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class InputStringAndReturnDollarAmount {
    public static void main(String[] args) {
        String input = "*1 Rental $70,0000shopping $299. Expenses $800 . House$2,00,000";
        //Arrays.stream(input.split(" ")).forEach(System.out::println);
        Pattern pattern = Pattern.compile("\\$[\\d,]+");
        Matcher matcher = pattern.matcher(input);
        List<Double> sumList = new ArrayList<>();
        while(matcher.find())
        {
          String dollarAmount = matcher.group();
          double amount = Double.parseDouble(dollarAmount.replaceAll("[^\\d]",""));
            sumList.add(amount);

        }
        OptionalDouble sum = sumList.stream().mapToDouble(Double::doubleValue).min();
        System.out.println(sum.getAsDouble());
        List<Integer> integer = Arrays.asList(1,2,3,4,5,6,7,8,9);
        //Calculate the average value of given list
        OptionalDouble min = integer.stream().mapToInt(Integer::intValue).average();
        System.out.println(min.getAsDouble());
        List<String> strings = Arrays.asList("sampath","Atharva","Akshatha", "",""," ");

        strings.stream().filter(a -> !a.isEmpty() && !a.isBlank()).forEach(System.out::println);








    }
}

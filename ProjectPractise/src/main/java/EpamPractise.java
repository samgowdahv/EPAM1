public class EpamPractise {

    private static final String  units[] = {"","one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String tens[] = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    public static String convertToNumber(int num){
        // 123456
        if (num == 0) {
         System.out.println("Zero");
            return "Zero";
        }

        if (num < 20)
        {
            System.out.println(tens[num]);
            return tens[num];
        }

//            return convertToNumber(num).trim();

        return null;



    }



    public  static void main(String[] args) {

       convertToNumber(19);



    }


}

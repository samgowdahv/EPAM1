import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionWithCarry {
    public static void main(String[] args) {
        // 234
        // 567
        int[] a = {2,3,4};
        int[] b= {5,6,7};
         int[] sumArray = new int[Math.min(a.length,b.length)];

         for (int i=0 ; i < sumArray.length; i++) {
             sumArray[i] = a[i] + b[i];
         }
         System.out.println(Arrays.toString(sumArray));
         System.out.println(Math.addExact(234,567));


        }
    }


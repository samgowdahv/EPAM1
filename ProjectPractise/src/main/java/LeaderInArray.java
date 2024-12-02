import java.util.ArrayList;
import java.util.List;

public class LeaderInArray {
    public static void main(String[] args) {
        int [] arr = {5,6,7,0,1,3,2};
        List<Integer> leaderarr = new ArrayList<>();
        int rightElement= arr[arr.length -1];
        for (int i =0 ; i < arr.length; i++) {
            if (i != arr.length-1) {
                if (arr[i +1] < arr[i ]) {
                    leaderarr.add(arr[i]);
                }
            }
        }
        leaderarr.forEach(System.out::println);
    }




}

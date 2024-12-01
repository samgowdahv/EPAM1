import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static int[] twonumbers(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int compliment = target - nums[i];

                if (nums[j] == compliment) {
                    System.out.print(nums[i]+ " "+ nums[j]);
                    return new int[] {i , j};
                }
            }

        }
        throw  new IllegalArgumentException(" No Match found");
    }
    //Merge sorted  array
    //nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3


    static int[] mergeArray(){
        int[] nums1 = {1,2,3,0,0,0};
       int[] nums2 = {2,5,6};
       Stream<Integer> stream1 = Arrays.stream(nums1).boxed();
        Stream<Integer> stream2 = Arrays.stream(nums2).boxed();
        int[] array = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).toArray();

        return array;
    }


    public static void main(String[] args) {
        twonumbers(new int[]{0,19,5,4,11,15}, 9 ); //Display the index of the numbers for the target numbes




    }
}

import java.util.Arrays;

public class Test_3 {
    public static int getMinMaxPairSum(int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int max = nums[left] + nums[right];
        left++;
        right--;
        while (left < right) {
            if (nums[left] + nums[right] > max) {
                max = nums[left] + nums[right];
            }
            left++;
            right--;
        }

        return max;
    }

    public static void main(String args[]) {
        int[] nums = new int[] {1, 100, 2, 99, 3, 98};
        System.out.println("Case 1: " + getMinMaxPairSum(nums));

        nums = new int[] {2, 4, 6, 8, 10, 12};
        System.out.println("Case 2: " + getMinMaxPairSum(nums));

        nums = new int[] {100000, 100000, 100000, 100000};
        System.out.println("Case 3: " + getMinMaxPairSum(nums));

        nums = new int[] {1, 1, 1, 1};
        System.out.println("Case 4: " + getMinMaxPairSum(nums));

        nums = new int[] {10, 10, 10, 90, 90, 90};
        System.out.println("Case 5: " + getMinMaxPairSum(nums));

        nums = new int[] {5, 6, 7, 1, 2, 100};
        System.out.println("Case 6: " + getMinMaxPairSum(nums));

    }
    
}

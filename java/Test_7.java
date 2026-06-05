import java.util.ArrayList;
import java.util.List;

class Test_7 {

    public static int maxSubArraySum_1(ArrayList<Integer> nums) {
        int currentSum = nums.get(0);
        int maxSum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            currentSum = Math.max(nums.get(i), currentSum + nums.get(i));
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

     public static int maxSubArraySum_2(ArrayList<Integer> nums) {
        int currentSum = nums.get(0);
        int maxSum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (currentSum >= 0 && nums.get(i) >= 0) {
                currentSum = currentSum + nums.get(i);
            } else if (currentSum >= 0 && nums.get(i) < 0) {
                currentSum = currentSum + nums.get(i);
            } else if (currentSum < 0 && nums.get(i) >= 0) {
                currentSum = nums.get(i);
            } else {
                currentSum = nums.get(i);
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static void main(String args[]) {
        ArrayList<Integer> nums = new ArrayList<>(List.of(2, 3, -8, 7, -1, 2, 3));
        System.out.println(nums + " " + maxSubArraySum_2(nums));
        nums = new ArrayList<>(List.of(-2, -4));
        System.out.println(nums + " " + maxSubArraySum_2(nums));
        nums = new ArrayList<>(List.of(5, 4, 1, 7, 8));
        System.out.println(nums + " " + maxSubArraySum_2(nums));
    }
}
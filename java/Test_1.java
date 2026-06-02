
import java.util.HashMap;

public class Test_1 {

    public static int getMaxDistinctSubStringSum(int[] nums, int k) {
        int currentSum = 0;
        int maxSum = 0;
        HashMap<Integer, Integer> mapCount = new HashMap<>();

        for (int startIndex = 0; startIndex <= nums.length - k; startIndex++) {
            if (startIndex == 0) {
                // startIndex == 0
                for (int i = startIndex; i <= startIndex + k - 1 ; i++) {
                    currentSum += nums[i];
                    if (mapCount.containsKey(nums[i])) {
                        int count = mapCount.get(nums[i]);
                        mapCount.replace(nums[i], count + 1);
                    } else {
                        mapCount.put(nums[i], 1);
                    }
                }
            } else {
                // startIndex >= 1
                currentSum = currentSum - nums[startIndex - 1] + nums[startIndex + k - 1];

                // add nums[startIndex + k - 1] to mapCount
                if (mapCount.containsKey(nums[startIndex + k - 1])) {
                    int count = mapCount.get(nums[startIndex + k - 1]);
                    mapCount.replace(nums[startIndex + k - 1], count + 1);
                } else {
                    mapCount.put(nums[startIndex + k - 1], 1);
                }
                
                // remove nums[startIndex - 1] from mapCount
                int count = mapCount.get(nums[startIndex - 1]);
                if (count == 1) {
                    mapCount.remove(nums[startIndex - 1]);
                } else {
                    mapCount.replace(nums[startIndex - 1], count - 1);
                }

            }
            if (mapCount.size() == k && currentSum > maxSum) {
                maxSum = currentSum;
            }
            /*
            System.out.println("\nCurrent Sum: " + currentSum + " mapCount.size(): " + mapCount.size());
            for (int i = startIndex; i <= startIndex + k - 1 ; i++) {
                System.out.print(nums[i] + " ");
            }
            */
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 4, 2, 9, 9, 9};
        int k = 3;
        System.out.println("\nCase 1: " + getMaxDistinctSubStringSum(nums, k));
        
        nums = new int[] {1, 2, 3, 4, 5};
        k = 2;
        System.out.println("\nCase 2: " + getMaxDistinctSubStringSum(nums, k));
    

        nums = new int[] {4, 4, 4, 4};
        k = 2;
        System.out.println("\nCase 3: " + getMaxDistinctSubStringSum(nums, k));
         
        nums = new int[] {1, 2, 1, 3, 4};
        k = 3;
        System.out.println("\nCase 4: " + getMaxDistinctSubStringSum(nums, k));

        nums = new int[] {1, 2, 3, 1, 5};
        k = 3;
        System.out.println("\nCase 5: " + getMaxDistinctSubStringSum(nums, k));

        nums = new int[] {1, 2, 3, 4};
        k = 4;
        System.out.println("\nCase 6: " + getMaxDistinctSubStringSum(nums, k));
         
    }
}
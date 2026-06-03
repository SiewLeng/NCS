public class Test_4 {

    public static int[] getNewArray(int[] arr, int start, int end) {
        int[] newArray = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            newArray[i - start] = arr[i];
        } 
        return newArray;
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public static void merge(int arr[], int l, int m, int r) {
        int leftPointer = 0;
        int rightPointer = 0;
        int[] leftArray = getNewArray(arr, l, m);
        int[] rightArray = getNewArray(arr, m + 1, r);

        while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
            if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                arr[l + leftPointer + rightPointer] = leftArray[leftPointer];
                leftPointer++;
            } else {
                arr[l + leftPointer + rightPointer] = rightArray[rightPointer];
                rightPointer++;
            }
        }
        while (leftPointer < leftArray.length) {
            arr[l + leftPointer + rightPointer] = leftArray[leftPointer];
            leftPointer++;
        }
         while (rightPointer < rightArray.length) {
            arr[l + leftPointer + rightPointer] = rightArray[rightPointer];
            rightPointer++;
        }
    }
    
    // Main function that sorts arr[l..r] using
    // merge()
    public static void mergeSort(int[] arr, int l, int r) {
       if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
       }
    }

    public static void main(String args[]) {
        int[] arr = new int[] {1, 4, 2, 3, 10};
        mergeSort(arr, 0,  arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
             System.out.print(arr[i] + " ");
        }
    }
}

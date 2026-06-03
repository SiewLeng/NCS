public class Test_4 {

    public static int[] getNewArray(int[] arr, int start, int end) {
        int[] newArray = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            newArray[i - start] = arr[i];
        } 
        return newArray;
    }

    // Merges two subarrays and return new arr
    // First subarray is leftarray which is sorted
    // Second subarray is rightarray whihc is sorted
    public static int[] merge(int[] leftArray, int[] rightArray) {
        int leftPointer = 0;
        int rightPointer = 0;
        int[] arr = new int[leftArray.length + rightArray.length];

        while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
            if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                arr[leftPointer + rightPointer] = leftArray[leftPointer];
                leftPointer++;
            } else {
                arr[leftPointer + rightPointer] = rightArray[rightPointer];
                rightPointer++;
            }
        }
        while (leftPointer < leftArray.length) {
            arr[leftPointer + rightPointer] = leftArray[leftPointer];
            leftPointer++;
        }
         while (rightPointer < rightArray.length) {
            arr[leftPointer + rightPointer] = rightArray[rightPointer];
            rightPointer++;
        }
        return arr;

    }
    
    // return a new array that is sorted
    public static int[] mergeSort(int[] arr) {
       if (arr.length == 1) return arr;
       int m = arr.length / 2;
       int[] leftArray = getNewArray(arr, 0, m - 1);
       int[] rightArray = getNewArray(arr, m, arr.length - 1);
       leftArray = mergeSort(leftArray);
       rightArray = mergeSort(rightArray);
       return merge(leftArray, rightArray);
    }

    public static void main(String args[]) {
        int[] arr = new int[] {1, 7, 6, 10, 5, 11};
        arr = mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
             System.out.print(arr[i] + " ");
        }
    }
}

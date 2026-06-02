import java.util.HashMap;

public class Test_2 {
    public static boolean canAddCharacter(Character c, HashMap<Character, Integer> countMap, int k) {
        if (countMap.containsKey(c)) {
            int count = countMap.get(c);
            countMap.replace(c, count + 1);
            return true;
        }
        if (countMap.size() <= k - 1) {
            countMap.put(c, 1);
            return true;
        }
        return false;
    }

    public static void removeCharacter(Character c, HashMap<Character, Integer> countMap) {
        int count = countMap.get(c);
        if (count == 1) {
            countMap.remove(c);
        }else {
            countMap.replace(c, count - 1);
        }
    }

    public static int getLongSubStringWithAtMostKDistinctChar(String s, int k) {
        if (k == 0) return 0;
        int max = 1;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> countMap = new HashMap<>();
        countMap.put(s.charAt(0), 1);
        while (right <= s.length() - 2) {
            Character nextChar = s.charAt(right + 1);
            if (canAddCharacter(nextChar, countMap, k)) {
                right++;
                int subStringLength = right - left + 1;
                if (subStringLength > max) {
                    System.out.println("");
                    for (int i = left; i <= right; i++) {
                        System.out.print(s.charAt(i));
                    }
                    max = subStringLength;
                }
            } else {
                removeCharacter(s.charAt(left), countMap);
                left++;
            }
        }
        return max;
    }

    public static void main(String args []) {
        String s = "eceba";
        int k = 2;
        System.out.println("\nCase 1: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "abc";
        k = 5;
        System.out.println("\nCase 2: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "abcdef";
        k = 0;
        System.out.println("\nCase 3: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "aaaaaaa";
        k = 1;
        System.out.println("\nCase 4: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "a";
        k = 1;
        System.out.println("\nCase 5: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "abaccc";
        k = 2;
        System.out.println("\nCase 6: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "abcdefghba";
        k = 3;
        System.out.println("\nCase 7: " + getLongSubStringWithAtMostKDistinctChar(s, k));

        s = "abababdbaba";
        k = 2;
        System.out.println("\nCase 8: " + getLongSubStringWithAtMostKDistinctChar(s, k));

    }
}
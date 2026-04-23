import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, true);
        }

        for (int num : nums) {
            if (map.containsKey(num - 1)) {
                map.put(num, false);
            }
        }

        int maxLength = 0;

        for (int num : nums) {
            if (map.get(num)) {
                int length = 1;
                while (map.containsKey(num + length)) {
                    length++;
                }
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Array: [100, 4, 200, 1, 3, 2]");
        System.out.println("Longest consecutive sequence: " + longestConsecutive(nums1));

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("\nArray: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]");
        System.out.println("Longest consecutive sequence: " + longestConsecutive(nums2));
    }
}

import java.util.HashMap;
import java.util.Map;

public class PairWithGivenSum {

    public static int[] findPair(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] result = findPair(arr, target);

        if (result.length == 2) {
            System.out.println("Pair found at indices: " + result[0] + " and " + result[1]);
            System.out.println("Values: " + arr[result[0]] + " + " + arr[result[1]] + " = " + target);
        } else {
            System.out.println("No pair found for target " + target);
        }

        int[] arr2 = {1, 5, 3, 8};
        int target2 = 20;
        int[] result2 = findPair(arr2, target2);

        if (result2.length == 2) {
            System.out.println("Pair found at indices: " + result2[0] + " and " + result2[1]);
        } else {
            System.out.println("No pair found for target " + target2);
        }
    }
}

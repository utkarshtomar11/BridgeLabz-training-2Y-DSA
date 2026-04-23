Here are all programs solved:

StringBuilder
Problem 1 — Reverse a String
javapublic class ReverseString {
    public static void main(String[] args) {
        String input = "hello";
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();
        System.out.println("Original : " + input);
        System.out.println("Reversed : " + sb.toString());
    }
}

Problem 2 — Remove Duplicate Characters
javaimport java.util.HashSet;

public class RemoveDuplicates {
    public static void main(String[] args) {
        String input = "programming";
        StringBuilder sb  = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                sb.append(c);
                seen.add(c);
            }
        }

        System.out.println("Original : " + input);
        System.out.println("No Dupes : " + sb.toString());
    }
}

StringBuffer
Problem 1 — Concatenate Array of Strings
javapublic class ConcatenateStrings {
    public static void main(String[] args) {
        String[] words = {"Java", " is", " powerful", " and", " fast"};
        StringBuffer sb = new StringBuffer();

        for (String word : words) {
            sb.append(word);
        }

        System.out.println("Concatenated: " + sb.toString());
    }
}

Problem 2 — StringBuilder vs StringBuffer Performance
javapublic class PerformanceComparison {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        String word = "hello";

        // StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(word);
        }
        long sbufferTime = System.nanoTime() - start;

        // StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(word);
        }
        long sbuilderTime = System.nanoTime() - start;

        System.out.println("StringBuffer  time: " + sbufferTime  + " ns");
        System.out.println("StringBuilder time: " + sbuilderTime + " ns");
        System.out.println(sbuilderTime < sbufferTime
                ? "StringBuilder is faster (no synchronization overhead)"
                : "StringBuffer was faster this run");
    }
}

FileReader
Problem 1 — Read File Line by Line
javaimport java.io.*;

public class ReadFileLineByLine {
    public static void main(String[] args) {
        String filePath = "sample.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Line " + lineNumber++ + ": " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

Problem 2 — Count Word Occurrences in a File
javaimport java.io.*;

public class CountWordOccurrences {
    public static void main(String[] args) {
        String filePath  = "sample.txt";
        String targetWord = "java";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split on any non-word character; ignore case
                String[] words = line.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (word.equals(targetWord.toLowerCase())) count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\"" + targetWord + "\" appears " + count + " time(s).");
    }
}

InputStreamReader
Problem 1 — Convert Byte Stream to Character Stream
javaimport java.io.*;
import java.nio.charset.StandardCharsets;

public class ByteToCharStream {
    public static void main(String[] args) {
        String filePath = "sample.txt";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

Problem 2 — Read Console Input and Write to File
javaimport java.io.*;

public class ConsoleToFile {
    public static void main(String[] args) {
        String outputFile = "output.txt";

        try (BufferedReader br     = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw     = new BufferedWriter(new FileWriter(outputFile))) {

            System.out.println("Type lines to save. Enter 'exit' to stop.");
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) break;
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Input saved to " + outputFile);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

Challenge — StringBuilder + StringBuffer + FileReader + InputStreamReader
javaimport java.io.*;
import java.nio.charset.StandardCharsets;

public class ChallengeComparison {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        String word = "hello";

        // --- StringBuilder ---
        StringBuilder sb = new StringBuilder();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) sb.append(word);
        long sbTime = System.nanoTime() - start;

        // --- StringBuffer ---
        StringBuffer sbuf = new StringBuffer();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) sbuf.append(word);
        long sbufTime = System.nanoTime() - start;

        System.out.println("=== String Concatenation (1,000,000 times) ===");
        System.out.println("StringBuilder : " + sbTime  + " ns");
        System.out.println("StringBuffer  : " + sbufTime + " ns");
        System.out.println("Faster        : " +
                (sbTime < sbufTime ? "StringBuilder" : "StringBuffer") + "\n");

        // --- FileReader word count ---
        String filePath = "large_file.txt";
        int wordCountFR = 0;

        start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) wordCountFR += words.length;
            }
        } catch (IOException e) {
            System.out.println("FileReader error: " + e.getMessage());
        }
        long frTime = System.nanoTime() - start;

        // --- InputStreamReader word count ---
        int wordCountISR = 0;

        start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) wordCountISR += words.length;
            }
        } catch (IOException e) {
            System.out.println("InputStreamReader error: " + e.getMessage());
        }
        long isrTime = System.nanoTime() - start;

        System.out.println("=== File Word Count ===");
        System.out.println("FileReader        — Words: " + wordCountFR  + " | Time: " + frTime  + " ns");
        System.out.println("InputStreamReader — Words: " + wordCountISR + " | Time: " + isrTime + " ns");
        System.out.println("Faster            : " +
                (frTime < isrTime ? "FileReader" : "InputStreamReader"));
    }
}

Linear Search
Problem 1 — First Negative Number
javapublic class FirstNegative {
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) return i;   // return early on first match
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 2, -5, 8, -1, 4};
        int index = findFirstNegative(arr);

        if (index != -1)
            System.out.println("First negative: " + arr[index] + " at index " + index);
        else
            System.out.println("No negative number found.");
    }
}

Problem 2 — Find First Sentence Containing a Word
javapublic class SearchWord {
    public static String findSentence(String[] sentences, String target) {
        for (String sentence : sentences) {
            // Check as whole word, case-insensitive
            for (String word : sentence.split("\\W+")) {
                if (word.equalsIgnoreCase(target)) return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language.",
            "Python is easy to learn.",
            "Java powers Android development.",
            "C++ is used in systems programming."
        };
        String target = "Python";
        System.out.println("Result: " + findSentence(sentences, target));
    }
}

Binary Search
Problem 1 — Rotation Point in Rotated Sorted Array
javapublic class RotationPoint {
    public static int findRotationPoint(int[] arr) {
        int left = 0, right = arr.length - 1;

        // If array is not rotated at all
        if (arr[left] <= arr[right]) return 0;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[right]) left  = mid + 1;  // smallest is in right half
            else                       right = mid;        // smallest is in left half
        }
        return left;    // index of smallest element
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 9, 15, 19, 2, 3};
        int index = findRotationPoint(arr);
        System.out.println("Rotation point index : " + index);
        System.out.println("Smallest element     : " + arr[index]);
    }
}

Problem 2 — Find a Peak Element
javapublic class PeakElement {
    public static int findPeak(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) left  = mid + 1;  // ascending → peak is right
            else                          right = mid;       // descending → peak is left
        }
        return left;    // left == right at the peak
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 8, 5, 2, 4, 3};
        int index = findPeak(arr);
        System.out.println("Peak element index : " + index);
        System.out.println("Peak element value : " + arr[index]);
    }
}

Problem 3 — Binary Search in 2D Sorted Matrix
javapublic class MatrixSearch {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int val = matrix[row][col];

            if      (val == target) return true;
            else if (val <  target) left  = mid + 1;
            else                    right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,  3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println("Search 11: " + searchMatrix(matrix, 11));  // true
        System.out.println("Search 13: " + searchMatrix(matrix, 13));  // false
    }
}

Problem 4 — First and Last Occurrence
javapublic class FirstLastOccurrence {
    public static int findFirst(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if      (arr[mid] == target) { result = mid; right = mid - 1; } // keep going left
            else if (arr[mid] <  target)   left  = mid + 1;
            else                           right = mid - 1;
        }
        return result;
    }

    public static int findLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if      (arr[mid] == target) { result = mid; left = mid + 1; }  // keep going right
            else if (arr[mid] <  target)   left  = mid + 1;
            else                           right = mid - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr    = {1, 2, 4, 4, 4, 5, 7, 8};
        int   target = 4;
        System.out.println("First occurrence of " + target + ": " + findFirst(arr, target));
        System.out.println("Last  occurrence of " + target + ": " + findLast(arr, target));
    }
}

Challenge — Linear Search + Binary Search Combined
javaimport java.util.Arrays;

public class SearchChallenge {

    // Linear Search: first missing positive integer
    public static int firstMissingPositive(int[] arr) {
        boolean[] present = new boolean[arr.length + 2];

        for (int val : arr) {
            if (val > 0 && val <= arr.length) {
                present[val] = true;
            }
        }
        for (int i = 1; i <= arr.length + 1; i++) {
            if (!present[i]) return i;
        }
        return 1;
    }

    // Binary Search: index of target in sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if      (arr[mid] == target) return mid;
            else if (arr[mid] <  target) left  = mid + 1;
            else                         right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] original = {3, 7, -1, 2, 5, 1, 8, -3, 4};

        System.out.println("Original array : " + Arrays.toString(original));
        System.out.println("First missing positive (Linear Search): "
                + firstMissingPositive(original));

        // Sort for binary search
        int[] sorted = original.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted array   : " + Arrays.toString(sorted));

        int target = 5;
        int index  = binarySearch(sorted, target);
        if (index != -1)
            System.out.println("Target " + target + " found at index " + index + " (Binary Search)");
        else
            System.out.println("Target " + target + " not found.");
    }
}

Program 1 — Bubble Sort (Student Marks)
javaimport java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        int[] marks = new int[n];

        System.out.println("Enter student marks:");
        for (int i = 0; i < n; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        System.out.println("\nUnsorted marks:");
        printArray(marks);

        // Bubble Sort
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (marks[j] > marks[j + 1]) {
                    // Swap
                    int temp    = marks[j];
                    marks[j]    = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }
            // No swaps in this pass means array is already sorted
            if (!swapped) break;
        }

        System.out.println("Sorted marks (ascending):");
        printArray(marks);
        scanner.close();
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }
}
How it works: Each pass bubbles the largest unsorted element to its correct position at the end. The swapped flag is an optimisation — if a full pass completes with zero swaps, the array is already sorted and we exit early. Without this, Bubble Sort is always O(n²); with it, best case on a nearly-sorted array drops to O(n).

Program 2 — Insertion Sort (Employee IDs)
javaimport java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        int[] ids = new int[n];

        System.out.println("Enter employee IDs:");
        for (int i = 0; i < n; i++) {
            System.out.print("Employee " + (i + 1) + ": ");
            ids[i] = scanner.nextInt();
        }

        System.out.println("\nUnsorted IDs:");
        printArray(ids);

        // Insertion Sort
        for (int i = 1; i < n; i++) {
            int key = ids[i];   // element to be inserted
            int j   = i - 1;

            // Shift elements of sorted part that are greater than key one position right
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = key;   // insert key into correct position
        }

        System.out.println("Sorted IDs (ascending):");
        printArray(ids);
        scanner.close();
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }
}
How it works: The array is conceptually split into a sorted left portion and unsorted right portion. For each element (key), we shift all larger sorted elements one step right to make room, then drop key into the gap. Complexity is O(n²) worst case but O(n) on nearly-sorted data, making it the preferred algorithm for small or nearly-sorted datasets.

Program 3 — Merge Sort (Book Prices)
javaimport java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of books: ");
        int n = scanner.nextInt();
        double[] prices = new double[n];

        System.out.println("Enter book prices:");
        for (int i = 0; i < n; i++) {
            System.out.print("Book " + (i + 1) + ": ");
            prices[i] = scanner.nextDouble();
        }

        System.out.println("\nUnsorted prices:");
        printArray(prices);

        mergeSort(prices, 0, n - 1);

        System.out.println("Sorted prices (ascending):");
        printArray(prices);
        scanner.close();
    }

    // Recursively divides the array
    static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);        // sort left half
            mergeSort(arr, mid + 1, right);   // sort right half
            merge(arr, left, mid, right);     // merge both halves
        }
    }

    // Merges two sorted sub-arrays arr[left..mid] and arr[mid+1..right]
    static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else               arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void printArray(double[] arr) {
        for (double val : arr) System.out.printf("%.2f ", val);
        System.out.println();
    }
}
How it works: Merge Sort follows divide-and-conquer — it keeps splitting the array in half until each piece has one element (trivially sorted), then merges pairs of sorted pieces back together. The merge() method uses temporary left/right arrays and a two-pointer technique to combine them in order. Time complexity is always Θ(n log n) regardless of input order, making it reliable for production use.

Program 4 — Quick Sort (Product Prices)
javaimport java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of products: ");
        int n = scanner.nextInt();
        double[] prices = new double[n];

        System.out.println("Enter product prices:");
        for (int i = 0; i < n; i++) {
            System.out.print("Product " + (i + 1) + ": ");
            prices[i] = scanner.nextDouble();
        }

        System.out.println("\nUnsorted prices:");
        printArray(prices);

        quickSort(prices, 0, n - 1);

        System.out.println("Sorted prices (ascending):");
        printArray(prices);
        scanner.close();
    }

    static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);   // sort left of pivot
            quickSort(arr, pivotIndex + 1, high);  // sort right of pivot
        }
    }

    // Partition: places pivot in correct position;
    // elements smaller than pivot go left, larger go right
    static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];   // last element as pivot
        int i = low - 1;            // index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i]      = arr[j];
                arr[j]      = temp;
            }
        }
        // Place pivot in correct position
        double temp  = arr[i + 1];
        arr[i + 1]   = arr[high];
        arr[high]    = temp;

        return i + 1;
    }

    static void printArray(double[] arr) {
        for (double val : arr) System.out.printf("%.2f ", val);
        System.out.println();
    }
}
How it works: Quick Sort picks a pivot (here the last element), then partitions the array so everything smaller than the pivot is left of it and everything larger is right of it — the pivot is now in its final sorted position. This is repeated recursively on each partition. Average case is O(n log n) but worst case (already sorted input with last-element pivot) degrades to O(n²). A randomised pivot avoids this in practice.

Program 5 — Selection Sort (Exam Scores)
javaimport java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        int[] scores = new int[n];

        System.out.println("Enter exam scores:");
        for (int i = 0; i < n; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            scores[i] = scanner.nextInt();
        }

        System.out.println("\nUnsorted scores:");
        printArray(scores);

        // Selection Sort
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;   // assume current position holds minimum

            // Find the actual minimum in the unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap minimum with first unsorted element
            if (minIndex != i) {
                int temp         = scores[i];
                scores[i]        = scores[minIndex];
                scores[minIndex] = temp;
            }
        }

        System.out.println("Sorted scores (ascending):");
        printArray(scores);
        scanner.close();
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }
}
How it works: In each pass, Selection Sort scans the entire unsorted portion to find the minimum element and swaps it into the correct position at the front. Unlike Bubble Sort, it makes at most n−1 swaps total (useful when writes are expensive), but always performs O(n²) comparisons regardless of input order — there's no early-exit optimisation possible.

Program 6 — Heap Sort (Job Applicant Salaries)
javaimport java.util.Scanner;

public class HeapSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of applicants: ");
        int n = scanner.nextInt();
        int[] salaries = new int[n];

        System.out.println("Enter expected salaries:");
        for (int i = 0; i < n; i++) {
            System.out.print("Applicant " + (i + 1) + ": ");
            salaries[i] = scanner.nextInt();
        }

        System.out.println("\nUnsorted salaries:");
        printArray(salaries);

        heapSort(salaries);

        System.out.println("Sorted salaries (ascending):");
        printArray(salaries);
        scanner.close();
    }

    static void heapSort(int[] arr) {
        int n = arr.length;

        // Phase 1: Build Max Heap (start from last non-leaf node)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Phase 2: Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum) to end
            int temp  = arr[0];
            arr[0]    = arr[i];
            arr[i]    = temp;

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Ensures subtree rooted at index i satisfies Max Heap property
    // n = current heap size
    static void heapify(int[] arr, int n, int i) {
        int largest = i;        // assume root is largest
        int left    = 2 * i + 1;
        int right   = 2 * i + 2;

        if (left  < n && arr[left]  > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        // If root is not the largest, swap and continue heapifying
        if (largest != i) {
            int temp    = arr[i];
            arr[i]      = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);   // recursively fix affected subtree
        }
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System..println();
    }
}
How it works: Heap Sort works in two phases. First, it builds a Max Heap from the array (the largest element is always at index 0). Then it repeatedly swaps the root (maximum) with the last unsorted element, shrinks the heap boundary by 1, and calls heapify() to restore the heap property. The result is a sorted array built from right to left. Time complexity is always O(n log n) and sorts in-place with O(1) extra space.

Program 7 — Counting Sort (Student Ages)
javaimport java.util.Scanner;

public class CountingSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        int[] ages = new int[n];

        System.out.println("Enter student ages (10 to 18):");
        for (int i = 0; i < n; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            ages[i] = scanner.nextInt();
            if (ages[i] < 10 || ages[i] > 18) {
                System.out.println("Invalid age. Please enter between 10 and 18.");
                i--;
            }
        }

        System.out.println("\nUnsorted ages:");
        printArray(ages);

        int[] sorted = countingSort(ages, 10, 18);

        System.out.println("Sorted ages (ascending):");
        printArray(sorted);
        scanner.close();
    }

    static int[] countingSort(int[] arr, int min, int max) {
        int range = max - min + 1;

        // Step 1: Count frequency of each age
        int[] count = new int[range];
        for (int val : arr) {
            count[val - min]++;
        }

        // Step 2: Compute cumulative counts (positions)
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build output array (traverse input right-to-left for stability)
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        return output;
    }

    static void printArray(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }
}

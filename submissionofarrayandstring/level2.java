Here are all 10 Java programs:

Program 1 — Employee Bonus Calculator (Zara)

java
import java.util.Scanner;

public class EmployeeBonus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] salary = new double[10];
        double[] yearsOfService = new double[10];
        double[] bonus = new double[10];
        double[] newSalary = new double[10];
        double totalBonus = 0, totalOldSalary = 0, totalNewSalary = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println("Employee " + (i + 1) + ":");
            while (true) {
                System.out.print("  Enter salary: ");
                salary[i] = scanner.nextDouble();
                if (salary[i] <= 0) {
                    System.out.println("  Invalid salary. Please enter again.");
                } else break;
            }
            while (true) {
                System.out.print("  Enter years of service: ");
                yearsOfService[i] = scanner.nextDouble();
                if (yearsOfService[i] < 0) {
                    System.out.println("  Invalid years of service. Please enter again.");
                } else break;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (yearsOfService[i] > 5) {
                bonus[i] = salary[i] * 0.05;
            } else {
                bonus[i] = salary[i] * 0.02;
            }
            newSalary[i] = salary[i] + bonus[i];
            totalBonus      += bonus[i];
            totalOldSalary  += salary[i];
            totalNewSalary  += newSalary[i];
        }

        System.out.println("\n--- Employee Salary Report ---");
        System.out.printf("%-10s %-12s %-10s %-12s %-12s%n",
                "Employee", "Old Salary", "Years", "Bonus", "New Salary");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-10d %-12.2f %-10.1f %-12.2f %-12.2f%n",
                    (i + 1), salary[i], yearsOfService[i], bonus[i], newSalary[i]);
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("Total Old Salary : %.2f%n", totalOldSalary);
        System.out.printf("Total Bonus Payout: %.2f%n", totalBonus);
        System.out.printf("Total New Salary : %.2f%n", totalNewSalary);
        scanner.close();
    }
}
Program 2 — Youngest and Tallest Friend

java
import java.util.Scanner;

public class YoungestTallest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = {"Amar", "Akbar", "Anthony"};
        double[] ages   = new double[3];
        double[] heights = new double[3];

        for (int i = 0; i < 3; i++) {
            System.out.println(names[i] + ":");
            System.out.print("  Age: ");
            ages[i] = scanner.nextDouble();
            System.out.print("  Height (cm): ");
            heights[i] = scanner.nextDouble();
        }

        int youngestIndex = 0, tallestIndex = 0;
        for (int i = 1; i < 3; i++) {
            if (ages[i] < ages[youngestIndex])     youngestIndex = i;
            if (heights[i] > heights[tallestIndex]) tallestIndex  = i;
        }

        System.out.println("\nYoungest friend: " + names[youngestIndex] +
                " (Age: " + ages[youngestIndex] + ")");
        System.out.println("Tallest friend : " + names[tallestIndex] +
                " (Height: " + heights[tallestIndex] + " cm)");
        scanner.close();
    }
}
Program 3 — Largest and Second Largest Digit (Limited to 10 digits)

java
import java.util.Scanner;

public class LargestDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = Math.abs(scanner.nextInt());

        int maxDigit = 10;
        int[] digits = new int[maxDigit];
        int index = 0;

        while (number != 0) {
            if (index == maxDigit) break;
            digits[index++] = number % 10;
            number /= 10;
        }

        int largest = 0, secondLargest = 0;
        for (int i = 0; i < index; i++) {
            if (digits[i] > largest) {
                secondLargest = largest;
                largest = digits[i];
            } else if (digits[i] > secondLargest && digits[i] != largest) {
                secondLargest = digits[i];
            }
        }

        System.out.println("Largest digit       : " + largest);
        System.out.println("Second largest digit: " + secondLargest);
        scanner.close();
    }
}
Program 4 — Largest and Second Largest Digit (Dynamic Array)

java
import java.util.Scanner;

public class LargestDigitsDynamic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = Math.abs(scanner.nextLong());

        int maxDigit = 10;
        int[] digits = new int[maxDigit];
        int index = 0;

        while (number != 0) {
            if (index == maxDigit) {
                maxDigit += 10;
                int[] temp = new int[maxDigit];
                for (int i = 0; i < digits.length; i++) temp[i] = digits[i];
                digits = temp;
            }
            digits[index++] = (int)(number % 10);
            number /= 10;
        }

        int largest = 0, secondLargest = 0;
        for (int i = 0; i < index; i++) {
            if (digits[i] > largest) {
                secondLargest = largest;
                largest = digits[i];
            } else if (digits[i] > secondLargest && digits[i] != largest) {
                secondLargest = digits[i];
            }
        }

        System.out.println("Largest digit       : " + largest);
        System.out.println("Second largest digit: " + secondLargest);
        scanner.close();
    }
}
Program 5 — Reverse a Number Using Arrays

java
import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = Math.abs(scanner.nextInt());

        // Count digits
        int temp = number, count = 0;
        while (temp != 0) { temp /= 10; count++; }

        // Store digits
        int[] digits = new int[count];
        for (int i = 0; i < count; i++) {
            digits[i] = number % 10;
            number /= 10;
        }

        // Reverse into new array
        int[] reversed = new int[count];
        for (int i = 0; i < count; i++) {
            reversed[i] = digits[count - 1 - i];
        }

        System.out.print("Reversed number: ");
        for (int i = 0; i < count; i++) {
            System.out.print(reversed[i]);
        }
        System.out.println();
        scanner.close();
    }
}
Program 6 — BMI Calculator (Separate Arrays)

java
import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int n = scanner.nextInt();

        double[] weight       = new double[n];
        double[] height       = new double[n];
        double[] bmi          = new double[n];
        String[] weightStatus = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i + 1) + ":");
            System.out.print("  Weight (kg): ");
            weight[i] = scanner.nextDouble();
            System.out.print("  Height (m) : ");
            height[i] = scanner.nextDouble();
        }

        for (int i = 0; i < n; i++) {
            bmi[i] = weight[i] / (height[i] * height[i]);
            if      (bmi[i] < 18.5)              weightStatus[i] = "Underweight";
            else if (bmi[i] < 25.0)              weightStatus[i] = "Normal";
            else if (bmi[i] < 30.0)              weightStatus[i] = "Overweight";
            else                                 weightStatus[i] = "Obese";
        }

        System.out.printf("%n%-10s %-10s %-10s %-12s%n",
                "Height(m)", "Weight(kg)", "BMI", "Status");
        System.out.println("------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-10.2f %-10.2f %-10.2f %-12s%n",
                    height[i], weight[i], bmi[i], weightStatus[i]);
        }
        scanner.close();
    }
}
Program 7 — BMI Calculator (2D Array)

java
import java.util.Scanner;

public class BMI2DArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int n = scanner.nextInt();

        // Columns: 0=weight, 1=height, 2=BMI
        double[][] personData  = new double[n][3];
        String[]   weightStatus = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i + 1) + ":");
            do {
                System.out.print("  Weight (kg): ");
                personData[i][0] = scanner.nextDouble();
                if (personData[i][0] <= 0) System.out.println("  Enter a positive value.");
            } while (personData[i][0] <= 0);

            do {
                System.out.print("  Height (m) : ");
                personData[i][1] = scanner.nextDouble();
                if (personData[i][1] <= 0) System.out.println("  Enter a positive value.");
            } while (personData[i][1] <= 0);
        }

        for (int i = 0; i < n; i++) {
            personData[i][2] = personData[i][0] / (personData[i][1] * personData[i][1]);
            double b = personData[i][2];
            if      (b < 18.5) weightStatus[i] = "Underweight";
            else if (b < 25.0) weightStatus[i] = "Normal";
            else if (b < 30.0) weightStatus[i] = "Overweight";
            else               weightStatus[i] = "Obese";
        }

        System.out.printf("%n%-10s %-10s %-10s %-12s%n",
                "Height(m)", "Weight(kg)", "BMI", "Status");
        System.out.println("------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-10.2f %-10.2f %-10.2f %-12s%n",
                    personData[i][1], personData[i][0],
                    personData[i][2], weightStatus[i]);
        }
        scanner.close();
    }
}
Program 8 — Student Grades (Separate Arrays)

java
import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        double[] physics    = new double[n];
        double[] chemistry  = new double[n];
        double[] maths      = new double[n];
        double[] percentage = new double[n];
        String[] grade      = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");
            do {
                System.out.print("  Physics marks   : ");
                physics[i] = scanner.nextDouble();
                if (physics[i] < 0) System.out.println("  Enter positive marks.");
            } while (physics[i] < 0);
            do {
                System.out.print("  Chemistry marks : ");
                chemistry[i] = scanner.nextDouble();
                if (chemistry[i] < 0) System.out.println("  Enter positive marks.");
            } while (chemistry[i] < 0);
            do {
                System.out.print("  Maths marks     : ");
                maths[i] = scanner.nextDouble();
                if (maths[i] < 0) System.out.println("  Enter positive marks.");
            } while (maths[i] < 0);
        }

        for (int i = 0; i < n; i++) {
            percentage[i] = (physics[i] + chemistry[i] + maths[i]) / 3.0;
            if      (percentage[i] >= 90) grade[i] = "A+";
            else if (percentage[i] >= 80) grade[i] = "A";
            else if (percentage[i] >= 70) grade[i] = "B";
            else if (percentage[i] >= 60) grade[i] = "C";
            else if (percentage[i] >= 50) grade[i] = "D";
            else                          grade[i] = "F";
        }

        System.out.printf("%n%-10s %-10s %-10s %-10s %-12s %-6s%n",
                "Student", "Physics", "Chemistry", "Maths", "Percentage", "Grade");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-10d %-10.1f %-10.1f %-10.1f %-12.2f %-6s%n",
                    (i + 1), physics[i], chemistry[i], maths[i], percentage[i], grade[i]);
        }
        scanner.close();
    }
}
Program 9 — Student Grades (2D Array)

java
import java.util.Scanner;

public class StudentGrades2D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        // Columns: 0=Physics, 1=Chemistry, 2=Maths
        double[][] marks    = new double[n][3];
        double[]   percentage = new double[n];
        String[]   grade    = new String[n];
        String[]   subjects = {"Physics", "Chemistry", "Maths"};

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");
            for (int j = 0; j < 3; j++) {
                do {
                    System.out.print("  " + subjects[j] + " marks: ");
                    marks[i][j] = scanner.nextDouble();
                    if (marks[i][j] < 0) System.out.println("  Enter positive marks.");
                } while (marks[i][j] < 0);
            }
        }

        for (int i = 0; i < n; i++) {
            percentage[i] = (marks[i][0] + marks[i][1] + marks[i][2]) / 3.0;
            if      (percentage[i] >= 90) grade[i] = "A+";
            else if (percentage[i] >= 80) grade[i] = "A";
            else if (percentage[i] >= 70) grade[i] = "B";
            else if (percentage[i] >= 60) grade[i] = "C";
            else if (percentage[i] >= 50) grade[i] = "D";
            else                          grade[i] = "F";
        }

        System.out.printf("%n%-10s %-10s %-10s %-10s %-12s %-6s%n",
                "Student", "Physics", "Chemistry", "Maths", "Percentage", "Grade");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-10d %-10.1f %-10.1f %-10.1f %-12.2f %-6s%n",
                    (i + 1), marks[i][0], marks[i][1], marks[i][2],
                    percentage[i], grade[i]);
        }
        scanner.close();
    }
}
Program 10 — Digit Frequency Counter

java
import java.util.Scanner;

public class DigitFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = Math.abs(scanner.nextLong());

        // Count digits
        long temp = number;
        int count = 0;
        if (number == 0) count = 1;
        while (temp != 0) { temp /= 10; count++; }

        // Store digits
        int[] digits = new int[count];
        temp = number;
        for (int i = 0; i < count; i++) {
            digits[i] = (int)(temp % 10);
            temp /= 10;
        }

        // Frequency array (index 0-9 represents digit 0-9)
        int[] frequency = new int[10];
        for (int i = 0; i < count; i++) {
            frequency[digits[i]]++;
        }

        System.out.println("\nDigit Frequencies in " + number + ":");
        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0) {
                System.out.println("  Digit " + i + " appears " + frequency[i] + " time(s)");
            }
        }
        scanner.close();
    }
}

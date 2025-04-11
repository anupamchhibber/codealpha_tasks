
import java.util.*;

public class StudentGradeTracker {
    // Mapping letter grades to numerical values
    private static final Map<String, Integer> gradeMap = new HashMap<>() {{
        put("A", 90);
        put("B", 80);
        put("C", 70);
        put("D", 60);
        put("F", 50);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> grades = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter letter grade for student " + (i + 1) + " (A, B, C, D, F): ");
            String grade = scanner.nextLine().toUpperCase();
            while (!gradeMap.containsKey(grade)) {
                System.out.print("Invalid grade. Please enter A, B, C, D, or F: ");
                grade = scanner.nextLine().toUpperCase();
            }
            grades.add(grade);
        }

        if (!grades.isEmpty()) {
            int sum = 0;
            int highest = gradeMap.get(grades.get(0));
            int lowest = gradeMap.get(grades.get(0));

            for (String grade : grades) {
                int numeric = gradeMap.get(grade);
                sum += numeric;
                if (numeric > highest) highest = numeric;
                if (numeric < lowest) lowest = numeric;
            }

            double average = (double) sum / grades.size();

            System.out.println("\n--- Grade Summary ---");
            System.out.println("Average Grade Value: " + average);
            System.out.println("Highest Grade Value: " + highest);
            System.out.println("Lowest Grade Value: " + lowest);
        } else {
            System.out.println("No grades entered.");
        }

        scanner.close();
    }
}

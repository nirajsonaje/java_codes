import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of rows for the diamond
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        // Upper half of the diamond (including the middle row)
        for (int i = 1; i <= rows; i++) {
            // Print leading spaces
            for (int j = i; j < rows; j++) {
                System.out.print(" ");
            }

            // Print the stars for each row
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }

            System.out.println(); // Move to the next line
        }

        // Lower half of the diamond (excluding the middle row)
        for (int i = rows - 1; i >= 1; i--) {
            // Print leading spaces
            for (int j = rows; j > i; j--) {
                System.out.print(" ");
            }

            // Print the stars for each row
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }

            System.out.println(); // Move to the next line
        }

        scanner.close();
    }
}

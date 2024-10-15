import java.util.Scanner;

public class PrimeNumbers {

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Asking for user input
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        
        int count = 0;
        int num = 2; // Start checking from 2
        
        // Loop to find first n prime numbers
        while (count < n) {
            if (isPrime(num)) {
                System.out.print(num + " ");
                count++;
            }
            num++;
        }
        
        scanner.close();
    }
}

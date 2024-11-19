public class MatrixIterationForEach {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix elements using for-each:");
        for (int[] row : matrix) {                           // Outer loop: Rows
            for (int element : row) {                        // Inner loop: Elements in the row
                System.out.print(element + " ");
            }
            System.out.println();                            // New line after each row
        }
    }
}

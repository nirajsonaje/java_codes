public class MatrixIteration {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix elements (row by row):");
        for (int i = 0; i < matrix.length; i++) {             // Outer loop: Rows
            for (int j = 0; j < matrix[i].length; j++) {      // Inner loop: Columns
                System.out.print(matrix[i][j] + " ");        // Access each element
            }
            System.out.println();                            // New line after each row
        }
    }
}

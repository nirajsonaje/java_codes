public class MatrixIterationColumn {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix elements (column by column):");
        for (int j = 0; j < matrix[0].length; j++) {          // Outer loop: Columns
            for (int i = 0; i < matrix.length; i++) {         // Inner loop: Rows
                System.out.print(matrix[i][j] + " ");        // Access each element
            }
            System.out.println();                            // New line after each column
        }
    }
}

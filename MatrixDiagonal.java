public class MatrixDiagonal {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Diagonal elements:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][i] + " ");  // Primary diagonal (top-left to bottom-right)
        }
        System.out.println();

        System.out.println("Anti-diagonal elements:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][matrix.length - 1 - i] + " "); // Anti-diagonal (top-right to bottom-left)
        }
    }
}

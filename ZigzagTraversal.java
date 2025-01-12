import java.util.ArrayList;
import java.util.List;

public class ZigzagTraversal {
    public static List<Integer> zigzagTraversalWithSkips(int[][] grid) {
        List<Integer> result = new ArrayList<>();
        boolean leftToRight = true; // Direction flag

        for (int i = 0; i < grid.length; i++) {
            if (leftToRight) {
                // Traverse from left to right
                for (int j = 0; j < grid[i].length; j++) {
                    if ((i + j) % 2 == 0) { // Skip alternate cells
                        result.add(grid[i][j]);
                    }
                }
            } else {
                // Traverse from right to left
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    if ((i + j) % 2 == 0) { // Skip alternate cells
                        result.add(grid[i][j]);
                    }
                }
            }
            // Switch direction
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2},
            {3, 4}
        };

        List<Integer> result = zigzagTraversalWithSkips(grid);
        System.out.println(result); // Output: [1, 4]
    }
}

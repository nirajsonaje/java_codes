class Solution {
    public String convert(String s, int numRows) {
        // If there's only one row or numRows is greater than or equal to the length of
        // the string,
        // the zigzag pattern is the same as the original string, so return the original
        // string.
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        // Create an array of StringBuilders to represent each row of the zigzag
        // pattern.
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int direction = 1; // 1 for down, -1 for up
        int currentRow = 0;

        // Iterate through each character in the string.
        for (char c : s.toCharArray()) {
            // Append the character to the current row.
            rows[currentRow].append(c);

            // Update the direction based on whether the current row is at the top or bottom
            // of the zigzag pattern.
            if (currentRow == 0) {
                direction = 1; // Change direction to down when reaching the top row.
            } else if (currentRow == numRows - 1) {
                direction = -1; // Change direction to up when reaching the bottom row.
            }

            // Move to the next row based on the current direction.
            currentRow += direction;
        }

        // Concatenate all the rows to get the final result.
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}

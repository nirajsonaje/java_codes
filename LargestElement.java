public class LargestElement {
    public static void main(String[] args) {
        int[] arr = {10, 20, 5, 30, 15};
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Largest element is: " + max);
    }
}
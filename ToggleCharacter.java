import java.util.Scanner;
@SuppressWarnings("all")
public class ToggleCharacter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char input = scanner.next().charAt(0);

        if (Character.isLowerCase(input)) {
            System.out.println(Character.toUpperCase(input));
        } else if (Character.isUpperCase(input)) {
            System.out.println(Character.toLowerCase(input));
        }
    }
}

import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        Interpreter interp = new Interpreter();

        Scanner scan = new Scanner(System.in);
        while (true) {
            // Clear screen.
            System.out.print("\033[H\033[2J");
            // Print out each register.
            for (int i = stackSize - 1; i >= stack.depth(); i--) {
                System.out.printf("%c\n", new char[] { 'Y', 'Z', 'T' }[i]);
            }
            for (int i = stack.depth() - 1; i >= 0; i--) {
                System.out.printf("%c: %.2f\n", new char[] { 'Y', 'Z', 'T' }[i], stack.peek(i));
            }
            // Get and execute command.
            System.out.print("> ");
            cmd(scan.nextLine());
        }
        // There is currently no way to end the program.
        // scan.close();
    }
}
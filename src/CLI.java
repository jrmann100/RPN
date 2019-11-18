import java.util.Scanner;

public class CLI {
	
    public static void main(String[] args) {
        Interpreter interp = new Interpreter();

        Scanner scan = new Scanner(System.in);
        while (true) {
            // Clear screen.
            System.out.print("\033[H\033[2J");
            // Print out each register.
            System.out.print(interp + "> ");
            // Get and execute command.
            interp.cmd(scan.nextLine());
        }
        // There is currently no way to end the program.
        // scan.close();
    }
}
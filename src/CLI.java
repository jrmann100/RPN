import java.util.Scanner;

// Command line interface.
// A basic interface for the Interpreter.
public class CLI {
	
    public static void main(String[] args) {
        Interpreter interp = new Interpreter(5);

        Scanner scan = new Scanner(System.in);
        while (true) {
            // Clear screen. This doesn't work in Eclipse.
            System.out.print("\033[H\033[2J");
            System.out.println();
            // Print out each register.
            System.out.print(interp + "> ");
            // Get and execute command.
            try {
				interp.cmd(scan.nextLine());
			} catch (RPNStackOverflowException | RPNStackUnderflowException e) {
	        	System.out.printf("Stack exception: %s\nReturn to continue >", e.getMessage());
	        	scan.nextLine();
			}
        }
        // There is currently no way to end the program.
        // scan.close();
    }
}
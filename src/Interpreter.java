import java.util.Scanner;

public class Interpreter {
    // Last command. Allows for blank command to execute last command.
    private static String lastCmd = "";
    // 4-register stack.
    private static Stack stack;
    private static final int stackSize = 4;
    public Interpreter(Stack givenStack){
        stack = givenStack;
    }
    public Interpreter(){
        stack = new Stack(4);
    }

    public static void main(String[] args) {
        stack = new Stack(4);
        Scanner scan = new Scanner(System.in);
        while (true) {
            // Clear screen.
            System.out.print("\033[H\033[2J");
            // Print out each register.
            for (int i = stack.depth() - 1;i>=0;i--) {
                System.out.printf("%c: %.2f\n", new char[] { 'X', 'Y', 'Z', 'T' }[i], stack.peek(i));
            }
            // Get and execute command.
            System.out.print("> ");
            cmd(scan.nextLine());
        }
        // There is currently no way to end the program.
        // scan.close();
    }

    // Check whether a command is a number.
    public static boolean isNumber(String cmd) {
        try {
            Double.parseDouble(cmd);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Execute command.
    public static void cmd(String cmd) {
        String[] subcmds = cmd.split(" ");
        if (subcmds.length > 1){
            for (String subcmd : subcmds){
                cmd(subcmd);
            }
            return;
        }
        if (cmd.length() == 0) {
            cmd = lastCmd;
        }
        if (isNumber(cmd)) {
            stack.push(Double.parseDouble(cmd));
        } else if (cmd.equals("+")) {
            stack.push(stack.pop() + stack.pop());
        } else if (cmd.equals("-")) {
            stack.push(stack.pop() - stack.pop());
        } else if (cmd.equals("*")) {
            stack.push(stack.pop() * stack.pop());
        } else if (cmd.equals("/")) {
            stack.push(stack.pop() / stack.pop());
        }
        lastCmd = cmd;
    }
}

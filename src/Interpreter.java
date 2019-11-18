import java.util.Scanner;

public class Interpreter {
    // Last command. Allows for blank command to execute last command.
    private static String lastCmd = "";
    // 4-register stack.
    private static double[] stack = { 0, 0, 0, 0 };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            // Clear screen.
            //System.out.print("\033[H\033[2J");
            // Print out each register.
            for (int i = stack.length - 1; i >= 0; i--) {
                System.out.printf("%c: %.2f\n", new char[] { 'X', 'Y', 'Z', 'T' }[i], stack[i]);
            }
            // Get and execute command.
            System.out.print("> ");
            cmd(scan.nextLine());
        }
        // There is currently no way to end the program.
        // scan.close();
    }

    // Shift stack up. Preserve X register.
    public static void sup() {
        for (int i = stack.length - 1; i >= 1; i--) {
            stack[i] = stack[i - 1];
        }
    }

    // Shift stack down. Preserve X register.
    public static void sdown() {
        for (int i = 1; i < stack.length - 1; i++) {
            stack[i] = stack[i + 1];
        }
        stack[stack.length - 1] = 0;
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
            sup();
            stack[0] = Double.parseDouble(cmd);
        } else if (cmd.equals("+")) {
            stack[0] = stack[1] + stack[0];
            sdown();
        } else if (cmd.equals("-")) {
            stack[0] = stack[1] - stack[0];
            sdown();
        } else if (cmd.equals("*")) {
            stack[0] = stack[1] * stack[0];
            sdown();
        } else if (cmd.equals("/")) {
            stack[0] = stack[1] / stack[0];
            sdown();
        }
        lastCmd = cmd;
    }
}

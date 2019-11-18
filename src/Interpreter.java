public class Interpreter {
    // Last command. Allows for blank command to execute last command.
    private static String lastCmd = "";
    // 4-register stack.
    private static Stack stack;
    private static int stackSize;

    public Interpreter(Stack givenStack) {
        stack = givenStack;
        stackSize = givenStack.getSize();
    }

    public Interpreter() {
        stack = new Stack(3);
        stackSize = 3;
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
        if (subcmds.length > 1) {
            for (String subcmd : subcmds) {
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
            stack.push(1 / stack.pop() * stack.pop());
        }
        lastCmd = cmd;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = stackSize - 1; i >= stack.depth(); i--) {
            output += String.format("%c\n", new char[] { 'Y', 'Z', 'T' }[i]);
        }
        for (int i = stack.depth() - 1; i >= 0; i--) {
            output += String.format("%c: %.2f\n", new char[] { 'Y', 'Z', 'T' }[i], stack.peek(i));
        }
        return output;
    }
}

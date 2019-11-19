public class Interpreter {
    // Last command. Allows for blank command to execute last command.
    private static String lastCmd = "";
    // 4-register stack.
    private Stack stack;
    public final int stackSize;

    public Interpreter(int givenStackSize) {
        stack = new Stack(givenStackSize);
        stackSize = givenStackSize;
    }

    public Interpreter() {
        stack = new Stack(3);
        stackSize = 3;
    }

    public Stack getStack() {
   	 return stack;
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
    public void cmd(String cmd) {
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
        } else if (cmd.startsWith("pick")) {
            stack.push(stack.pick(Integer.parseInt(cmd.substring(4))));
        }
        lastCmd = cmd;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = stackSize - 1; i >= stack.depth(); i--) {
            output += String.format("%d%n", i);
        }
        for (int i = 0; i < stack.depth(); i++) {
            output += String.format("%d: %.2f%n", stack.depth() - i, stack.peek(i));
        }
        return output;
    }
}

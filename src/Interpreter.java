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
	public void cmd(String cmd) throws IllegalArgumentException, RPNStackOverflowException, RPNStackUnderflowException {
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
		} else if (cmd.equals("^")) {
			double power = stack.pop();
			double base = stack.pop();
			stack.push(Math.pow(base, power));
		} else if (cmd.equals("pi")) {
			stack.push(Math.PI);
		} else if (cmd.equals("e")) {
			stack.push(Math.E);
		} else if (cmd.equals("log")) {
			stack.push(Math.log10(stack.pop()));
		} else if (cmd.equals("ln")) {
			stack.push(Math.log(stack.pop()));
		} else if (cmd.equals("logbase")) {
			// Or you could just log log /
			double base = stack.pop();
			double argument = stack.pop();
			stack.push(Math.log(argument) / Math.log(base));
		}

		else if (cmd.startsWith("pick")) {
			if (cmd.substring(4).length() == 0) {
				throw new IllegalArgumentException("pickX expects an integer argument X.");
			}
			stack.pick(Integer.parseInt(cmd.substring(4)));
		} else if (cmd.startsWith("rotn")) {
			if (cmd.substring(4).length() == 0) {
				throw new IllegalArgumentException("rotnX expects an integer argument X.");
			}
			stack.rotn(Integer.parseInt(cmd.substring(4)));
		} else if (cmd.equals("rot")) {
			stack.rot();
		} else if (cmd.equals("swap")) {
			stack.swap();
		} else if (cmd.equals("roll")) {
			stack.roll();
		}

		lastCmd = cmd;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = stack.depth(); i < stackSize; i++) {
			output += String.format("%n");
		}
		for (int i = 0; i < stack.depth(); i++) {
			output += String.format("%d: %.2f%n", stack.depth() - i - 1, stack.peek(i));
		}
		return output;
	}
}

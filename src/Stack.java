import java.util.Arrays;

public class Stack {
	private double[] stack;
	private int sp;

	public Stack(int givenSize) {
		stack = new double[givenSize];
		Arrays.fill(stack, 0);
		sp = 0;
	}

	public void push(double number) throws RPNStackOverflowException {
		if (sp == stack.length) {
			throw new RPNStackOverflowException("Tried to push() to a full stack.");
		}
		stack[sp++] = number;
	}

	public double pop() throws RPNStackUnderflowException {
		if (sp == 0) {
			throw new RPNStackUnderflowException("Tried to pop() an empty stack.");
		}
		return stack[--sp];
	}

	public double peek(int n) {
		return stack[n];
	}

	public int depth() {
		return sp;
	}

	public double pick(int n) throws RPNStackUnderflowException {
		if (sp == 0) {
			throw new RPNStackUnderflowException("Tried to pick() an empty stack.");
		}
		sp--;
		double popped = stack[sp - n];
		for (int i = sp - n; i < sp; i++) {
			stack[i] = stack[i + 1];
		}
		return popped;
	}

	public void rotn(int n) throws RPNStackOverflowException, RPNStackUnderflowException {
		push(pick(n));
	}

	public void rot() throws RPNStackOverflowException, RPNStackUnderflowException {
		double s0 = pop();
		double s1 = pop();
		double s2 = pop();
		push(s1);
		push(s0);
		push(s2);
	}
	
	public void roll() throws RPNStackOverflowException, RPNStackUnderflowException {
		push(pick(sp - 1));
	}

	public void swap() throws RPNStackOverflowException, RPNStackUnderflowException {
		double s0 = pop();
		double s1 = pop();
		push(s0);
		push(s1);
	}
}
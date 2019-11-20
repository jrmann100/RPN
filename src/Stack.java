import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private double[] stack;
    private int sp;
    
    public Stack(int givenSize){
        stack = new double[givenSize];
        Arrays.fill(stack, 0);
        sp = 0;
    }

    public void push(double number) throws RPNStackOverflowException {
    	if (sp + 1 == stack.length) {
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

    public double peek(int n){
        return stack[n];
    }
    
    public int depth(){
        return sp;
    }

    public double pick(int n){
    	double popped = stack[n];
    	sp--;
    	for (int i = n; i < sp; i++) {
    		stack[i] = stack[i + 1];
    	}
    	return popped;
    }
}
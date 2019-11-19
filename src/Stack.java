import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Arrays;

public class Stack {
    private double[] stack;
    private int sp;
    
    public Stack(int givenSize){
        stack = new double[givenSize];
        Arrays.fill(stack, 0);
        sp = 0;
    }

    public void push(double number) throws BufferUnderflowException, BufferOverflowException {
        if (sp == stack.length) { // Is this allowed here? Or do I do this in the Interpreter? This must be wrong.
      	  pick(0);
        }
        stack[sp++] = number;
    }

    public double pop() throws BufferUnderflowException, BufferOverflowException {
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
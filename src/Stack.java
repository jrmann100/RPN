import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Arrays;

public class Stack {
    private double[] stack;
    private int sp;

    public Stack(int size){
        stack = new double[size];
        Arrays.fill(stack, 0);
        sp = 0; //?
    }
    public Stack() {
        stack = new double[] {0,0,0,0};
        sp = 0;
    }
    public void push(double number) throws BufferUnderflowException, BufferOverflowException{
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
}
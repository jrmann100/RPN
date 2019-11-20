
public class RPNStackUnderflowException extends Exception {
	private static final long serialVersionUID = 1L;

	public RPNStackUnderflowException(String msg) {
		super(msg);
	}

	public RPNStackUnderflowException() {
		super();
	}
}

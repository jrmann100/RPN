
public class RPNStackOverflowException extends Exception {
	private static final long serialVersionUID = 1L;

	public RPNStackOverflowException(String msg) {
		super(msg);
	}

	public RPNStackOverflowException() {
		super();
	}
}

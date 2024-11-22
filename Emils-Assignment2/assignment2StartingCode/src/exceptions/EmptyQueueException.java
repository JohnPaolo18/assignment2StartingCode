package exceptions;

public class EmptyQueueException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyQueueException() {
        super("The queue is empty.");
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
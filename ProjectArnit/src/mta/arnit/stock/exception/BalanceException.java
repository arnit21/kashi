package mta.arnit.stock.exception;

/**
 * This exception is thrown when user tries to create and existing account.
 */
public class BalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceException() {
		super("The portfolio balance becomes negative");
	}
	
	public BalanceException(String message) {
		super(message);
	}
}

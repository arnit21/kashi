package mta.arnit.stock.exception;

/**
 * This exception is thrown when user had reached maximum portfolio size.
 */
public class PortfolioFullException extends Exception {

	private static final long serialVersionUID = 1L;

	public PortfolioFullException() {
		super("You had reached maximum portfolio size!");
	}
}
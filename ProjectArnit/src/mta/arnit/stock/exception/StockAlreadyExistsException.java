package mta.arnit.stock.exception;

/**
 * This exception is thrown when stock already exist in portfolio.
 */
public class StockAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) {
		super("Stock " + symbol + " already exists in portfolio!");
	}
}
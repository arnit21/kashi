package mta.arnit.stock.exception;

/**
 * This exception is thrown when stock not exist in portfolio.
 */
public class StockNotExistException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockNotExistException(String symbol) {
		super("Stock " + symbol + " doesn't exist");
	}

}

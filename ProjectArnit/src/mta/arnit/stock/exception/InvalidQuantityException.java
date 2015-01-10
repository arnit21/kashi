package mta.arnit.stock.exception;

/**
 * This exception is thrown when the quantity is invalid.
 */
public class InvalidQuantityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidQuantityException(String error){
		super(error);
	}

}

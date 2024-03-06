package fr.eni.projetencheres.dal;

public class DataException extends Exception {
	private static final long serialVersionUID = 1L;

	public static final String DAL_SQLEXCEPTION = "Erreur lors de %s : %s";
	
	public DataException() {
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(Throwable cause) {
		super(cause);
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataException(String message, String exceptionMessage) {
		super(String.format(DAL_SQLEXCEPTION, message, exceptionMessage));
	}
	

	public DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package fr.eni.projetencheres.bll;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final String DAL_INSERT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'insertion de l'article dans la base de donnée";
	public static final String DAL_UPDATE_ARTICLE_SQLEXCEPTION = "Erreur lors de la mise à jour de l'article dans la base de donnée";
	public static final String DAL_SELECT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'obtention d'articles dans la base de donnée";
	public static final String DAL_DELETE_ARTICLE_SQLEXCEPTION = "Erreur lors de la supprésion de l'article dans la base de donnée";
	
	public static final String DAL_INSERT_BID_SQLEXCEPTION = "Erreur lors de l'insertion de l'enchère dans la base de donnée";
	public static final String DAL_SELECT_BID_SQLEXCEPTION = "Erreur lors de l'obtention d'enchères dans la base de donnée";
	
	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

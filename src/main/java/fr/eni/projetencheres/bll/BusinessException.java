package fr.eni.projetencheres.bll;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final String DAL_INSERT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'insertion de l'article dans la base de donnée";
	public static final String DAL_UPDATE_ARTICLE_SQLEXCEPTION = "Erreur lors de la mise à jour de l'article dans la base de donnée";
	public static final String DAL_SELECT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'obtention d'articles dans la base de donnée";
	public static final String DAL_DELETE_ARTICLE_SQLEXCEPTION = "Erreur lors de la suppression de l'article dans la base de donnée";
	
	public static final String DAL_INSERT_BID_SQLEXCEPTION = "Erreur lors de l'insertion de l'enchère dans la base de donnée";
	public static final String DAL_SELECT_BID_SQLEXCEPTION = "Erreur lors de l'obtention d'enchères dans la base de donnée";
  
	public static final String DAL_INSERT_USER_SQLEXCEPTION = "Something went wrong..." ;
  
	
	public static final String BLL_LOGIN_USER_EXCEPTION = "Erreur lors de votre connexion ; vérifiez votre pseudo ou mot de passe";
	public static final String BLL_PWD_USER_EXCEPTION = "Il y a une erreur sur la vérification du mot de passe : veuillez essayer à nouveau";
  public static final String BLL_ARTICLE_NULL = "Aucun articles n'a pu être trouvé";
	public static final String BLL_ARTICLES_NULL = "Aucun articles n'ont pu être trouvés";

	public static final String BLL_BID_AMOUNT_NOT_ENOUGH = "Le montant de l'enchère doit être supérieur ou égale à un";
	public static final String BLL_BID_ENDED = "L'enchère est terminée, vous ne pouvez plus enchérir sur celle-ci";
	public static final String BLL_ERROR_SQLEXCEPTION_LOGIN = "Il y a un probleme avec votre statut -en ligne- !";

	
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

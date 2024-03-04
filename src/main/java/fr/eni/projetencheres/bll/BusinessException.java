package fr.eni.projetencheres.bll;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public static final String DAL_INSERT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'insertion de l'article dans la base de données";
	public static final String DAL_UPDATE_ARTICLE_SQLEXCEPTION = "Erreur lors de la mise à jour de l'article dans la base de données";
	public static final String DAL_SELECT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'obtention d'articles dans la base de données";
	public static final String DAL_DELETE_ARTICLE_SQLEXCEPTION = "Erreur lors de la suppréssion de l'article dans la base de données";

	public static final String DAL_INSERT_BID_SQLEXCEPTION = "Erreur lors de l'insertion de l'enchère dans la base de données";
	public static final String DAL_SELECT_BID_SQLEXCEPTION = "Erreur lors de l'obtention d'enchères dans la base de données";

	public static final String DAL_INSERT_USER_SQLEXCEPTION = "Erreur lors de la création du compte";

	public static final String BLL_LOGIN_USER_EXCEPTION = "Erreur lors de votre connexion ; vérifiez votre pseudo ou mot de passe";
	public static final String BLL_PWD_USER_EXCEPTION = "Il y a une erreur sur la vérification du mot de passe : veuillez essayer à nouveau";

	public static final String BLL_ADD_ARTICLE_START_DATE_AFTER_END_DATE_ERROR = "La date du début de l'enchère ne peut pas être après la fin de celle-ci";
	public static final String BLL_GET_ARTICLE_NULL = "L'article n'a pu être trouvé";
	public static final String BLL_GET_ALL_ARTICLES_NULL = "Aucuns articles n'ont pu être trouvés";

	public static final String BLL_FIELD_EMPTY_ERROR = "Le %s doit être renseigné";
	public static final String BLL_FIELD_BOUNDS_ERROR = "Le %s doit être compris entre %d et %d caractères";
	public static final String BLL_MONEY_NOT_ENOUGH_ERROR = "Le %s doit être supérieur ou égal à %d";
	
	public static final String BLL_EMPTY_FIELDS_ERROR = "Tous les champs doivent être renseignés";
	
	public static final String BLL_ADD_BID_ENDED_ERROR = "L'enchère est terminée, vous ne pouvez plus enchérir sur celle-ci";

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

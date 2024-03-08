package fr.eni.projetencheres.bll;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public static final String BLL_LOGIN_USER_EXCEPTION = "Erreur lors de votre connexion ; vérifiez votre pseudo ou mot de passe";
	public static final String BLL_USER_MAILDOESNOTEXIST = "Si tout va bien, vous devriez recevoir un e-mail pour réinitialiser votre mot de passe";
	public static final String BLL_ADD_ARTICLE_START_DATE_AFTER_END_DATE_ERROR = "La date du début de l'enchère ne peut pas être après la fin de celle-ci";
	public static final String BLL_GET_ARTICLE_NULL = "L'article n'a pas pu être trouvé";
	public static final String BLL_GET_ALL_ARTICLES_NULL = "Aucun article n'ont pu être trouvés";

	public static final String BLL_FIELD_EMPTY_ERROR = "Le champ %s doit être renseigné";
	public static final String BLL_FIELD_BOUNDS_ERROR = "Le champ %s doit être compris entre %d et %d caractères";
	public static final String BLL_MONEY_NOT_ENOUGH_ERROR = "Le %s doit être supérieur ou égal à %d";
	
	public static final String BLL_EMPTY_FIELDS_ERROR = "Tous les champs doivent être renseignés";

	public static final String BLL_ERROR_SQLEXCEPTION_LOGIN = "Erreur de saisie pseudo et/ou mot de passe";
	public static final String BLL_PWD_USER_EXCEPTION = "Le mot de passe ne correspond pas";

	public static final String BLL_ADD_BID_ENDED_ERROR = "L'enchère est terminée, vous ne pouvez plus enchérir sur celle-ci";
	public static final String BLL_PASSWORD_NOT_VALID = "Le mot de passe doit contenir au moins 1 majuscule, 1 minuscule, 1 chiffre";

	public static final String BLL_EMAIL_NOT_VALID = "L'email n'est pas valide";

	public static final String BLL_FIELDS_INVALID_VALUES_ERROR = "Les informations entrées ne sont pas valides, veuillez les corriger";
	
	public static final String BLL_INSERT_CHEAT = "Anti-Cheat system : arrêtez de toucher le DevTools, merci :-)";
	
	public static final String BLL_IMAGE_SAVE_FAILED = "Une erreur s'est produite, l'image de l'article n'a pas pu être sauvegardée";
	
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

package fr.eni.projetencheres.bll;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final String DAL_INSERT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'insertion de l'article dans la base de données";
	public static final String DAL_UPDATE_ARTICLE_SQLEXCEPTION = "Erreur lors de la mise à jour de l'article dans la base de données";
	public static final String DAL_SELECT_ARTICLE_SQLEXCEPTION = "Erreur lors de l'obtention d'articles dans la base de données";
	public static final String DAL_DELETE_ARTICLE_SQLEXCEPTION = "Erreur lors de la suppréssion de l'article dans la base de données";
	
	public static final String DAL_INSERT_BID_SQLEXCEPTION = "Erreur lors de l'insertion de l'enchère dans la base de données";
	public static final String DAL_SELECT_BID_SQLEXCEPTION = "Erreur lors de l'obtention d'enchères dans la base de données";
  
	public static final String DAL_INSERT_USER_SQLEXCEPTION = "Erreur lors de la création du compte" ;
	public static final String DAL_SELECT_USER_EXCEPTION = "Erreur lors de la récupération de l'utilisateur";
	public static final String DAL_USER_NOT_FOUND = "Utilisateur non trouvé";
  
	
	public static final String BLL_LOGIN_USER_EXCEPTION = "Erreur lors de votre connexion ; vérifiez votre pseudo ou mot de passe";
	public static final String BLL_ERROR_SQLEXCEPTION_LOGIN = "Erreur de saisie pseudo et/ou mot de passe";
	public static final String BLL_PWD_USER_EXCEPTION = "Le mot de passe ne correspond pas";
	public static final String BLL_ARTICLE_NULL = "Aucun article n'a pu être trouvé";
	public static final String BLL_ARTICLES_NULL = "Aucun article n'a pu être trouvé";

	public static final String BLL_BID_AMOUNT_NOT_ENOUGH = "Le montant de l'enchère doit être supérieur ou égale à un";
	public static final String BLL_BID_ENDED = "L'enchère est terminée, vous ne pouvez plus enchérir sur celle-ci";

	
	public static final String BLL_USERNAME_EMPTY = "Le pseudo ne peut pas être vide";
	public static final String BLL_USERNAME_TOO_LONG = "Le pseudo ne peut pas dépasser 30 caractères";
	public static final String BLL_PASSWORD_EMPTY = "Le mot de passe ne peut pas être vide";
	public static final String BLL_PASSWORD_TOO_LONG = "Le mot de passe ne peut pas dépasser 30 caractères";
	public static final String BLL_PASSWORD_TOO_SHORT = "Le mot de passe doit contenir au moins 8 caractères";
	public static final String BLL_PASSWORD_NOT_VALID = "Le mot de passe doit contenir au moins 1 majuscule, 1 minuscule, 1 chiffre";
	public static final String BLL_FIRSTNAME_EMPTY = "Le prénom ne peut pas être vide";
	public static final String BLL_FIRSTNAME_TOO_LONG = "Le prénom ne peut pas dépasser 30 caractères";
	public static final String BLL_LASTNAME_EMPTY = "Le nom ne peut pas être vide";
	public static final String BLL_LASTNAME_TOO_LONG = "Le nom ne peut pas dépasser 30 caractères";
	public static final String BLL_EMAIL_EMPTY = "L'email ne peut pas être vide";
	public static final String BLL_EMAIL_TOO_LONG = "L'email ne peut pas dépasser 50 caractères";
	public static final String BLL_EMAIL_NOT_VALID = "L'email n'est pas valide";
	public static final String BLL_PHONE_EMPTY = "Le téléphone ne peut pas être vide";
	public static final String BLL_PHONE_TOO_LONG = "Le téléphone ne peut pas dépasser 15 caractères";
	public static final String BLL_STREET_EMPTY = "La rue ne peut pas être vide";
	public static final String BLL_STREET_TOO_LONG = "La rue ne peut pas dépasser 50 caractères";
	public static final String BLL_CITY_EMPTY = "La ville ne peut pas être vide";
	public static final String BLL_CITY_TOO_LONG = "La ville ne peut pas dépasser 30 caractères";
	public static final String BLL_ZIPCODE_EMPTY = "Le code postal ne peut pas être vide";
	public static final String BLL_ZIPCODE_TOO_LONG = "Le code postal ne peut pas dépasser 10 caractères";
	
	
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

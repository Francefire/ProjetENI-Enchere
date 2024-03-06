package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UserDAO;

public class UserManager {
	// on souhaite créer une unique instance du UserManager, de sorte à ce que ce
	// soit toujours le même qui soit utilisé
	private static UserDAO instance = null;

	// SINGLETON du UserDAO
	public static UserDAO getInstance() {
		if (instance == null) { // On s'assure qu'il n'y a pas déjà d'instance déjà crée
			instance = DAOFactory.getUserDAO(); // dans ce cas, on créé une instance de l'UserManager.
		}
		return instance;
	}

	// INSCRIPTION : création d'une méthode qui se sert de la DAO factory pour créer
	// un nouvel user.
	public static void createUser(User u, String checkPassword) throws BusinessException {
		UserManager.checkUserInfo(u);
		comparePwd(u.getPassword(), checkPassword);
		UserManager.getInstance().insert(u);
	}

	// INSCRIPTION : vérification du contenu de la saisie du pseudo
	public static void check_username(String username) throws BusinessException {
		if (username == null || username.isEmpty()) {
			throw new BusinessException(BusinessException.DAL_INSERT_USER_SQLEXCEPTION);
		}
	}

	// INSCRIPTION : Création d'une méthode pour comparer les saisies mot de passe
	public static void comparePwd(String password, String checkPassword) throws BusinessException {
		System.out.println(password + " " + checkPassword);
		if (!password.trim().equals(checkPassword.trim())) {
			throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
		}
	}

	// Creation d'une méthode qui verifie le mot de passe de l'utilisateur
	public static boolean checkPwd(User u, String password) throws BusinessException {
		if (!(u.getPassword().trim().equals(password.trim()))) {
			throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
		}
		return true;
	}

	public static User login(String userName, String password) throws BusinessException {
		int userId = UserManager.getInstance().check(userName, password);
		
		if (userId < 1) {
			throw new BusinessException(BusinessException.BLL_ERROR_SQLEXCEPTION_LOGIN);
		}
		
		User user = UserManager.getInstance().selectById(userId);
		
		return user;
	}

	// Creation d'une méthode pour recuperer un utilisateur
	public static User getUserById(int userId) throws BusinessException {
		try {
			return UserManager.getInstance().selectById(userId);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	public static void getAllUsers() throws BusinessException {
		UserManager.getInstance().selectAllUsers();
	}

	public static void editUser(User u) {
		UserManager.getInstance().update(u);
	}
	
	public static void addCreditsToUser(double amount, int userId) throws BusinessException {
		Utils.verifyMoneyField("montant", amount, 1);
		
		UserManager.getInstance().updateCreditsForUser(amount, userId);
	}

	public static void deleteUser(int userId) {
		UserManager.getInstance().delete(userId);
	}

	// Creation d'une methode permettant de verifier que les informations sont
	// conforme aux contraintes
	public static void checkUserInfo(User u) throws BusinessException {
		Utils.verifyStringField("username", u.getUsername(), 0, 30);
		Utils.verifyStringField("password", u.getPassword(), 8, 30);
		Utils.verifyStringField("firstName", u.getFirstName(), 0, 30);
		Utils.verifyStringField("lastName", u.getLastName(), 0, 30);
		Utils.verifyStringField("email", u.getEmail(), 0, 50);
		Utils.verifyStringField("phoneNumber", u.getPhoneNumber(), 0, 15);
		Utils.verifyStringField("street", u.getStreet(), 0, 50);
		Utils.verifyStringField("city", u.getCity(), 0, 30);
		Utils.verifyStringField("zipCode", u.getZipCode(), 0, 10);

		if (!u.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new BusinessException(BusinessException.BLL_PASSWORD_NOT_VALID);
		}
		if (!u.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")) {
			throw new BusinessException(BusinessException.BLL_EMAIL_NOT_VALID);
		}

	}

	public static void checkUserInfo(User u, boolean checkPassword) throws BusinessException {
		Utils.verifyStringField("username", u.getUsername(), 0, 30);
		if (checkPassword) {
			Utils.verifyStringField("password", u.getPassword(), 8, 30);
			if (!u.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
				throw new BusinessException(BusinessException.BLL_PASSWORD_NOT_VALID);
			}
		}
		Utils.verifyStringField("firstName", u.getFirstName(), 0, 30);
		Utils.verifyStringField("lastName", u.getLastName(), 0, 30);
		Utils.verifyStringField("email", u.getEmail(), 0, 50);
		Utils.verifyStringField("phoneNumber", u.getPhoneNumber(), 0, 15);
		Utils.verifyStringField("street", u.getStreet(), 0, 50);
		Utils.verifyStringField("city", u.getCity(), 0, 30);
		Utils.verifyStringField("zipCode", u.getZipCode(), 0, 10);

		if (!u.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")) {
			throw new BusinessException(BusinessException.BLL_EMAIL_NOT_VALID);
		}

	}
}

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
		if (!password.equals(checkPassword)) {
			throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
		}
	}

	public static User login(String userName, String password) throws BusinessException {
//			String pass = User.hashPwd(password);
//			User u = UserDAO.check(userName, pass);
		int u_id = UserManager.getInstance().check(userName, password);
		if (u_id < 1) {
			throw new BusinessException(BusinessException.BLL_ERROR_SQLEXCEPTION_LOGIN);
		}
		User user = UserManager.getInstance().selectById(u_id);
		return user;
	}

	// Creation d'une méthode pour recuperer un utilisateur
	public static User getUserById(int userId) {
		return UserManager.getInstance().selectById(userId);
	}

	public static void editUser(User u) {
		UserManager.getInstance().update(u);
	}

	public static void deleteUser(int userId) {
		UserManager.getInstance().delete(userId);
	}

	// Creation d'une methode permettant de verifier que les informations sont
	// conforme aux contraintes
	public static void checkUser(User u) throws BusinessException {
        if (u.getUsername() == null || u.getUsername().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_USERNAME_EMPTY);
        }
        if (u.getUsername().length() > 30) {
            throw new BusinessException(BusinessException.BLL_USERNAME_TOO_LONG);
        }
        
        if (u.getPassword() == null || u.getPassword().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_PASSWORD_EMPTY);
        }
		if (u.getPassword().length() < 8) {
			throw new BusinessException(BusinessException.BLL_PASSWORD_TOO_SHORT);
		}
		if (!u.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new BusinessException(BusinessException.BLL_PASSWORD_NOT_VALID);
		}
        if (u.getPassword().length() > 30) {
            throw new BusinessException(BusinessException.BLL_PASSWORD_TOO_LONG);
        }
        if (u.getFirstName() == null || u.getFirstName().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_FIRSTNAME_EMPTY);
        }
        if (u.getFirstName().length() > 30) {
            throw new BusinessException(BusinessException.BLL_FIRSTNAME_TOO_LONG);
        }
        if (u.getLastName() == null || u.getLastName().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_LASTNAME_EMPTY);
        }
        if (u.getLastName().length() > 30) {
            throw new BusinessException(BusinessException.BLL_LASTNAME_TOO_LONG);
        }
        if (u.getEmail() == null || u.getEmail().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_EMAIL_EMPTY);
        }
        if (u.getEmail().length() > 50) {
            throw new BusinessException(BusinessException.BLL_EMAIL_TOO_LONG);
        }
        if (!u.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")) {
            throw new BusinessException(BusinessException.BLL_EMAIL_NOT_VALID);
        }
        if (u.getPhoneNumber() == null || u.getPhoneNumber().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_PHONE_EMPTY);
        }
        if (u.getPhoneNumber().length() > 15) {
            throw new BusinessException(BusinessException.BLL_PHONE_TOO_LONG);
        }
        if (u.getStreet() == null || u.getStreet().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_STREET_EMPTY);
        }
        if (u.getStreet().length() > 50) {
            throw new BusinessException(BusinessException.BLL_STREET_TOO_LONG);
        }
        if (u.getCity() == null || u.getCity().isEmpty()) {
            throw new BusinessException(BusinessException.BLL_CITY_EMPTY);
            }
        if (u.getCity().length() > 30) {
        	throw new BusinessException(BusinessException.BLL_CITY_TOO_LONG);
        }
		if (u.getZipCode() == null || u.getZipCode().isEmpty()) {
			throw new BusinessException(BusinessException.BLL_ZIPCODE_EMPTY);
		}
		if (u.getZipCode().length() > 10) {
			throw new BusinessException(BusinessException.BLL_ZIPCODE_TOO_LONG);
		}
		
        	            
	}

	// création d'une méthode pour se connecter
//		public static login(String username, String password) throws BusinessException {
//		String pass = User.hashPwd(pwd);
//		System.out.println(pass);
//		User u = user.login(username, password); ;
//
//		return 

//		}

}

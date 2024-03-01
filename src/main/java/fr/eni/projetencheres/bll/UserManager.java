package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UserDAO;

public class UserManager {
	// on souhaite créer une unique instance du UserManager, de sorte à ce que ce
	// soit toujours le même qui soit utilisé
	private static UserDAO instance = null;
//	private UserDAO userDAO ;

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
}
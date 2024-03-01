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

	// création d'une méthode qui se sert de la DAO factory pour créer un nouvel
	// user.
	public static void createUser(User u, String checkPassword) throws BusinessException {
		comparePwd(u.getPassword(), checkPassword);
		UserManager.getInstance().insert(u);
	}

	// Création d'une méthode pour hasher le mot de passe
	// TODO

	// Creation d'une méthode pour recuperer un utilisateur
	public static User getUserById(int userId) {
		return UserManager.getInstance().selectById(userId);
	}

	// Création d'une méthode pour comparer les saisies mot de passe
	public static void comparePwd(String password, String checkPassword) throws BusinessException {
		if (password.equals(checkPassword)) {
			return;
		}
		throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
	}

	public static void editUser(User u) {
		// TODO Auto-generated method stub
		UserManager.getInstance().update(u);
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

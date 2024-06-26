package fr.eni.projetencheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.ConnectionProvider;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.DataException;
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
	public static void createUser(User user, String checkPassword) throws BusinessException, DataException {
		UserManager.checkUserInfo(user);
		comparePwd(user.getPassword(), checkPassword); // comparaison des saisies
		String mdphashe = UserManager.hashPwd(user.getPassword()); // récupération du mdp hashé, et transfert dans la variable
		user.setPassword(mdphashe);
		try {
			UserManager.getInstance().insert(user);
		} catch (DataException e) {
			if (e.getMessage().contains("UQ_Email")) {
				throw new BusinessException(BusinessException.BLL_FIELD_NOT_UNIQUE, "email");
			}
			else if (e.getMessage().contains("UQ_Pseudo")) {
					throw new BusinessException(BusinessException.BLL_FIELD_NOT_UNIQUE, "pseudo");
			}
			System.out.println(e.getMessage());
		}
	}

	// VÉRIFICATION DES CHEATERS
	public static void check_cheaters(String username, String lastName, String firstName, String email, String street,
			String zipCode, String city, String password) throws BusinessException {
		if (username == null || username.isEmpty() || lastName == null || lastName.isEmpty() || firstName == null
				|| firstName.isEmpty() || email == null || email.isEmpty() || street == null || street.isEmpty()
				|| zipCode == null || zipCode.isEmpty() || city == null || city.isEmpty() || password == null
				|| password.isEmpty()) {
			throw new BusinessException(BusinessException.BLL_INSERT_CHEAT);
		}
		if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")) {
			throw new BusinessException(BusinessException.BLL_INSERT_CHEAT);
		}
		if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new BusinessException(BusinessException.BLL_INSERT_CHEAT);
		}
	}

	// INSCRIPTION : Création d'une méthode pour comparer les saisies mot de passe
	public static void comparePwd(String password, String checkPassword) throws BusinessException {
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

	public static User login(String username, String password) throws BusinessException, DataException {
		String pass = UserManager.hashPwd(password);
		User u = UserManager.getInstance().login(username, pass);
		if (u == null) {
			throw new BusinessException(BusinessException.BLL_ERROR_SQLEXCEPTION_LOGIN);
		}
		return u;
	}

	// Creation d'une méthode pour recuperer un utilisateur
	public static User getUserById(int userId) throws DataException {
		return UserManager.getInstance().selectById(userId);
	}

	public static List<User> getAllUsers() throws DataException {
		return UserManager.getInstance().selectAllUsers();
	}

	public static void editUser(User u) throws DataException {
		UserManager.getInstance().update(u);
	}

	public static void addCreditsToUser(double amount, int userId) throws BusinessException, DataException {
		Utils.verifyMoneyField("montant", amount, 1);

		UserManager.getInstance().updateCreditsForUser(amount, userId);
	}

	public static void deleteUser(int userId) throws DataException {
		UserManager.getInstance().delete(userId);
	}

	// Creation d'une methode permettant de verifier que les informations sont
	// conformes aux contraintes
	public static void checkUserInfo(User u) throws BusinessException {
		Utils.verifyStringField("pseudo", u.getUsername(), 0, 30);
		Utils.verifyStringField("mot de passe", u.getPassword(), 8, 30);
		Utils.verifyStringField("prénom", u.getFirstName(), 0, 30);
		Utils.verifyStringField("nom", u.getLastName(), 0, 30);
		Utils.verifyStringField("email", u.getEmail(), 0, 50);
//		Utils.verifyStringField("phoneNumber", u.getPhoneNumber(), 0, 15);
		Utils.verifyStringField("rue", u.getStreet(), 0, 50);
		Utils.verifyStringField("ville", u.getCity(), 0, 30);
		Utils.verifyStringField("code postal", u.getZipCode(), 0, 10);

		if (!u.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new BusinessException(BusinessException.BLL_PASSWORD_NOT_VALID);
		}
		if (!u.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")) {
			throw new BusinessException(BusinessException.BLL_EMAIL_NOT_VALID);
		}
		
	}

	public static String hashPwd(String password) {
		MessageDigest md = null;
		StringBuffer sb = new StringBuffer();
		byte[] response;
		try {
			md = MessageDigest.getInstance("SHA-256");
			response = md.digest(password.getBytes());
			for (int i : response) {
				sb.append((Integer.toString((i & 0xff) + 0x100, 16).substring(1)));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void checkUserInfo(User u, boolean checkPassword) throws BusinessException {
		Utils.verifyStringField("username", u.getUsername(), 0, 30);
		if (checkPassword) {
			Utils.verifyStringField("password", u.getPassword(), 8, 30);
			if (!u.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
				throw new BusinessException(BusinessException.BLL_PASSWORD_NOT_VALID);
			}
		}
		Utils.verifyStringField("prénom", u.getFirstName(), 0, 30);
		Utils.verifyStringField("nom", u.getLastName(), 0, 30);
		Utils.verifyStringField("email", u.getEmail(), 0, 50);
//		Utils.verifyStringField("phoneNumber", u.getPhoneNumber(), 0, 15);
		Utils.verifyStringField("rue", u.getStreet(), 0, 50);
		Utils.verifyStringField("ville", u.getCity(), 0, 30);
		Utils.verifyStringField("code postal", u.getZipCode(), 0, 10);

		if (!u.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$")) {
			throw new BusinessException(BusinessException.BLL_EMAIL_NOT_VALID);
		}

	}

	// TODO : mettre un pattern sur le pseudo
	// TODO : placer une ternaire
	public static boolean mailExist(String email) throws BusinessException {
		try {
			if (UserManager.getInstance().mailExist(email) == 0) {
				throw new BusinessException(BusinessException.BLL_USER_MAILDOESNOTEXIST);
			}
		} catch (DataException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public static void updateNewPassword(String mailFromUser, String password) throws BusinessException, DataException {
		// on vérifie si l'adresse mail renseignée par l'utilisateur est bien dans la base de données
		if (mailExist(mailFromUser)) {
			UserManager.getInstance().updateNewPassword(mailFromUser, UserManager.hashPwd(password));
			System.out.println("BLL UserManager ligne 185");
		}
	}
}

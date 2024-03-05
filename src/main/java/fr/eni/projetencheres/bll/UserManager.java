package fr.eni.projetencheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	// INSCRIPTION : création d'une méthode qui se sert de la DAO factory pour créer un nouvel user.
	public static void createUser(User user, String checkPassword) throws BusinessException {
		comparePwd(user.getPassword(), checkPassword); //comparaison des saisies 
		String mdphashe = UserManager.hashPwd(user.getPassword()); //récup du mdp hashé, et transféré dans la variable
		user.setPassword(mdphashe);
		UserManager.getInstance().insert(user);
		System.out.println("mot de passe mdphashé: " + mdphashe + " u.getPassword () : " + user.getPassword());
	}

	
	// VERIFICATION DES CHEATERS
	public static void check_cheaters(String username, String lastName, String firstName, String email, String street, String zipCode, String city, String password) throws BusinessException {
		if (username == null || username.isEmpty() || lastName == null || lastName.isEmpty() || firstName == null || firstName.isEmpty() || email == null || email.isEmpty() || street == null || street.isEmpty() || zipCode == null || zipCode.isEmpty() || city == null || city.isEmpty() || password == null || password.isEmpty())  {
			throw new BusinessException(BusinessException.DAL_INSERT_CHEAT);
		}
		if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")) {
			throw new BusinessException(BusinessException.DAL_INSERT_CHEAT);
        }
		if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new BusinessException(BusinessException.DAL_INSERT_CHEAT);
		}
	}
	

	// INSCRIPTION : Création d'une méthode pour comparer les saisies mot de passe
	public static void comparePwd(String password, String checkPassword) throws BusinessException {
		if (!password.equals(checkPassword)) {
			throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
		}
	}
	
	//Creation d'une méthode qui verifie le mot de passe de l'utilisateur
	public static boolean checkPwd(User u, String password) throws BusinessException {
		if ( !(u.getPassword().trim().equals(password.trim())) ) {
			throw new BusinessException(BusinessException.BLL_PWD_USER_EXCEPTION);
		}
		return true;
	}

	public static User login(String userName, String password) throws BusinessException {
			String pass = UserManager.hashPwd(password);
			User u = UserManager.getInstance().login(userName, pass);
		if (u == null) {
			throw new BusinessException(BusinessException.BLL_ERROR_SQLEXCEPTION_LOGIN);
		}
		return u;
	}

	// Creation d'une méthode pour recuperer un utilisateur
	public static User getUserById(int userId) throws BusinessException {
		try {
			return UserManager.getInstance().selectById(userId);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		}
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
	
	public static String hashPwd(String password)
	{
		MessageDigest md = null;
		StringBuffer sb = new StringBuffer();
		byte[] response;
		try {
			md=MessageDigest.getInstance("SHA-256");			
			response=md.digest(password.getBytes());			
			for(int i:response)
			{
				sb.append((Integer.toString((i&0xff)+0x100, 16).substring(1)));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}

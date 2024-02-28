package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UserDAO;

public class UserManager {
	private UserDAO userDAO;

	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}

	public void newUser(String pseudo, String lastName, String firstName, String email, String phoneNumber,
			String street, String postalCode, String city, String password) {
		User u = new User(pseudo, lastName, firstName, email, phoneNumber, street, postalCode, city, password);
		this.userDAO.insert(u);
	}
	
	public User getUserById(int id) {
		return this.userDAO.selectById(id);
	}
	
	public User getUserByPseudo(String pseudo) {
		return this.userDAO.selectByPseudo(pseudo);
	}
	
	public User getUserByEmail(String email) {
		return this.userDAO.selectByEmail(email);
	}
	
	public void updateUser(User user) {
		this.userDAO.update(user);
	}
	
	public void deleteUser(int id) {
		this.userDAO.delete(id);
	}
	
	public void updateUserCredit(int id, int credit) {
		User u = this.userDAO.selectById(id);
		u.setCredit(credit);
		this.userDAO.update(u);
	}
}

package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.User;
public interface UserDAO {
	public static UserDAO instance = null ;
	
//	public static UserDAO getInstance() {
//		if (instance == null) {
//			instance = new UserDAO();
//		}
//		return instance;
//	}
	
	
//Pattern de méthodes permettant de faire les requetes vers la BDD
	public void insert(User user);
	public User selectById(int id);
	public User selectByUsername(String username);
	public User selectByEmail(String email);
	public void update(User user);
	public void delete(int id);
}

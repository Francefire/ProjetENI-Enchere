package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.User;

public interface UserDAO {

//Pattern de méthodes permettant de faire les requetes vers la BDD
	public void insert(User user) throws BusinessException; 

	public User selectById(int id) throws BusinessException;

	public User selectByUsername(String username);

	public User selectByEmail(String email);
	
	public void update(User user);
	
	public void updateCreditsForUser(double amount, int userId) throws BusinessException;

	public void delete(int id);

	public User login(String userName, String password) throws BusinessException ;

	public int check(String userName, String password) throws BusinessException;

	public List<User> selectAllUsers();
}

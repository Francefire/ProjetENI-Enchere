package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.User;

public interface UserDAO {

//Pattern de méthodes permettant de faire les requetes vers la BDD
	public void insert(User user) throws DataException;

	public User selectById(int id) throws DataException;

	public User selectByUsername(String username) throws DataException;

	public User selectByEmail(String email) throws DataException;

	public void update(User user) throws DataException;

	public void updateCreditsForUser(double amount, int userId) throws DataException;

	public void delete(int id) throws DataException;

	public User login(String userName, String password) throws DataException;

	public int check(String userName, String password) throws DataException;

	public List<User> selectAllUsers() throws DataException;
	
	public int mailExist(String email) throws DataException ; 
	
	public void updateNewPassword(String mailFromUser, String password) throws  DataException ;
}

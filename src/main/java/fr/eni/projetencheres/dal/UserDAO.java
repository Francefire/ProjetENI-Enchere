package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.User;

public interface UserDAO {

//Pattern de méthodes permettant de faire les requetes vers la BDD
	public void insert(User user);

	public User selectById(int id) throws BusinessException;

	public User selectByUsername(String username);

	public User selectByEmail(String email);

	public void update(User user);

	public void delete(int id);

	public User login(String userName, String password) throws BusinessException ;

	public int check(String userName, String password) throws BusinessException;
}
